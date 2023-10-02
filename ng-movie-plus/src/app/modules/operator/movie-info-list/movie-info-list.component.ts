import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { MovieInfoListData } from '../data/movie-info-list.data';
import { MovieInfo } from '../models/movie-info';
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
  columnData$!: { lable: string; isImage: boolean; style: string }[];
  startEdit(id: string): void {
    this.editCache[id].edit = true;
  }

  ngOnInit(): void {
    this.columnData$ = [
      { lable: 'id', isImage: false, style: '' },
      { lable: 'movieName', isImage: false, style: '' },
      { lable: 'movieSubName', isImage: false, style: '' },
      { lable: 'durationMin', isImage: false, style: '' },
      { lable: 'description', isImage: false, style: '' },
      { lable: 'thumnail', isImage: false, style: '' },
      { lable: 'productionId', isImage: false, style: '' },
      { lable: 'yearReleaseAt', isImage: false, style: '' },
      { lable: 'registTime', isImage: false, style: '' },
      { lable: 'updateTime', isImage: false, style: '' },
      { lable: 'updateUser', isImage: false, style: '' },
      { lable: 'delFlg', isImage: false, style: '' },
    ];

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
    console.log(this.movieInfoData);
  }
}
