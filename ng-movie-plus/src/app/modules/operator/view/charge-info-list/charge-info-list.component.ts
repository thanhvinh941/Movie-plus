import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { DynamicMasterEntityService } from '../../services/dynamic-master-entity.service';
import { ChargeService } from 'src/app/common/config/endpoint.constants';

@Component({
  selector: 'app-charge-info-list',
  templateUrl: './charge-info-list.component.html',
  styleUrls: ['./charge-info-list.component.css'],
})
export class ChargeInfoListComponent implements OnInit {
  chargeInfoPlanData!: any[];

  constructor(private _dynamicMasterEntity: DynamicMasterEntityService) {}

  async ngOnInit(): Promise<void> {
    await this._dynamicMasterEntity
      .getDynamicMasterEntity(ChargeService.END_POINT, {
        tableName: ChargeService.TABLE.CHARGE_INFO_PLAN,
        conditionStr: 'del_flg = 0',
        listFields: ['*'],
        orderBys: { sort_no: 'asc' },
      })
      .then((res) => (this.chargeInfoPlanData = res?.data));
  }
}
