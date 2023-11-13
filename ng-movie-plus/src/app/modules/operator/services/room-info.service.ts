import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Observer } from 'rxjs';
import { AbstractListResponse } from 'src/app/common/models/abstact_list_reponse';
import { AbstractPaginationResponse } from 'src/app/common/models/abstract-pagination-response';
import { AbstractObjectResponse } from 'src/app/common/models/abstract_object_response';
import { AbstractRegistResponse } from 'src/app/common/models/abstract_regist_response';

@Injectable({
  providedIn: 'root',
})
export class RoomInfoService {
  endpoint: string = 'http://localhost:8084/site/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) {}

  public getShowTimeOfRoom(request : any): Observable<AbstractPaginationResponse<any>>{
    let api = `${this.endpoint}/getShowTimeOfRoom`;
    return this.http.post<AbstractPaginationResponse<any>>(api, request)
  }

  public getSeatInRoom(request : any): Observable<AbstractObjectResponse>{
    let api = `${this.endpoint}/getSeatInRoom`;
    return this.http.post<AbstractObjectResponse>(api, request)
  }

  public getRoomInfoDetail(request: any): Promise<any> {
    let api = `${this.endpoint}/getRoomInfoDetail`;
    return this.http
      .post<AbstractListResponse<any>>(api, request)
      .toPromise()
      .then((res) => res);
  }

  public settingRoomSeat(request: any): Promise<AbstractRegistResponse> {
    let api = `${this.endpoint}/settingRoomSeat`;
    return this.http
      .post<AbstractRegistResponse>(api, request)
      .toPromise()
      .then((res) => res as AbstractRegistResponse);
  }

  public settingShowTime(request: any): Promise<AbstractRegistResponse> {
    let api = `${this.endpoint}/settingShowTime`;
    return this.http
      .post<AbstractRegistResponse>(api, request)
      .toPromise()
      .then((res) => res as AbstractRegistResponse);
  }
}
