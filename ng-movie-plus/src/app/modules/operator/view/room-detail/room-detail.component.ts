import { debounceTime, filter, first, startWith, tap } from 'rxjs';
import { Component, OnInit, Pipe, ViewChild } from '@angular/core';
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
import { MovieInfoService } from 'src/app/modules/operator/services/movie-info.service';
import { ShowtimeOfRoomComponent } from './showtime-of-room/showtime-of-room.component';
import { SeatInRoomComponent } from './seat-in-room/seat-in-room.component';

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
    private modalService: NzModalService,
    private movieInfoService: MovieInfoService,
  ) {}
  @ViewChild('showtimeOfRoomComp') showtimeOfRoomComp! : ShowtimeOfRoomComponent
  @ViewChild('seatInRoomComp') seatInRoomComp! : SeatInRoomComponent
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
  roomSeatStatusRender!: any;
  roomInfo$!: any;
  seatGradleList$!: { id: string; displayName: string }[];
  siteId!: string;
  roomId!: string;
  rowSize = 7;
  columnSize = 12;
  isVisibleRoomSeat = false;
  isSpinningRoomSeat = false;
  isVisibleShowTime = false;
  isSpinningShowTime = false;
  dateFormat = 'yyyy/MM/dd HH:mm:ss';
  optionLoading = false;
  optionList: any;

  handleSearch($event: string) {
    console.log($event);
    this.optionLoading = true;
    this.movieInfoService
      .fullTextSearchMovie({ term: $event })
      .pipe(tap(() => (this.optionLoading = false)))
      .subscribe((res) => (this.optionList = res.data));
  }

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
  
  ngAfterViewInit() {
    console.log(this.showtimeOfRoomComp);
    console.log(this.seatInRoomComp);
    this.showtimeOfRoomComp.getShowTimeOfRoom(this.roomId);
    this.seatInRoomComp.getSeatInRoom(this.roomId);
  }

  getRoomSeatStatus(row: number, column: number) {
    return this.roomSeatStatus.controls.find(
      (group: AbstractControl) =>
        group.get('seatRow')?.value == row &&
        group.get('seatColume')?.value == column
    );
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

  showModalRoomSeat(): void {
    this.isVisibleRoomSeat = true;
  }

  showModalShowTime(): void {
    let date = new Date();
    let minN = date.getMinutes();
    let dateN = date.getDate();
    date.setMinutes(Math.floor(minN / 10) * 10);
    date.setDate(dateN + 1);
    date.setSeconds(0);
    this.showTimeForm = this.fb.group({
      roomId: [this.roomId],
      siteId: [this.siteId],
      movieId: [, Validators.required],
      startTime: [date, Validators.required],
      endTime: [date, Validators.required],
    });
    this.isVisibleShowTime = true;
  }

  async handleOkRoomSeat(): Promise<void> {
    console.log(this.roomSeatForm.value);
    this.isSpinningRoomSeat = true;
    await this._roomInfoService
      .settingRoomSeat(this.roomSeatForm.value)
      .then((res) => {
        if (res?.data) {
          this.message.success('Setting Room Seat success');
        } else {
          this.message.error(res.errors[0]);
        }
        this.isVisibleRoomSeat = false;
        this.isSpinningRoomSeat = false;
      });
  }

  handleCancelRoomSeat(): void {
    this.isVisibleRoomSeat = false;
  }

  handleCancelShowTime(): void {
    this.isVisibleShowTime = false;
  }

  async handleOkShowTime() {
    console.log(this.showTimeForm.value);
    this.isSpinningShowTime = true;
    await this._roomInfoService
      .settingShowTime(this.showTimeForm.value)
      .then((res) => {
        if (res?.data) {
          this.message.success('Setting Room Seat success');
        } else {
          this.message.error(res.errors[0]);
        }
        this.isVisibleShowTime = false;
        this.isSpinningShowTime = false;
      });
  }
}
