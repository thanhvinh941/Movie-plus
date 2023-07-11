import { Injectable } from '@angular/core';
import { Observable, delay, filter, from, of, take } from 'rxjs';
import { Movie } from 'src/app/common/data/movie';
import { MovieDetail } from 'src/app/common/data/movie-detail';
import { movies } from 'src/app/common/service/movie.data';

@Injectable({
  providedIn: 'root',
})
export class MovieService {
  constructor() {}

  getMovieDetail(movieId: string | null): Observable<MovieDetail> {
    return from(movies).pipe(
      filter((movie) => movie.id == Number(movieId)),
      take(1),
      delay(300)
    );
  }

  getAllMovie(): Observable<Movie[]> {
    return of(movies).pipe(delay(500));
  }
}
