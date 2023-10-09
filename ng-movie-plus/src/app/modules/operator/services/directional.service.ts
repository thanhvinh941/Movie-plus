import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AbstractDirectionRequest } from 'src/app/common/models/abstact_direction_request';
import { AbstractListResponse } from 'src/app/common/models/abstact_list_reponse';

@Injectable({
  providedIn: 'root',
})
export class DirectionalService {
  constructor(private http: HttpClient) {}

  getDirectionalValue<Type>(
    endpoint: string,
    request: AbstractDirectionRequest
  ): Observable<AbstractListResponse<Type>> {
    let api = `${endpoint}/getDirectionalValue`;
    return this.http.post<AbstractListResponse<Type>>(api, request);
  }
}
