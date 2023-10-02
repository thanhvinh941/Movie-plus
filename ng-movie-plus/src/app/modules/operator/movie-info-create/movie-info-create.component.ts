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
import { Observable, Observer } from 'rxjs';
import {
  HttpClient,
  HttpEvent,
  HttpEventType,
  HttpRequest,
  HttpResponse,
} from '@angular/common/http';

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
    banners: this.fb.array(['']),
    genreTypeIds: this.fb.array(['']),
    trailers: this.fb.array([
      this.fb.group({
        trailerUrl: ['1', [Validators.required]],
        trailerTitle: ['1', [Validators.required]],
      }),
      this.fb.group({
        trailerUrl: ['2', [Validators.required]],
        trailerTitle: ['2', [Validators.required]],
      }),
    ]),
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
    private fb: FormBuilder,
    private msg: NzMessageService,
    private http: HttpClient
  ) {}
  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.movieForm.value);
  }
  ngOnInit(): void {
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
  index2 = 0;
  bannerFileList!: any;
  thumnailFile!: any;
  loading = false;
  validateForm!: FormGroup<{
    movieName: FormControl<string>;
    movieSubName: FormControl<string>;
    durationMin: FormControl<number>;
    thumnail: FormControl<string>;
    yearReleaseAt: FormControl<number>;
    description: FormControl<string>;
    banners: FormControl<string[]>;
    genreTypeIds: FormControl<string[]>;
    productionId: FormControl<string>;
    trailers: FormControl<{ trailerUrl: string; trailerTitle: string }[]>;
  }>;
  // validateForm: FormRecord<FormControl<string>> = this.fb.record({});

  listOfControl: Array<{
    id: number;
    trailerTitle: string;
    trailerUrl: string;
  }> = [];

  submitForm() {
    console.log(this.movieForm.value);
  }

  beforeUpload = (
    file: NzUploadFile,
    _fileList: NzUploadFile[]
  ): Observable<boolean> =>
    new Observable((observer: Observer<boolean>) => {
      const isJpgOrPng =
        file.type === 'image/jpeg' || file.type === 'image/png';
      if (!isJpgOrPng) {
        this.msg.error('You can only upload JPG file!');
        observer.complete();
        return;
      }
      const isLt2M = file.size! / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.msg.error('Image must smaller than 2MB!');
        observer.complete();
        return;
      }
      observer.next(isJpgOrPng && isLt2M);
      observer.complete();
    });

  private getBase64(img: File, callback: (img: string) => void): void {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result!.toString()));
    reader.readAsDataURL(img);
  }

  handleChange(info: { file: NzUploadFile }): void {
    console.log(info);

    switch (info.file.status) {
      case 'uploading':
        this.loading = true;
        break;
      case 'done':
        // Get this url from response in real world.
        this.getBase64(info.file!.originFileObj!, (img: string) => {
          this.loading = false;
          // this.bannerFileList = this.bannerFileList.concat(img);
        });
        break;

      case 'removed':
        break;
      case 'error':
        this.loading = false;
        break;
    }
  }

  getGroupAt(index: number) {
    console.log(this.movieForm.get('trailers')?.value.at(index));
    return this.movieForm
      .get('trailers')
      ?.value.at(index) as unknown as FormGroup;
  }

  setMediaUploadHeaders = (file: NzUploadFile) => {
    return {
      'Content-Type': 'multipart/form-data',
      Accept: 'application/json',
    };
  };
  customUploadReq = (item: any) => {
    const formData = new FormData();
    this.getBase64(item.file!.originFileObj!, (img: string) => {
      formData.append('fileBase64', img); // tslint:disable-next-line:no-any
    });
    ///formData.append('id', '1000');
    const req = new HttpRequest('POST', item.action, formData, {
      reportProgress: true,
      withCredentials: false,
    });
    // Always return a `Subscription` object, nz-upload will automatically unsubscribe at the appropriate time
    return this.http.request(req).subscribe(
      (event) => {
        if (event.type === HttpEventType.UploadProgress) {
          // if (event.total > 0) {
          //   (event as any).percent = event.loaded / event.total * 100; // tslint:disable-next-line:no-any
          // }
          // To process the upload progress bar, you must specify the `percent` attribute to indicate progress.
          item.onProgress(event, item.file);
        } else if (event instanceof HttpResponse) {
          /* success */
          item.onSuccess(event.body, item.file, event);
        }
      },
      (err) => {
        /* error */
        item.onError(err, item.file);
      }
    );
  };
}
