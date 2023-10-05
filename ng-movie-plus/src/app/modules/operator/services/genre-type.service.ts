import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AbstractPaginationResponse } from 'src/app/common/models/abstract-pagination-response';
import { MovieInfo } from '../models/movie-info';
import { AbstractRegistResponse } from 'src/app/common/models/abstract_regist_response';
import { AbstractListResponse } from 'src/app/common/models/abstact_list_reponse';
import { GenreType } from '../models/genre-type';

@Injectable({
  providedIn: 'root',
})
export class GenreTypeService {
  endpoint: string = 'http://localhost:8083/movie/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) {}

  getAllGenreType(): Observable<AbstractListResponse<GenreType>> {
    let api = `${this.endpoint}/getAllGenreType`;
    return this.http.post<AbstractListResponse<GenreType>>(api, {});
  }
}
