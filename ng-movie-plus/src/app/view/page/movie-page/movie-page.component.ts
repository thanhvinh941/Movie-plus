import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Movie } from 'src/app/common/data/movie';
import { MovieService } from '../../service/movie-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie-page',
  templateUrl: './movie-page.component.html',
  styleUrls: ['./movie-page.component.css']
})
export class MoviePageComponent implements OnInit{
  movie$!: Observable<Movie[]>;

  constructor(private readonly _movieService : MovieService, private router: Router){}

  ngOnInit(): void {
    this.movie$ = this._movieService.getAllMovie();
  }

}
