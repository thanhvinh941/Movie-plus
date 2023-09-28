import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OperatorComponent } from './operator.component';
import { MovieInfoCreateComponent } from './movie-info-create/movie-info-create.component';
import { MovieInfoListComponent } from './movie-info-list/movie-info-list.component';

const routes: Routes = [{ path: 'operator', children:[
  { path: '', redirectTo: '/operator/home', pathMatch: 'full' },
  {
    path: 'movie-infos/create',
    component: MovieInfoCreateComponent,
  },{
    path: 'movie-infos',
    component: MovieInfoListComponent,
  }
      
] }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OperatorRoutingModule {}
