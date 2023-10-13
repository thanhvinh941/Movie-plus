import { map } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { DynamicMasterEntityService } from '../../services/dynamic-master-entity.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SiteService } from 'src/app/common/config/endpoint.constants';

@Component({
  selector: 'app-site-info-create',
  templateUrl: './site-info-create.component.html',
  styleUrls: ['./site-info-create.component.css'],
})
export class SiteInfoCreateComponent implements OnInit {
  constructor(
    private _dynamicMasterEntity: DynamicMasterEntityService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  siteInfoForm: FormGroup = this.fb.group({
    siteName: ['', [Validators.required]],
    localtion: ['', [Validators.required]],
    siteGradleId: ['', [Validators.required]],
    siteZipNo: ['', [Validators.required]],
    siteLatY: [0, [Validators.required]],
    siteLonX: [0, [Validators.required]],
    accessInfo: ['', [Validators.required]],
    information: ['', [Validators.required]],
    notice: ['', [Validators.required]],
  });

  siteGradleList$!: { id: string; displayName: string }[];

  fieldDefind!: any;

  async ngOnInit() {
    await this._dynamicMasterEntity
      .getDynamicMasterEntity(SiteService.END_POINT, {
        tableName: SiteService.TABLE.SITE_GRADLE,
        conditionStr: 'del_flg = 0 and member_visible_flg = 1',
        listFields: ['id', 'siteGradeName'],
        orderBys: { sort_no: 'asc' },
      })
      .then((res) => {
        this.siteGradleList$ = res?.data.map((_r: any) => {
          return { id: _r['id'], displayName: _r['siteGradeName'] };
        });
      });

    this.fieldDefind = {
      siteName: {
        label: 'Site Name',
        type: 'text',
        placeholder: '',
        resource: [],
      },
      localtion: {
        label: 'Location',
        type: 'text',
        placeholder: '',
        resource: [],
      },
      siteGradleId: {
        label: 'Site Gradle',
        type: 'select',
        placeholder: '',
        resource: this.siteGradleList$,
      },
      siteZipNo: {
        label: 'Site ZipNo',
        type: 'text',
        placeholder: '',
        resource: [],
      },
      siteLatY: {
        label: 'Site LatY',
        type: 'text',
        placeholder: '',
        resource: [],
      },
      siteLonX: {
        label: 'Site LonX',
        type: 'text',
        placeholder: '',
        resource: [],
      },
      accessInfo: {
        label: 'Access Info',
        type: 'text',
        placeholder: '',
        resource: [],
      },
      information: {
        label: 'Information',
        type: 'text',
        placeholder: '',
        resource: [],
      },
      notice: {
        label: 'Notice',
        type: 'text',
        placeholder: '',
        resource: [],
      },
    };

    console.log(this.siteGradleList$);
  }

  submitForm(): void {
    this._dynamicMasterEntity
      .entryDynamicMasterEntity(SiteService.END_POINT, {
        tableName: SiteService.TABLE.SITE_INFO,
        records: this.siteInfoForm.value,
      })
      .then((res) => {
        let siteInfoPlanId = res.data[0];
        this.router.navigateByUrl(
          `/operator/site-infos?targetId=${siteInfoPlanId}`
        );
      });
  }
}
