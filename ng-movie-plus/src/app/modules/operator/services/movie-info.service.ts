import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AbstractPaginationResponse } from 'src/app/common/models/abstract-pagination-response';
import { MovieInfo } from '../models/movie-info';
import { AbstractRegistResponse } from 'src/app/common/models/abstract_regist_response';
import { AbstractListResponse } from 'src/app/common/models/abstact_list_reponse';

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

  public entryMovieInfo(request : any)  : Observable<AbstractRegistResponse> {
    let api = `${this.endpoint}/entryMovieInfo`;
    return this.http.post<AbstractRegistResponse>(api, request);
  }

  public getGenreTypeList(request : any) : Observable<AbstractPaginationResponse<MovieInfo>> {
    let api = `${this.endpoint}/getGenreTypeList`;
    return this.http.post<AbstractPaginationResponse<MovieInfo>>(api, request);
  }

  public entryGenreType(request : any)  : Observable<AbstractRegistResponse> {
    let api = `${this.endpoint}/entryGenreType`;
    return this.http.post<AbstractRegistResponse>(api, request);
  }

  public fullTextSearchMovie(request : any) : Observable<AbstractListResponse<any>>{
    let api = `${this.endpoint}/fullTextSearchMovie`;
    return this.http.post<AbstractListResponse<any>>(api, request);
  }
}
