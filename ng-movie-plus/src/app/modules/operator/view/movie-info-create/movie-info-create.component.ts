import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  FormRecord,
  NonNullableFormBuilder,
  Validators,
} from '@angular/forms';
import {
  NzUploadChangeParam,
  NzUploadFile,
  NzUploadXHRArgs,
  UploadFileStatus,
} from 'ng-zorro-antd/upload';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Observable, Observer, filter, of, map } from 'rxjs';
import {
  HttpClient,
  HttpEvent,
  HttpEventType,
  HttpProgressEvent,
  HttpRequest,
  HttpResponse,
} from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import { GenreType } from '../../models/genre-type';
import { GenreTypeService } from '../../services/genre-type.service';
import { async } from '@angular/core/testing';
import { Router } from '@angular/router';
import { MovieInfoService } from '../../services/movie-info.service';
import { GenreTypeData } from '../../data/genre-type.data';
import { DynamicMasterEntityService } from '../../services/dynamic-master-entity.service';
import { MovieService } from 'src/app/common/config/endpoint.constants';

const getBase64 = (file: File): Promise<string | ArrayBuffer | null> =>
  new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = (error) => reject(error);
  });

@Component({
  selector: 'app-movie-info-create',
  templateUrl: './movie-info-create.component.html',
  styles: [
    `
      :host ::ng-deep .upload-list-inline .ant-upload-list-item {
        float: left;
        width: 200px;
        margin-right: 8px;
      }
      :host
        ::ng-deep
        .upload-list-inline
        [class*='-upload-list-rtl']
        .ant-upload-list-item {
        float: right;
      }
      :host ::ng-deep .upload-list-inline .ant-upload-animate-enter {
        animation-name: uploadAnimateInlineIn;
      }
      :host ::ng-deep .upload-list-inline .ant-upload-animate-leave {
        animation-name: uploadAnimateInlineOut;
      }
    `,
  ],
  styleUrls: ['./movie-info-create.component.css'],
})
export class MovieInfoCreateComponent implements OnInit, OnChanges {
  movieForm = this.fb.group({
    movieName: ['', [Validators.required]],
    movieSubName: ['', [Validators.required]],
    durationMin: ['', [Validators.required]],
    thumnail: ['', [Validators.required]],
    yearReleaseAt: ['', [Validators.required]],
    description: ['', [Validators.required]],
    banners: this.fb.array([], [Validators.required]),
    genreTypeIds: [[], [Validators.required]],
    trailers: this.fb.array(
      [
        this.fb.group({
          trailerUrl: ['', [Validators.required]],
          trailerTitle: ['', [Validators.required]],
        }),
      ],
      [Validators.required]
    ),
  });
  // movieForm!: FormGroup<{
  //   movieName: FormControl<string>;
  //   movieSubName: FormControl<string>;
  //   durationMin: FormControl<number>;
  //   thumnail: FormControl<string>;
  //   yearReleaseAt: FormControl<number>;
  //   description: FormControl<string>;
  //   banners: FormArray<FormControl<string>>;
  //   genreTypeIds: FormArray<FormControl<string>>;
  //   productionId: FormControl<string>;
  //   trailers: FormArray<
  //     FormGroup<{
  //       trailerUrl: FormControl<string>;
  //       trailerTitle: FormControl<string>;
  //     }>
  //   >;
  // }>;

  constructor(
    private _movieInfoService: MovieInfoService,
    private _genreTypeService: GenreTypeService,
    private _genreTypeData: GenreTypeData,
    private fb: FormBuilder,
    private msg: NzMessageService,
    private http: HttpClient,
    private _sanitizer: DomSanitizer,
    private router: Router,
    private _dynamicMasterEntity: DynamicMasterEntityService
  ) {}
  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.movieForm.value);
  }
  async ngOnInit(): Promise<void> {
    await this._dynamicMasterEntity
      .getDynamicMasterEntity(MovieService.END_POINT, {
        tableName: MovieService.TABLE.GENRE_TYPE,
        conditionStr: 'del_flg = 0',
        listFields: ['id', 'displayName'],
      })
      .then((res) => {
        this.genreType$ = res?.data;
      });
    console.log(this.genreType$);
    console.log(this.movieForm.value);
  }

  tabSelectIndex = 0;
  genreType$!: { id: string; displayName: string }[];
  bannerBase64List: NzUploadFile[] = [];
  thumnail!: any;
  thumnailBase64!: string;
  loading = false;

  submitForm() {
    let bannerBase64s: string[] = this.bannerBase64List.map((bannerBase64) => {
      return bannerBase64.thumbUrl!;
    });
    this.pushBanners(bannerBase64s);
    console.log(this.movieForm.value);
    this._movieInfoService.entryMovieInfo(this.movieForm.value).subscribe({
      next: (res) => {
        let movieId = res.data[0];
        this.router.navigate(['/operator/movie-infos', { targetId: movieId }]);
      },
      error: (err) => {
        this.msg.error(err);
      },
    });
  }

  safeURL(index: number) {
    return this._sanitizer.bypassSecurityTrustResourceUrl(
      this.getTrailerUrl(index).replace('watch?v=', 'embed/') + '&output=embed'
    );
  }

  addTrailer() {
    this.movieForm.controls.trailers.push(
      this.fb.group({ trailerUrl: '', trailerTitle: '' })
    );
  }

  get trailers(): FormArray<any> {
    return this.movieForm.controls.trailers;
  }

  getTrailerUrl(index: number): string {
    return this.movieForm.get('trailers')?.value.at(index)?.trailerUrl!;
  }

  setThumnail(thumnail: string): void {
    this.movieForm.setControl('thumnail', this.fb.control(thumnail));
  }

  get banners(): FormArray<any> {
    return this.movieForm.controls.banners;
  }

  pushBanners(banners: string[]): void {
    banners.forEach((banner) => {
      this.banners.push(this.fb.control(banner));
    });
  }

  beforeUploadThumnail = (file: NzUploadFile): boolean => {
    const myReader = new FileReader();
    myReader.readAsDataURL(file as any);
    myReader.onloadend = (e) => {
      this.setThumnail(myReader.result as string);
      this.thumnailBase64 = myReader.result as string;
    };
    return false;
  };

  beforeUploadBanner = (
    file: NzUploadFile,
    _fileList: NzUploadFile[]
  ): boolean => {
    const myReader = new FileReader();
    myReader.readAsDataURL(file as any);
    myReader.onloadend = (e) => {
      let thumbUrl: string = myReader.result as string;
      let bannerBase64: NzUploadFile = {
        thumbUrl: thumbUrl,
        status: 'success',
        filename: file.name!,
        uid: crypto.randomUUID(),
        name: file.name!,
      };
      this.bannerBase64List = [...this.bannerBase64List, bannerBase64];
    };
    return false;
  };
}
