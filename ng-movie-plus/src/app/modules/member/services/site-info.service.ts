import { AbstractAPIResponse } from './../../../shared/models/APIResponse';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { RetrieveMovieInfoResponse } from '../models/RetrieveMovieInfoResponse';
import { RetrieveShowTimeResponse } from '../models/RetrieveShowTimeResponse';

@Injectable({
  providedIn: 'root',
})
export class SiteInfoService {
  endpoint: string = 'http://localhost:4000/api/site';
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) {}

  retrieveSiteInfo(siteId: string | ''): Observable<RetrieveMovieInfoResponse> {
    let api = `${this.endpoint}/getMovieInfoList`;
    return this.http
      .post<RetrieveMovieInfoResponse>(api, { siteId })
      .pipe(catchError(this.handleError));
  }

  retriveShowTime(
    showTimeId: string | ''
  ): Observable<RetrieveShowTimeResponse> {
    let api = `${this.endpoint}/retrieveShowTime`;
    return this.http
      .post<RetrieveShowTimeResponse>(api, { showTimeId })
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
