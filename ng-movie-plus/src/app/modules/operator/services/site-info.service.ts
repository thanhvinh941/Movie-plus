import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SiteInfo } from '../models/site-info';
import { AbstractPaginationResponse } from 'src/app/common/models/abstract-pagination-response';
import { Observable } from 'rxjs';
import { AbstractRegistResponse } from 'src/app/common/models/abstract_regist_response';

@Injectable({
  providedIn: 'root'
})
export class SiteInfoService {
  endpoint: string = 'http://localhost:8084/site/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) {}

  public getSiteInfoList(request : any) : Observable<AbstractPaginationResponse<SiteInfo>> {
    let api = `${this.endpoint}/getSiteInfoList`;
    return this.http.post<AbstractPaginationResponse<SiteInfo>>(api, request);
  }

  public entrySiteInfo(request : any)  : Observable<AbstractRegistResponse> {
    let api = `${this.endpoint}/entryMovieInfo`;
    return this.http.post<AbstractRegistResponse>(api, request);
  }

  public getSiteGradleList(request : any) : Observable<AbstractPaginationResponse<SiteInfo>> {
    let api = `${this.endpoint}/getSiteInfoList`;
    return this.http.post<AbstractPaginationResponse<SiteInfo>>(api, request);
  }

  public entrySiteGradle(request : any)  : Observable<AbstractRegistResponse> {
    let api = `${this.endpoint}/entryMovieInfo`;
    return this.http.post<AbstractRegistResponse>(api, request);
  }

  public getSeatGradleList(request : any) : Observable<AbstractPaginationResponse<SiteInfo>> {
    let api = `${this.endpoint}/getSiteInfoList`;
    return this.http.post<AbstractPaginationResponse<SiteInfo>>(api, request);
  }

  public entrySeatGradle(request : any)  : Observable<AbstractRegistResponse> {
    let api = `${this.endpoint}/entryMovieInfo`;
    return this.http.post<AbstractRegistResponse>(api, request);
  }
  
  public getSeatInfoList(request : any) : Observable<AbstractPaginationResponse<SiteInfo>> {
    let api = `${this.endpoint}/getSiteInfoList`;
    return this.http.post<AbstractPaginationResponse<SiteInfo>>(api, request);
  }

  public entrySeatInfo(request : any)  : Observable<AbstractRegistResponse> {
    let api = `${this.endpoint}/entryMovieInfo`;
    return this.http.post<AbstractRegistResponse>(api, request);
  }

  public getRoomInfoList(request : any) : Observable<AbstractPaginationResponse<SiteInfo>> {
    let api = `${this.endpoint}/getSiteInfoList`;
    return this.http.post<AbstractPaginationResponse<SiteInfo>>(api, request);
  }

  public entryRoomInfo(request : any)  : Observable<AbstractRegistResponse> {
    let api = `${this.endpoint}/entryMovieInfo`;
    return this.http.post<AbstractRegistResponse>(api, request);
  }

  public getShowTimeList(request : any) : Observable<AbstractPaginationResponse<SiteInfo>> {
    let api = `${this.endpoint}/getSiteInfoList`;
    return this.http.post<AbstractPaginationResponse<SiteInfo>>(api, request);
  }

  public entryShowTime(request : any)  : Observable<AbstractRegistResponse> {
    let api = `${this.endpoint}/entryMovieInfo`;
    return this.http.post<AbstractRegistResponse>(api, request);
  }
}
