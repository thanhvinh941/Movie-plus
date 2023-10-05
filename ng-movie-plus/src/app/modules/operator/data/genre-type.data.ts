import { Injectable } from '@angular/core';
import { MovieInfoService } from '../services/movie-info.service';
import { AbstractPaginationResponse } from 'src/app/common/models/abstract-pagination-response';
import { MovieInfo } from '../models/movie-info';
import { Observable, map, throwError } from 'rxjs';
import { AbstractPaginationData } from 'src/app/common/models/abstract_pagination_data';
import { GenreTypeService } from '../services/genre-type.service';
import { AbstractListResponse } from 'src/app/common/models/abstact_list_reponse';
import { GenreType } from '../models/genre-type';

const initAbstractPaginationData: AbstractPaginationData<any> = {
  page: 0,
  pageSize: 0,
  totalPages: 0,
  totalRecords: 0,
  records: [],
};

@Injectable()
export class GenreTypeData {
  allGenreType: AbstractListResponse<GenreType> =
    new AbstractListResponse<GenreType>();
  constructor(private service: GenreTypeService) {}

  getAllGenreType(): Observable<GenreType[]> {
    return this.service.getAllGenreType().pipe(
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
