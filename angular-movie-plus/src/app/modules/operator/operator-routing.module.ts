import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {
  ChargePlanListComponent,
  DashboardComponent,
  GenreTypeListComponent,
  MemberDetailComponent,
  MemberListComponent,
  MovieGradleListComponent,
  MovieListComponent,
  SeatGradleListComponent,
  SiteGradleListComponent,
  SiteListComponent,
} from './pages';

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'dashboard', component: DashboardComponent },
  {
    path: 'site-info',
    children: [
      { path: '', component: SiteListComponent },
      { path: ':id', component: SiteListComponent },
    ],
  },
  {
    path: 'site-gradle',
    children: [
      { path: '', component: SiteGradleListComponent },
      { path: ':id', component: SiteListComponent },
    ],
  },
  {
    path: 'seat-gradle',
    children: [
      { path: '', component: SeatGradleListComponent },
      { path: ':id', component: SiteListComponent },
    ],
  },
  {
    path: 'movie-info',
    children: [
      { path: '', component: MovieListComponent },
      { path: ':id', component: SiteListComponent },
    ],
  },
  {
    path: 'movie-gradle',
    children: [
      { path: '', component: MovieGradleListComponent },
      { path: ':id', component: SiteListComponent },
    ],
  },
  {
    path: 'genre-type',
    children: [
      { path: '', component: GenreTypeListComponent },
      { path: ':id', component: SiteListComponent },
    ],
  },
  {
    path: 'charge-plan',
    children: [
      { path: '', component: ChargePlanListComponent },
      { path: ':id', component: SiteListComponent },
    ],
  },
  {
    path: 'member-info',
    children: [
      { path: '', component: MemberListComponent },
      { path: ':id', component: MemberDetailComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OperatorRoutingModule {}
