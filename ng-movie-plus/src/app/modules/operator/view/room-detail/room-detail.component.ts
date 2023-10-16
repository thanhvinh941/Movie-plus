import { Component, OnInit } from '@angular/core';
import { DynamicMasterEntityService } from '../../services/dynamic-master-entity.service';
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SiteService } from 'src/app/common/config/endpoint.constants';
import { RoomInfoService } from '../../services/room-info.service';

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
    private _route: ActivatedRoute
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
  roomInfo$!: any;
  seatGradleList$!: { id: string; displayName: string }[];
  siteId!: string;
  roomId!: string;
  rowSize = 7;
  columnSize = 8;
  isVisible = false;
  isSpinning = false;

  async ngOnInit(): Promise<void> {
    await this._route.params.subscribe((parameter) => {
      this.roomSeatForm.setControl(
        'roomId',
        this.fb.control(parameter['roomId']!)
      );
      this.roomId = parameter['roomId'];
      this.siteId = parameter['siteId'];
      this._dynamicMasterEntity
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

      this._roomInfoService
        .getRoomInfoDetail({ id: parameter['roomId'] })
        .then((res) => {
          this.roomInfo$ = res?.data;
        });
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
          seatGradle: '',
          seatEnable: true,
        });
        seatMaster.push(seatForm);
      }
    }

    this.roomSeatForm.setControl('seatMaster', seatMaster);
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

  handleOk(): void {
    this.isSpinning = true;
    console.log(this.roomSeatForm.value);
    // this.isVisible = false;
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
