import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import {
  MovieService,
  SiteService,
} from 'src/app/common/config/endpoint.constants';
import { DynamicMasterEntityService } from '../services/dynamic-master-entity.service';

@Component({
  selector: 'app-charge-info-creat',
  templateUrl: './charge-info-creat.component.html',
  styleUrls: ['./charge-info-creat.component.css'],
})
export class ChargeInfoCreatComponent implements OnInit {
  constructor(
    private _dynamicMasterEntity: DynamicMasterEntityService,
    private fb: FormBuilder
  ) {}
  movieGradleList$!: { id: string; movieGradeName: string }[];
  siteGradleList$!: { id: string; siteGradeName: string }[];
  seatGradleList$!: { id: string; seatGradeName: string }[];
  chargeInfoForm!: FormGroup;
  planKbnList: { id: number; displayName: string }[] = [];
  dayKbnList: { id: number; displayName: string }[] = [];

  async ngOnInit(): Promise<void> {
    await this._dynamicMasterEntity
      .getDynamicMasterEntity(MovieService.END_POINT, {
        tableName: MovieService.TABLE.MOVIE_GRADLE,
        conditionStr: 'del_flg = 0 and member_visible_flg = 1',
        listFields: ['id', 'movieGradeName'],
        orderBys: { sort_no: 'asc' },
      })
      .then(
        (res) =>
          (this.movieGradleList$ = res?.data as {
            id: string;
            movieGradeName: string;
          }[])
      );

    await this._dynamicMasterEntity
      .getDynamicMasterEntity(SiteService.END_POINT, {
        tableName: SiteService.TABLE.SITE_GRADLE,
        conditionStr: 'del_flg = 0 and member_visible_flg = 1',
        listFields: ['id', 'siteGradeName'],
        orderBys: { sort_no: 'asc' },
      })
      .then(
        (res) =>
          (this.siteGradleList$ = res?.data as {
            id: string;
            siteGradeName: string;
          }[])
      );

    await this._dynamicMasterEntity
      .getDynamicMasterEntity(SiteService.END_POINT, {
        tableName: SiteService.TABLE.SEAT_GRADLE,
        conditionStr: 'del_flg = 0 and member_visible_flg = 1',
        listFields: ['id', 'seatGradeName'],
        orderBys: { sort_no: 'asc' },
      })
      .then(
        (res) =>
          (this.seatGradleList$ = res?.data as {
            id: string;
            seatGradeName: string;
          }[])
      );

    this.setupFormGroup();
  }

  setupFormGroup() {
    const formGroupFields: any = {};
    for (const siteGradle of this.siteGradleList$) {
      for (const seatGradle of this.seatGradleList$) {
        for (const movieGradle of this.movieGradleList$) {
          let key = movieGradle?.id + '/' + seatGradle.id + '/' + siteGradle.id;
          formGroupFields[key] = new FormControl(49000);
        }
      }
    }

    this.chargeInfoForm = this.fb.group({
      planName: ['', [Validators.required]],
      planKbn: [0, [Validators.required]],
      dayKbn: [0, [Validators.required]],
      timeStart: ['', [Validators.required]],
      timeEnd: ['', [Validators.required]],
      chargeInfoSet: this.fb.group(formGroupFields),
    });
    console.log(this.chargeInfoForm.value);
  }

  submitForm() {
    console.log(this.chargeInfoForm.value);
  }

  getSiteGradleName(id: string): string {
    return this.siteGradleList$.find((s) => s.id === id)?.siteGradeName!;
  }
}
