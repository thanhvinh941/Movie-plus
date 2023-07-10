import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Movie } from 'src/app/common/data/movie';
import { MovieService } from '../../service/movie-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie-page',
  templateUrl: './movie-page.component.html',
  styleUrls: ['./movie-page.component.css']
})
export class MoviePageComponent implements OnInit{

  private movieEvent$ = new BehaviorSubject<Movie[] | null>(null);
  public movie$ = this.movieEvent$.asObservable();

  pageIndex$ : number = 1;
  pageSize$ : number = 50;
  total$ : number = 1234;

  constructor(private readonly _movieService : MovieService, private router: Router){}

  ngOnInit(): void {
    this._movieService.getAllMovie().subscribe(movies => {
      this.movieEvent$.next(movies);
    })
  }

}
