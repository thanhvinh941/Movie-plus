import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OperatorComponent } from './modules/operator/pages';

const routes: Routes = [
  {
    path: 'operator',
    component: OperatorComponent,
    loadChildren: () =>
      import('./modules/operator/operator.module').then(
        (m) => m.OperatorModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
