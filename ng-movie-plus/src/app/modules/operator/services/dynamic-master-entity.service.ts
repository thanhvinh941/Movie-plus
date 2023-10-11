import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, retry, throwError, pluck, map } from 'rxjs';
import { AbstractDynamicRequest } from 'src/app/common/models/abstact_dynamic_request';
import { AbstractListResponse } from 'src/app/common/models/abstact_list_reponse';

@Injectable({
  providedIn: 'root',
})
export class DynamicMasterEntityService {
  constructor(private http: HttpClient) {}

  async getDynamicMasterEntity(endpoint: string, request: any): Promise<any> {
    let api = `${endpoint}/getDynamicMasterEntity`;
    return await this.http
      .post<AbstractListResponse<any>>(api, request)
      .toPromise()
      .then((res) => res);
  }
}
