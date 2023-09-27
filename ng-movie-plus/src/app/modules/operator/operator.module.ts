import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OperatorRoutingModule } from './operator-routing.module';
import { OperatorComponent } from './operator.component';
import { MovieInfoListComponent } from './movie-info-list/movie-info-list.component';
import { MovieInfoDetailComponent } from './movie-info-detail/movie-info-detail.component';
import { MovieInfoCreateComponent } from './movie-info-create/movie-info-create.component';
import { NgZorroAntdModule } from 'src/app/common/ng-zorro-antd.module';

@NgModule({
  declarations: [OperatorComponent, MovieInfoListComponent, MovieInfoDetailComponent, MovieInfoCreateComponent, MovieInfoCreateComponent],
  imports: [CommonModule, OperatorRoutingModule, NgZorroAntdModule],
})
export class OperatorModule {}
