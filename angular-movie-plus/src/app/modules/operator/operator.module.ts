import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OperatorRoutingModule } from './operator-routing.module';
import {
  DashboardComponent,
  SiteListComponent,
  MovieListComponent,
  MemberListComponent,
  GenreTypeListComponent,
  MovieGradleListComponent,
  SeatGradleListComponent,
  SiteGradleListComponent,
  ChargePlanListComponent,
  OperatorComponent,
  SiteDetailComponent,
  MovieDetailComponent,
  ChargePlanDetailComponent,
  GenreTypeDetailComponent,
  MemberDetailComponent,
  MovieGradleDetailComponent,
  SeatGradleDetailComponent,
  SiteGradleDetailComponent,
} from './pages';
import { SharedModule } from 'src/app/shared';

@NgModule({
  declarations: [
    DashboardComponent,
    SiteListComponent,
    MovieListComponent,
    MemberListComponent,
    GenreTypeListComponent,
    MovieGradleListComponent,
    SeatGradleListComponent,
    SiteGradleListComponent,
    ChargePlanListComponent,
    OperatorComponent,
    SiteDetailComponent,
    MovieDetailComponent,
    MemberDetailComponent,
    GenreTypeDetailComponent,
    MovieGradleDetailComponent,
    SeatGradleDetailComponent,
    SiteGradleDetailComponent,
    ChargePlanDetailComponent,
  ],
  imports: [SharedModule, OperatorRoutingModule],
})
export class OperatorModule {}
