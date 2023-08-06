import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, delay, first, map, of, take } from 'rxjs';
import { Movie } from 'src/app/common/data/movie-list';
import { MovieDetail } from 'src/app/common/data/movie-detail';
import { movies } from 'src/app/common/service/movie.data';

class APIResponse<T> {
  data!: T | [];
  status!: number;
  errors!: string[];
}

// interface RetrieveMovieInfoRequest {
//   movieId: String;
// }

@Injectable({
  providedIn: 'root',
})
export class MovieService {
  constructor(private http: HttpClient) {}

  getMovieDetail(movieId: String): Observable<MovieDetail[]> {
    return this.http
      .post<APIResponse<MovieDetail[]>>(
        'http://localhost:8080/api/movie/retrieveMovieInfo',
        {
          movieId: movieId,
        }
      )
      .pipe(
        map((data) => {
          return data?.data;
        })
      );
  }

  getAllMovie(): Observable<Movie[]> {
    return of(movies).pipe(delay(500));
  }
}
