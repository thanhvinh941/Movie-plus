import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { RoomInfoService } from '../../../services/room-info.service';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';

@Component({
  selector: 'app-showtime-of-room',
  templateUrl: './showtime-of-room.component.html',
  styleUrls: ['./showtime-of-room.component.css'],
})
export class ShowtimeOfRoomComponent {
  loadingData = false;
  showTimeData$!: any[];
  constructor(
    private _roomInfoService: RoomInfoService,
    private fb: FormBuilder
  ) {}

  getShowTimeOfRoom(roomId: string): void {
    this.loadingData = true;
    this._roomInfoService
      .getShowTimeOfRoom({ id: roomId, ignoreDelFlg: true })
      .pipe(tap(() => (this.loadingData = false)))
      .subscribe((res) => {
        this.showTimeData$ = res.data.records;
      });
  }
}
