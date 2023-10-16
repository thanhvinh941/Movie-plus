import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractListResponse } from 'src/app/common/models/abstact_list_reponse';
import { AbstractRegistResponse } from 'src/app/common/models/abstract_regist_response';

@Injectable({
  providedIn: 'root',
})
export class RoomInfoService {
  endpoint: string = 'http://localhost:8084/site/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) {}

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
}
