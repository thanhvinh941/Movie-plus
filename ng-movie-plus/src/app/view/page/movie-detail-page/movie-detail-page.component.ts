import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, first, map, pluck, switchMap } from 'rxjs';
import { MovieService } from '../../service/movie-service.service';
import { BreadcrumbService } from '../../service/breadcrumb.service';
import { MovieDetail } from 'src/app/common/data/movie-detail';

@Component({
  selector: 'app-movie-detail-page',
  templateUrl: './movie-detail-page.component.html',
  styleUrls: ['./movie-detail-page.component.css'],
})
export class MovieDetailPageComponent {
  movie$!: Observable<MovieDetail[]>;
  effect = 'scrollx';
  constructor(
    private _route: ActivatedRoute,
    private readonly _movieService: MovieService,
    private _breadcrumbService: BreadcrumbService
  ) {}

  ngOnInit(): void {
    console.log('MoviePageComponent ngOnInit');
    this.movie$ = this._route.paramMap.pipe(
      map((params) => params.get('movieId')),
      switchMap((movieId) => this._movieService.getMovieDetail(movieId!))
    );
    console.log(this.movie$);
  }
}
