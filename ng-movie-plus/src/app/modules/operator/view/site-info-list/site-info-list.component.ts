import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { SiteInfo } from '../../models/site-info';
import { DynamicMasterEntityService } from '../../services/dynamic-master-entity.service';
import { SiteService } from 'src/app/common/config/endpoint.constants';

@Component({
  selector: 'app-site-info-list',
  templateUrl: './site-info-list.component.html',
  styleUrls: ['./site-info-list.component.css'],
})
export class SiteInfoListComponent implements OnInit {
  initCache: { [key: string]: { data: any } } = {};
  editCache: { [key: string]: { edit: boolean; data: any; change: boolean } } =
    {};
  siteInfoDataList!: any[];

  constructor(private _dynamicMasterEntity: DynamicMasterEntityService) {}

  async ngOnInit(): Promise<void> {
    await this._dynamicMasterEntity
      .getDynamicMasterEntity(SiteService.END_POINT, {
        tableName: SiteService.TABLE.SITE_INFO,
        conditionStr: 'del_flg = 0',
        listFields: [
          'id',
          'access_info',
          'del_flg',
          'information',
          'localtion',
          'notice',
          'regist_time',
          'site_gradle_id',
          'site_lat_y',
          'site_lon_x',
          'site_name',
          'site_zip_no',
          'update_time',
          'update_user',
        ],
      })
      .then((res) => {
        this.siteInfoDataList = res?.data;
        this.updateEditCache();
      });
  }

  startEdit(id: string): void {
    this.editCache[id].edit = true;
  }

  cancelEdit(id: string): void {
    const index = this.siteInfoDataList.findIndex((item) => item.id === id);
    this.editCache[id] = {
      data: { ...this.siteInfoDataList[index] },
      edit: false,
      change: false,
    };
  }

  cancelChange(id: string): void {
    const index = this.siteInfoDataList.findIndex((item) => item.id === id);
    Object.assign(this.siteInfoDataList[index], this.initCache[id].data);
    this.editCache[id].change = false;
  }

  saveEdit(id: string): void {
    const index = this.siteInfoDataList.findIndex((item) => item.id === id);
    Object.assign(this.siteInfoDataList[index], this.editCache[id].data);
    this.editCache[id].edit = false;
    this.editCache[id].change = true;
  }

  updateEditCache(): void {
    this.siteInfoDataList.forEach((item) => {
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
