import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MemberRoutingModule } from './member-routing.module';
import { MemberComponent } from './member.component';
import { GenreTypesComponent } from './page/genre-types/genre-types.component';
import { MovieSearchComponent } from './page/movie-search/movie-search.component';
import { MovieDetailComponent } from './page/movie-detail/movie-detail.component';
import { ShowTimeComponent } from './page/show-time/show-time.component';
import { SiteInfoComponent } from './page/site-info/site-info.component';
import { BookingComponent } from './page/booking/booking.component';
import { NgZorroAntdModule } from 'src/app/common/ng-zorro-antd.module';

@NgModule({
  declarations: [
    GenreTypesComponent,
    MemberComponent,
    MovieSearchComponent,
    MovieDetailComponent,
    ShowTimeComponent,
    SiteInfoComponent,
    BookingComponent,
  ],
  imports: [CommonModule, MemberRoutingModule, NgZorroAntdModule],
  bootstrap: [MemberComponent],
})
export class MemberModule {}
