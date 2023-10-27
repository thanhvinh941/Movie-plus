import { Component, Input } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { tap } from 'rxjs';
import { RoomInfoService } from '../../../services/room-info.service';
import * as _ from 'lodash';

@Component({
  selector: 'app-seat-in-room',
  templateUrl: './seat-in-room.component.html',
  styleUrls: ['./seat-in-room.component.css']
})
export class SeatInRoomComponent {
  @Input()
  roomSeatStatus!: FormArray<FormGroup<any>>;
  roomSeatData$! : {};
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

  getSeatInRoom(roomId: string){
    this.loadingData = true;
    this._roomInfoService
      .getSeatInRoom({ id: roomId})
      .pipe(tap(() => (this.loadingData = false)))
      .subscribe((res) => {
        this.roomSeatData$ = res.data
        // this.roomSeatStatus = this.fb.array(
        //   _.map(this.roomSeatData$, (x) => {
        //     return this.fb.group({ ...x['seatMaster'], seatEnable: true });
        //   })
        // );

        this.roomSeatData$ = _.groupBy(
          res?.data,
          'seatMaster.seatRow'
        );
      });
  }
}
