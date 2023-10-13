import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OperatorRoutingModule } from './operator-routing.module';
import { OperatorComponent } from './operator.component';
import { MovieInfoListComponent } from './view/movie-info-list/movie-info-list.component';
import { MovieInfoDetailComponent } from './view/movie-info-detail/movie-info-detail.component';
import { MovieInfoCreateComponent } from './view/movie-info-create/movie-info-create.component';
import { NgZorroAntdModule } from 'src/app/common/ng-zorro-antd.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PaginationComponent } from 'src/app/common/layout/pagination/pagination.component';
import { TableComponent } from 'src/app/common/layout/table/table.component';
import { SiteInfoListComponent } from './view/site-info-list/site-info-list.component';
import { SiteInfoDetailComponent } from './view/site-info-detail/site-info-detail.component';
import { ChargeInfoComponent } from './view/charge-info/charge-info.component';
import { ChargeInfoCreatComponent } from './view/charge-info-creat/charge-info-creat.component';
import { RoomDetailComponent } from './view/room-detail/room-detail.component';
import { RoomCreateComponent } from './view/room-create/room-create.component';
import { ChargeInfoListComponent } from './view/charge-info-list/charge-info-list.component';
import { ChargeInfoDetailComponent } from './view/charge-info-detail/charge-info-detail.component';
import { SiteInfoCreateComponent } from './view/site-info-create/site-info-create.component';

@NgModule({
  declarations: [
    OperatorComponent,
    MovieInfoListComponent,
    MovieInfoDetailComponent,
    MovieInfoCreateComponent,
    MovieInfoCreateComponent,
    PaginationComponent,
    TableComponent,
    SiteInfoListComponent,
    SiteInfoDetailComponent,
    ChargeInfoComponent,
    ChargeInfoCreatComponent,
    RoomDetailComponent,
    RoomCreateComponent,
    ChargeInfoListComponent,
    ChargeInfoDetailComponent,
    SiteInfoCreateComponent,
  ],
  imports: [
    CommonModule,
    OperatorRoutingModule,
    NgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class OperatorModule {}
