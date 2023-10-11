import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { MovieInfoData } from '../../data/movie-info.data';
import { MovieInfo } from '../../models/movie-info';
import { Observable, from, map, of, tap } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

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
  constructor(
    private movieInfoData: MovieInfoData,
    private route: ActivatedRoute
  ) {}

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
    const targetId: string = this.route.snapshot.params['targetId'];
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

    let orderBys: { [key: string]: string } = {};
    if (targetId != null) {
      let keyOrder = "FIELD(ID,'" + targetId + "')";
      orderBys = { [keyOrder]: 'DESC' };
    }

    this.movieInfoData
      .getMovieInfoList({
        page: 0,
        pageSize: 50,
        ignoreDelFlg: false,
        searchTerm: '',
        orderBys: orderBys,
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
