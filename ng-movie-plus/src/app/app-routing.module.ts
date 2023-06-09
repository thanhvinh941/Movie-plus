import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TicketPageComponent } from './view/page/ticket-page/ticket-page.component';
import { HomePageComponent } from './view/page/home-page/home-page.component';
import { MoviePageComponent } from './view/page/movie-page/movie-page.component';
import { MovieDetailPageComponent } from './view/page/movie-detail-page/movie-detail-page.component';
import { MovieResolverService } from './view/service/movie-resolver.service';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {
    path: 'movie',
    data: { breadcrumb: 'Movie' },
    children: [
      {
        path: '',
        component: MoviePageComponent,
        data: { breadcrumb: '' },
      },
      {
        path: ':movieId',
        component: MovieDetailPageComponent,
        data: { breadcrumb: (data: any) => `${data.movie.name}` },
        resolve: { movie: MovieResolverService },
      },
    ],
  },
  { path: 'home', component: HomePageComponent, data: { breadcrumb: 'Home' } },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
