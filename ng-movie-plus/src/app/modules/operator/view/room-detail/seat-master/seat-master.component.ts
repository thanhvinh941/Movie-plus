import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'room-seat-master',
  templateUrl: './seat-master.component.html',
  styleUrls: ['./seat-master.component.css'],
})
export class SeatMasterComponent{
  @Input() form!: FormGroup;
  @Input() seatRow!: number;
  @Input() seatColunm!: number;
  get seatEnable() : boolean{
    return this.form.controls['seatEnable'].value as boolean;
  }
  @Input() stylesObj: any = {};
  @Input() seatGradleList$! : any[];
  @Input() isDisplayOption : boolean = true;
}
