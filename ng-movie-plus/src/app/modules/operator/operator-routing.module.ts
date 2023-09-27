import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OperatorComponent } from './operator.component';
import { MovieInfoCreateComponent } from './movie-info-create/movie-info-create.component';

const routes: Routes = [{ path: 'operator', children:[
  { path: '', redirectTo: '/operator/home', pathMatch: 'full' },
  {
    path: 'movie-info/create',
    component: MovieInfoCreateComponent,
  }
      
] }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OperatorRoutingModule {}
