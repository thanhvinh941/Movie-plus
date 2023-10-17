import { filter, first } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { DynamicMasterEntityService } from '../../services/dynamic-master-entity.service';
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SiteService } from 'src/app/common/config/endpoint.constants';
import { RoomInfoService } from '../../services/room-info.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import * as _ from 'lodash';
import { NzModalService } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-room-detail',
  templateUrl: './room-detail.component.html',
  styleUrls: ['./room-detail.component.css'],
})
export class RoomDetailComponent implements OnInit {
  constructor(
    private _dynamicMasterEntity: DynamicMasterEntityService,
    private _roomInfoService: RoomInfoService,
    private fb: FormBuilder,
    private router: Router,
    private _route: ActivatedRoute,
    private message: NzMessageService,
    private modalService: NzModalService
  ) {}

  showTimeForm!: FormGroup;
  roomSeatForm = this.fb.group({
    roomId: '',
    seatMaster: new FormArray<
      FormGroup<{
        seatRow: FormControl<number | null>;
        seatColume: FormControl<number | null>;
        seatSize: FormControl<number | null>;
        seatGradle: FormControl<string | null>;
        seatEnable: FormControl<boolean | null>;
      }>
    >([]),
  });
  roomSeatStatus!: FormArray<FormGroup<any>>;
  roomSeatStatusRender! : any;
  roomInfo$!: any;
  seatGradleList$!: { id: string; displayName: string }[];
  siteId!: string;
  roomId!: string;
  rowSize = 7;
  columnSize = 12;
  isVisible = false;
  isSpinning = false;

  async ngOnInit(): Promise<void> {
    this._route.params.subscribe(async (parameter) => {
      this.roomSeatForm.setControl(
        'roomId',
        this.fb.control(parameter['roomId']!)
      );
      this.roomId = parameter['roomId'];
      this.siteId = parameter['siteId'];
      await this._dynamicMasterEntity
        .getDynamicMasterEntity(SiteService.END_POINT, {
          tableName: SiteService.TABLE.SEAT_GRADLE,
          conditionStr: 'del_flg = 0 and member_visible_flg = 1',
          listFields: ['id', 'seatGradeName'],
          orderBys: { sort_no: 'asc' },
        })
        .then((res) => {
          this.seatGradleList$ = res?.data.map((_r: any) => {
            return { id: _r['id'], displayName: _r['seatGradeName'] };
          });
        });

      await this._roomInfoService
        .getRoomInfoDetail({ id: parameter['roomId'] })
        .then((res) => {
          this.roomInfo$ = res?.data;
          this.roomSeatStatus = this.fb.array(
            _.map(this.roomInfo$['roomSeats'], (x) => {
              return this.fb.group({ ...x['seatMaster'], seatEnable: true });
            })
          );

          this.roomInfo$['roomSeats'] = _.groupBy(
            res?.data['roomSeats'],
            'seatMaster.seatRow'
          );

          console.log(this.roomSeatStatus.value);
        });

      let seatMaster = new FormArray<
        FormGroup<{
          seatRow: FormControl<number | null>;
          seatColume: FormControl<number | null>;
          seatSize: FormControl<number | null>;
          seatGradle: FormControl<string | null>;
          seatEnable: FormControl<boolean | null>;
        }>
      >([]);
      for (let i = 1; i <= this.rowSize; i++) {
        for (let j = 1; j <= this.columnSize; j++) {
          let seatForm = this.fb.group({
            seatRow: i!,
            seatColume: j!,
            seatSize: 1,
            seatGradle: this.seatGradleList$[0].id,
            seatEnable: true,
          });
          seatMaster.push(seatForm);
        }
      }

      this.roomSeatForm.setControl('seatMaster', seatMaster);
    });
  }

  getRoomSeatStatus(row: number, column : number){
    return this.roomSeatStatus.controls.find((group: AbstractControl) => group.get('seatRow')?.value == row && group.get('seatColume')?.value == column)
  }

  getSeatSize(index: number): number | null {
    return this.roomSeatForm.controls.seatMaster.at(index).controls.seatSize
      .value;
  }

  getSeatEnable(index: number): boolean | null {
    return this.roomSeatForm.controls.seatMaster.at(index).controls.seatEnable
      .value;
  }

  getSeatRow(index: number): number | null {
    return this.roomSeatForm.controls.seatMaster.at(index).controls.seatRow
      .value;
  }

  getSeatColumn(index: number): number | null {
    return this.roomSeatForm.controls.seatMaster.at(index).controls.seatColume
      .value;
  }

  showModal(): void {
    this.isVisible = true;
  }

  showModalShowTime(): void {
    this.isVisible = true;
  }

  async handleOk(): Promise<void> {
    console.log(this.roomSeatForm.value);
    this.isSpinning = true;
    await this._roomInfoService
      .settingRoomSeat(this.roomSeatForm.value)
      .then((res) => {
        if (res?.data) {
          this.message.success('Setting Room Seat success');
        } else {
          this.message.error(res.errors[0]);
        }
        this.isVisible = false;
        this.isSpinning = false;
      });
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  addShowTime(): void {
    this.showTimeForm = this.fb.group({
      roomId: [this.roomId],
      siteId: [this.siteId],
      movieId: [, Validators.required],
      startTime: [new Date(), Validators.required],
      endTime: [new Date(), Validators.required],
    });
    console.log(this.showTimeForm.value);
  }

  submitShowTime() {
    console.log(this.showTimeForm.value);
  }
}

