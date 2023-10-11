import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { DynamicMasterEntityService } from '../../services/dynamic-master-entity.service';
import { ChargeService } from 'src/app/common/config/endpoint.constants';
import { ChargeInfoService } from '../../services/charge-info.service';

@Component({
  selector: 'app-charge-info',
  templateUrl: './charge-info.component.html',
  styleUrls: ['./charge-info.component.css'],
})
export class ChargeInfoComponent implements OnInit {
  constructor(private _chargeInfoService: ChargeInfoService) {}
  async ngOnInit(): Promise<void> {
    await this._chargeInfoService.selectChargeInfo({}).then(
      (res) =>
        (this.movieGradleList$ = res?.data as {
          id: string;
          movieGradeName: string;
        }[])
    );
  }
  movieGradleList$!: { id: string; movieGradeName: string }[];
  siteGradleList$!: { id: string; siteGradeName: string }[];
  seatGradleList$!: { id: string; seatGradeName: string }[];
  chargeInfoList$!: { [key: string]: number };

  getChargByKey(key: string) {
    return this.chargeInfoList$[key];
  }
}
