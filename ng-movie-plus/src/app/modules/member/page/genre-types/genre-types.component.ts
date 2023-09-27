import { GenreType } from './../../models/dto/GenreTypeDto';
import { Component, OnInit } from '@angular/core';
import { Observable, delay, map } from 'rxjs';
import { MovieInfoService } from '../../services/movie-info.service';

@Component({
  selector: 'app-genre-types',
  templateUrl: './genre-types.component.html',
  styleUrls: ['./genre-types.component.css'],
})
export class GenreTypesComponent implements OnInit {
  constructor(private readonly _movieService: MovieInfoService) {}
  ngOnInit(): void {
    console.log('GenreTypesComponent OnInit');
    this.genreTypes$ = this._movieService.getMovieInfoList([], '').pipe(
      map((res) => {
        console.log(res);
        return res.data.genreTypes;
      })
    );
  }

  genreTypes$!: Observable<GenreType[]>;
}
