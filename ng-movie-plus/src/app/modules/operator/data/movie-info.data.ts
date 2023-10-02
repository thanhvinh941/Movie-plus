import { Injectable } from '@angular/core';
import { MovieInfoService } from '../services/movie-info.service';
import { AbstractPaginationResponse } from 'src/app/common/models/abstract-pagination-response';
import { MovieInfo } from '../models/movie-info';
import { Observable, map, throwError } from 'rxjs';
import { AbstractPaginationData } from 'src/app/common/models/abstract_pagination_data';

const initAbstractPaginationData: AbstractPaginationData<any> = {
  page: 0,
  pageSize: 0,
  totalPages: 0,
  totalRecords: 0,
  records: [],
};

@Injectable()
export class MovieInfoData {
  movieInfoListData: AbstractPaginationData<MovieInfo> = new AbstractPaginationData<MovieInfo>();
  constructor(private service: MovieInfoService) {}

  getMovieInfoList(request: {
    page: number;
    pageSize: number;
    ignoreDelFlg: boolean | false;
    searchTerm: string;
    orderBys: { [key: string]: string };
  }) : Observable<AbstractPaginationData<MovieInfo>> {
    return this.service.getMovieInfoList(request).pipe(map(res=> {
        if(res.status == 0){
            throw throwError(res); 
        }else {
            return res.data
        }
    }));
  }
}
