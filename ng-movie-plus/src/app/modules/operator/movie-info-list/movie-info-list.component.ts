import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { MovieInfoListData } from '../data/movie-info-list.data';
import { MovieInfo } from '../models/movie-info';
import { async } from '@angular/core/testing';
import { Observable, from, map, of, tap } from 'rxjs';

@Component({
  selector: 'app-movie-info-list',
  templateUrl: './movie-info-list.component.html',
  styles: [
    `
      .save {
        margin-right: 8px;
      }
    `,
  ],
  styleUrls: ['./movie-info-list.component.css'],
})
export class MovieInfoListComponent implements OnInit {
  constructor(private movieInfoData: MovieInfoListData) {}

  pagination!: {
    page: number;
    pageSize: number;
    totalPages: number;
    totalRecords: number;
  };

  editCache: { [key: string]: { edit: boolean; data: MovieInfo } } = {};
  listOfData$!: Observable<MovieInfo[]>;

  startEdit(id: string): void {
    this.editCache[id].edit = true;
  }

  ngOnInit(): void {
    this.movieInfoData
      .getMovieInfoList({
        page: 0,
        pageSize: 50,
        ignoreDelFlg: false,
        searchTerm: '',
        orderBys: {},
      })
      .subscribe((success) => {
        this.listOfData$ = of(success.records);
        success.records.forEach((item) => {
          this.editCache[item.id] = {
            edit: false,
            data: { ...item },
          };
        });
        this.pagination = { ...success };
      });
  }
}
