import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OperatorComponent } from './operator.component';
import { MovieInfoCreateComponent } from './view/movie-info-create/movie-info-create.component';
import { MovieInfoListComponent } from './view/movie-info-list/movie-info-list.component';
import { ChargeInfoCreatComponent } from './view/charge-info-creat/charge-info-creat.component';

const routes: Routes = [
  {
    path: 'operator',
    children: [
      { path: '', redirectTo: '/operator/home', pathMatch: 'full' },
      {
        path: 'movie-infos/create',
        component: MovieInfoCreateComponent,
      },
      {
        path: 'movie-infos',
        component: MovieInfoListComponent,
      },
      {
        path: 'charge-infos/create',
        component: ChargeInfoCreatComponent,
      },
      {
        path: 'charge-infos',
        component: ChargeInfoCreatComponent,
      },
      {
        path: 'site-infos/create',
        component: ChargeInfoCreatComponent,
      },
      {
        path: 'site-infos',
        component: ChargeInfoCreatComponent,
      },
      {
        path: 'seat-infos',
        component: ChargeInfoCreatComponent,
      },
      {
        path: 'seat-infos/create',
        component: ChargeInfoCreatComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OperatorRoutingModule {}
