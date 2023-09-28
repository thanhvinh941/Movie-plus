import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AbstractPaginationResponse } from 'src/app/common/models/abstract-pagination-response';
import { MovieInfo } from '../models/movie-info';

@Injectable({
  providedIn: 'root'
})
export class MovieInfoService {
  endpoint: string = 'http://localhost:8083/movie/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) {}

  public getMovieInfoList(request : any) : Observable<AbstractPaginationResponse<MovieInfo>> {
    let api = `${this.endpoint}/GetMovieInfoList`;
    return this.http.post<AbstractPaginationResponse<MovieInfo>>(api, request);
  }
}
