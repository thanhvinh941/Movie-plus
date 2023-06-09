import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, map, pluck, switchMap } from 'rxjs';
import { Movie } from 'src/app/common/data/movie';
import { MovieService } from '../../service/movie-service.service';
import { BreadcrumbService } from '../../service/breadcrumb.service';

@Component({
  selector: 'app-movie-detail-page',
  templateUrl: './movie-detail-page.component.html',
  styleUrls: ['./movie-detail-page.component.css']
})
export class MovieDetailPageComponent {
  movie$ !: Observable<Movie>;

  constructor(private _route: ActivatedRoute, private readonly _movieService: MovieService, private _breadcrumbService: BreadcrumbService){}

  ngOnInit(): void {
    this.movie$ =  this._route.paramMap.pipe(
      map((params) => params.get('movieId')),
      switchMap((movieId) => this._movieService.getMovie(movieId))
    );
  }
}
