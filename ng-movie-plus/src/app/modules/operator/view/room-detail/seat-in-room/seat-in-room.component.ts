import { Component, Input } from '@angular/core';
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormGroup,
} from '@angular/forms';
import { tap } from 'rxjs';
import { RoomInfoService } from '../../../services/room-info.service';
import * as _ from 'lodash';

@Component({
  selector: 'app-seat-in-room',
  templateUrl: './seat-in-room.component.html',
  styleUrls: ['./seat-in-room.component.css'],
})
export class SeatInRoomComponent {
  @Input()
  roomSeatStatus!: FormArray<FormGroup<any>>;
  roomSeatData$!: {};
  loadingData = false;

  constructor(
    private _roomInfoService: RoomInfoService,
    private fb: FormBuilder
  ) {}

  getRoomSeatStatus(row: number, column: number) {
    return this.roomSeatStatus.controls.find(
      (group: AbstractControl) =>
        group.get('seatRow')?.value == row &&
        group.get('seatColume')?.value == column
    );
  }

  getSeatInRoom(roomId: string) {
    this.loadingData = true;
    this._roomInfoService
      .getSeatInRoom({ id: roomId })
      .pipe(tap(() => (this.loadingData = false)))
      .subscribe((res) => {
        this.roomSeatStatus = this.fb.array(
          _.map(res?.data, (x) => {
            return this.fb.group({ ...x['seatMaster'], seatEnable: true });
          })
        );
        this.roomSeatData$ = _.groupBy(res?.data, 'seatMaster.seatRow');
      });
  }

  addRow() {
    let currentRow = _.size(this.roomSeatData$);
    let nextRow = currentRow + 1;
    let prevColunmCount = 0;
    _.forEach(this.roomSeatData$, (value, key) => {
      prevColunmCount = (value as any).length;
    });
    let listNewSeat: any[] = [];
    for (let i = 1; i <= prevColunmCount; i++) {
      listNewSeat.push({
        id: null,
        seatColume: i,
        seatEnable: false,
        seatGradle: '',
        seatRow: nextRow,
        seatSize: 1,
      });
    }
    _.forEach(listNewSeat, (x) => {
      this.roomSeatStatus.push(this.fb.group({ ...x, seatEnable: false }));
    });

    _.set(
      this.roomSeatData$,
      `${nextRow}`,
      _.map(listNewSeat, (x) => {
        return { seatMaster: { ...x } };
      })
    );
  }
}
