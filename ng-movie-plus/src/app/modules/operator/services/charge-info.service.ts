import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChargeService } from 'src/app/common/config/endpoint.constants';
import { AbstractListResponse } from 'src/app/common/models/abstact_list_reponse';
import { AbstractPaginationResponse } from 'src/app/common/models/abstract-pagination-response';
import { AbstractPaginationData } from 'src/app/common/models/abstract_pagination_data';
import { AbstractRegistResponse } from 'src/app/common/models/abstract_regist_response';

@Injectable({
  providedIn: 'root',
})
export class ChargeInfoService {
  constructor(private http: HttpClient) {}
  readonly endpoint = ChargeService.END_POINT;

  async selectChargeInfo(request: any): Promise<AbstractListResponse<any>> {
    let api = `${this.endpoint}/selectChargeInfo`;
    return await this.http
      .post<AbstractListResponse<any>>(api, request)
      .toPromise()
      .then((res) => res as AbstractListResponse<any>);
  }

  settingChargeInfo(request: any): Observable<AbstractRegistResponse> {
    let api = `${this.endpoint}/settingChargeInfo`;
    return this.http.post<AbstractRegistResponse>(api, request);
  }
}
