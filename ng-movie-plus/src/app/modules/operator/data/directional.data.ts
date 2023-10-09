import { Injectable } from '@angular/core';
import { DirectionalService } from '../services/directional.service';
import { AbstractDirectionRequest } from 'src/app/common/models/abstact_direction_request';
import { Observable, map, throwError } from 'rxjs';

@Injectable()
export class DirectionalData {
  constructor(private service: DirectionalService) {}

  getDirectionalValue(
    endpoint: string,
    tableName: string,
    limit: number,
    offset: number,
    conditionStr: string,
    listFields: string[],
    isForUpdate: boolean | false,
    orderBys: { [key: string]: string } | {}
  ): Observable<any> {
    type targetType = (typeof listFields)[any];
    return this.service
      .getDirectionalValue<targetType>(endpoint, {
        tableName,
        limit,
        offset,
        conditionStr,
        listFields,
        isForUpdate,
        orderBys,
      })
      .pipe(
        map((res) => {
          if (res.status == 0) {
            throw throwError(res);
          } else {
            return res.data;
          }
        })
      );
  }
}
