import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-charge-info-detail',
  templateUrl: './charge-info-detail.component.html',
  styleUrls: ['./charge-info-detail.component.css']
})
export class ChargeInfoDetailComponent implements OnInit{
  parameterValue!: string;

  constructor(private _route: ActivatedRoute){}
  ngOnInit(): void {
    this._route.params.subscribe(parameter => {
      this.parameterValue = parameter['id'];
    })
    throw new Error('Method not implemented.');
  }
}
