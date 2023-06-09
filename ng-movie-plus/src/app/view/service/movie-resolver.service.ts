import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { Movie } from 'src/app/common/data/movie';
import { MovieService } from './movie-service.service';

@Injectable({
  providedIn: 'root'
})
export class MovieResolverService implements Resolve<Movie> {

  constructor(private readonly _movieService : MovieService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Movie>{
    const id = route.params['movieId'];
    return this._movieService.getMovie(id);
  }
}
