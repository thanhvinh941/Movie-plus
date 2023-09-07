import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable } from 'rxjs';
import { Movie } from 'src/app/common/data/movie-list';
import { MovieService } from './movie-service.service';

@Injectable({
  providedIn: 'root',
})
export class MovieResolverService implements Resolve<Movie[] | undefined> {
  constructor(private readonly _movieService: MovieService) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Movie[] | undefined> {
    const id = route.params['movieId'];
    return this._movieService.getMovieDetail(id);
  }
}
