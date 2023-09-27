import { AbstractAPIResponse } from './../../../shared/models/APIResponse';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, delay, throwError } from 'rxjs';
import { GetMovieInfoListResponse } from '../models/GetMovieInfoListResponse';
import { RetrieveMovieInfoResponse } from '../models/RetrieveMovieInfoResponse';

@Injectable({
  providedIn: 'root',
})
export class MovieInfoService {
  endpoint: string = 'http://localhost:8080/api/movie';
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) {}

  getMovieInfoList(
    genreTypeIds: string[] | [],
    searchTerm: string | ''
  ): Observable<GetMovieInfoListResponse> {
    let api = `${this.endpoint}/getMovieInfoList`;
    return this.http
      .post<GetMovieInfoListResponse>(api, { genreTypeIds, searchTerm })
      .pipe(catchError(this.handleError));
  }

  retrieveMovieInfo(
    movieId: string | ''
  ): Observable<RetrieveMovieInfoResponse> {
    let api = `${this.endpoint}/retrieveMovieInfo`;
    return this.http
      .post<RetrieveMovieInfoResponse>(api, { movieId })
      .pipe(catchError(this.handleError));
  }

  handleError(res: AbstractAPIResponse) {
    let msg = '';
    if (res.status == 0) {
      // client-side error
      msg = res.errors[0];
    }
    return throwError(msg);
  }
}
