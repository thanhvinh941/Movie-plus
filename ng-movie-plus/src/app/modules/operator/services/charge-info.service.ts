import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ChargeService } from 'src/app/common/config/endpoint.constants';
import { AbstractListResponse } from 'src/app/common/models/abstact_list_reponse';

@Injectable({
  providedIn: 'root',
})
export class ChargeInfoService {
  constructor(private http: HttpClient) {}

  async selectChargeInfo(request: any): Promise<AbstractListResponse<any>> {
    const endpoint = ChargeService.END_POINT;
    let api = `${endpoint}/selectChargeInfo`;
    return await this.http
      .post<AbstractListResponse<any>>(api, request)
      .toPromise()
      .then((res) => res as AbstractListResponse<any>);
  }
}
