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
  initCache: { [key: string]: { data: any } } = {};
  editCache: { [key: string]: { edit: boolean; data: any; change: boolean } } =
    {};
  chargeInfoPlanData!: {
    id: string;
    planName: string;
    dayKbn: number;
    planKbn: number;
    timeStart: Date;
    timeEnd: Date;
    delFlg: number;
    registTime: Date;
    updateTime: Date;
    updateUser: string;
  }[];

  constructor(private _dynamicMasterEntity: DynamicMasterEntityService) {}

  async ngOnInit(): Promise<void> {
    await this._dynamicMasterEntity
      .getDynamicMasterEntity(ChargeService.END_POINT, {
        tableName: ChargeService.TABLE.CHARGE_INFO_PLAN,
        conditionStr: 'del_flg = 0',
        listFields: [
          'id',
          'plan_name',
          'day_kbn',
          'plan_kbn',
          'time_start',
          'time_end',
          'del_flg',
          'regist_time',
          'update_time',
          'update_user',
        ],
      })
      .then((res) => {
        this.chargeInfoPlanData = res?.data;
        this.updateEditCache();
      });
  }

  startEdit(id: string): void {
    this.editCache[id].edit = true;
  }

  cancelEdit(id: string): void {
    const index = this.chargeInfoPlanData.findIndex((item) => item.id === id);
    this.editCache[id] = {
      data: { ...this.chargeInfoPlanData[index] },
      edit: false,
      change: false,
    };
  }

  cancelChange(id: string): void {
    const index = this.chargeInfoPlanData.findIndex((item) => item.id === id);
    Object.assign(this.chargeInfoPlanData[index], this.initCache[id].data);
    this.editCache[id].change = false;
  }

  saveEdit(id: string): void {
    const index = this.chargeInfoPlanData.findIndex((item) => item.id === id);
    Object.assign(this.chargeInfoPlanData[index], this.editCache[id].data);
    this.editCache[id].edit = false;
    this.editCache[id].change = true;
  }

  updateEditCache(): void {
    this.chargeInfoPlanData.forEach((item) => {
      this.initCache[item.id] = {
        data: { ...item },
      };
      this.editCache[item.id] = {
        edit: false,
        data: this.initCache[item.id].data,
        change: false,
      };
    });
  }

  onSaveAllChange(): void {
    let changeCache = Object.entries(this.editCache).filter(
      ([key, value]) => value.change
    );
    console.log(changeCache);
  }
}
