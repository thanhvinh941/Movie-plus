import { SiteInfoService } from './../../services/site-info.service';
import { SiteInfo } from './../../models/site-info';
import { Component, OnInit } from '@angular/core';
import { DynamicMasterEntityService } from '../../services/dynamic-master-entity.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SiteService } from 'src/app/common/config/endpoint.constants';

@Component({
  selector: 'app-site-info-detail',
  templateUrl: './site-info-detail.component.html',
  styleUrls: ['./site-info-detail.component.css'],
})
export class SiteInfoDetailComponent implements OnInit {
  constructor(
    private _dynamicMasterEntity: DynamicMasterEntityService,
    private _siteInfoService: SiteInfoService,
    private fb: FormBuilder,
    private router: Router,
    private _route: ActivatedRoute
  ) {}

  roomInfoForm!: FormGroup;

  isVisible = false;
  siteInfo$: any;
  fieldDefind!: any;
  siteId: any;

  async ngOnInit(): Promise<void> {
    await this._route.params.subscribe((parameter) => {
      this.siteId = parameter['id'];
      this._siteInfoService
        .retrievesiteInfo({ id: parameter['id'] })
        .then((res) => (this.siteInfo$ = res.data));
    });

    this.roomInfoForm = this.fb.group({
      siteId: [this.siteId, [Validators.required]],
      roomName: ['', [Validators.required]],
    });

    this.fieldDefind = {
      roomName: {
        label: 'Room Name',
        type: 'text',
        placeholder: '',
        resource: [],
      },
    };
  }

  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    this.isVisible = false;
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  submitForm(): void {
    this._dynamicMasterEntity
      .entryDynamicMasterEntity(SiteService.END_POINT, {
        tableName: SiteService.TABLE.ROOM_INFO,
        records: this.roomInfoForm.value,
      })
      .then((res) => {
        let siteInfoPlanId = res.data[0];
        this.router.navigateByUrl(
          `/operator/site-infos/${this.siteId}?roomId=${siteInfoPlanId}`
        );
      });
  }

  routerToRoomInfo(id: string){
    this.router.navigateByUrl(
      `/operator/site-infos/room-info/${id}`
    );
  }
}
