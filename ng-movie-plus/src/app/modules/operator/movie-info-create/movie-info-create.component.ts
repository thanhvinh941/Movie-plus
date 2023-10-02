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
import { NzUploadChangeParam, NzUploadFile } from 'ng-zorro-antd/upload';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Observable, Observer, filter, of } from 'rxjs';
import {
  HttpClient,
  HttpEvent,
  HttpEventType,
  HttpRequest,
  HttpResponse,
} from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import { GenreType } from '../models/genre-type';
import { GenreTypeService } from '../services/genre-type.service';

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
    banners: this.fb.array([''], [Validators.required]),
    genreTypeIds: this.fb.array([''], [Validators.required]),
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
    private _genreTypeService: GenreTypeService,
    private fb: FormBuilder,
    private msg: NzMessageService,
    private http: HttpClient,
    private _sanitizer: DomSanitizer
  ) {}
  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.movieForm.value);
  }
  ngOnInit(): void {
    // this._genreTypeService.getAllGenreType().subscribe(success=>{
    //   this.genreType$ = of(success.data);
    // })
    console.log(this.movieForm.value);
  }

  addBanners(file: string[]) {
    this.movieForm.addControl('banners', this.fb.array(file));
  }

  addThumnail(file: string) {
    this.movieForm.addControl('thumnail', this.fb.control(file));
  }

  addTrailer(e?: MouseEvent) {
    this.movieForm.addControl(
      'trailers',
      this.fb.array([
        this.fb.group({ trailerUrl: 'Great Book', trailerTitle: 'Great Book' }),
      ])
    );
  }

  genreType$!: Observable<GenreType[]>;
  index2 = 0;
  bannerFileList!: any[];
  thumnailFile!: any;
  loading = false;
  // validateForm!: FormGroup<{
  //   movieName: FormControl<string>;
  //   movieSubName: FormControl<string>;
  //   durationMin: FormControl<number>;
  //   thumnail: FormControl<string>;
  //   yearReleaseAt: FormControl<number>;
  //   description: FormControl<string>;
  //   banners: FormControl<string[]>;
  //   genreTypeIds: FormControl<string[]>;
  //   productionId: FormControl<string>;
  //   trailers: FormControl<{ trailerUrl: string; trailerTitle: string }[]>;
  // }>;
  // validateForm: FormRecord<FormControl<string>> = this.fb.record({});

  listOfControl: Array<{
    id: number;
    trailerTitle: string;
    trailerUrl: string;
  }> = [];

  submitForm() {
    console.log(this.movieForm.value);
  }

  handleFileInput($event: any) {
    let me =  this.movieForm;
    let file = $event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      console.log(reader.result);
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
    };
 }

  beforeUpload = (file: NzUploadFile): boolean => {
    this.bannerFileList = this.bannerFileList.concat(file);
    return false;
  };

  handleUpload(): void {
    const formData = new FormData();
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    this.bannerFileList.forEach((file: any) => {
      formData.append('files[]', file);
    });
    this.loading = true;
    // You can use any AJAX library you like
    const req = new HttpRequest('POST', 'localhos:8088/system/util/upload-file', formData, {
      // reportProgress: true
    });
    this.http
      .request(req)
      .pipe(filter(e => e instanceof HttpResponse))
      .subscribe(
        () => {
          this.loading = false;
          this.bannerFileList = [];
          this.msg.success('upload successfully.');
        },
        () => {
          this.loading = false;
          this.msg.error('upload failed.');
        }
      );
  }

  safeURL(index: number) {
    return this._sanitizer.bypassSecurityTrustResourceUrl(
      this.getTrailerUrl(index).replace('watch?v=', 'embed/') + '&output=embed'
    );
  }

  getTrailerUrl(index: number): string {
    return this.movieForm.get('trailers')?.value.at(index)?.trailerUrl!;
  }
}
