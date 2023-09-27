import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MemberComponent } from './member.component';
import { HomeComponent } from './page/home/home.component';
import { GenreTypesComponent } from './page/genre-types/genre-types.component';
import { MovieDetailComponent } from './page/movie-detail/movie-detail.component';
import { MovieSearchComponent } from './page/movie-search/movie-search.component';
import { ShowTimeComponent } from './page/show-time/show-time.component';
import { SiteInfoComponent } from './page/site-info/site-info.component';

const routes: Routes = [
  {
    path: 'member',
    children: [
      { path: '', redirectTo: '/member/home', pathMatch: 'full' },
      {
        path: 'home',
        component: HomeComponent,
      },
      {
        path: 'genre-type',
        component: GenreTypesComponent,
      },
      {
        path: 'movie-search',
        component: MovieSearchComponent,
      },
      {
        path: 'movie-detail/:movieId',
        component: MovieDetailComponent,
      },
      {
        path: 'showtime/:showtimeId',
        component: ShowTimeComponent,
      },
      {
        path: 'site/:siteId',
        component: SiteInfoComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MemberRoutingModule {}
