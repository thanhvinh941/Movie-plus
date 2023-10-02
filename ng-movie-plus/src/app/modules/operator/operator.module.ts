import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OperatorRoutingModule } from './operator-routing.module';
import { OperatorComponent } from './operator.component';
import { MovieInfoListComponent } from './movie-info-list/movie-info-list.component';
import { MovieInfoDetailComponent } from './movie-info-detail/movie-info-detail.component';
import { MovieInfoCreateComponent } from './movie-info-create/movie-info-create.component';
import { NgZorroAntdModule } from 'src/app/common/ng-zorro-antd.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PaginationComponent } from 'src/app/common/layout/pagination/pagination.component';
import { TableComponent } from 'src/app/common/layout/table/table.component';
import { SiteInfoListComponent } from './site-info-list/site-info-list.component';
import { SiteInfoDetailComponent } from './site-info-detail/site-info-detail.component';

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
