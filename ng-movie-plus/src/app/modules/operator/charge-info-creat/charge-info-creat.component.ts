import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormControl,
} from '@angular/forms';

@Component({
  selector: 'app-charge-info-creat',
  templateUrl: './charge-info-creat.component.html',
  styleUrls: ['./charge-info-creat.component.css'],
})
export class ChargeInfoCreatComponent implements OnInit {
  constructor(private fb: FormBuilder) {}
  properties: { [key: string]: number } = {};
  chargeInfoForm = this.fb.group(this.properties);
  ngOnInit(): void {
    let value: { [key: string]: number } = {};
    this.siteGradleList.forEach((site) => {
      this.seatGradleList.forEach((seat) => {
        this.movieGradleList.forEach((movie) => {
          let key = movie.id + '/' + seat.id + '/' + site.id;
          value[key] = 0;
        });
      });
    });
    this.chargeInfoForm = this.fb.group(value);
    console.log(this.chargeInfoForm);
  }

  toControl(absCtrl: AbstractControl): FormControl {
    const ctrl = absCtrl as FormControl;
    // if(!ctrl) throw;
    return ctrl;
  }

  submitForm() {
    console.log(this.chargeInfoForm.value);
  }

  getSiteGradleName(id: string): string {
    return this.siteGradleList.find((s) => s.id === id)?.siteGradeName!;
  }

  siteGradleList: { id: string; siteGradeName: string }[] = [
    {
      id: 'a',
      siteGradeName: 'siteGrade1',
    },
    {
      id: 'b',
      siteGradeName: 'siteGrade2',
    },
    {
      id: 'b',
      siteGradeName: 'siteGrade3',
    },
    {
      id: 'd',
      siteGradeName: 'siteGrade4',
    },
    {
      id: 'e',
      siteGradeName: 'siteGrade5',
    },
    {
      id: 'f',
      siteGradeName: 'siteGrade6',
    },
  ];
  seatGradleList: { id: string; seatGradeName: string }[] = [
    {
      id: 'a',
      seatGradeName: 'seatGrade1',
    },
    {
      id: 'b',
      seatGradeName: 'seatGrade2',
    },
    {
      id: 'c',
      seatGradeName: 'seatGrade3',
    },
    {
      id: 'd',
      seatGradeName: 'seatGrade4',
    },
    {
      id: 'e',
      seatGradeName: 'seatGrade5',
    },
    {
      id: 'f',
      seatGradeName: 'seatGrade6',
    },
  ];
  movieGradleList: { id: string; movieGradeName: string }[] = [
    {
      id: 'a',
      movieGradeName: 'movieGrade1',
    },
    {
      id: 'b',
      movieGradeName: 'movieGrade2',
    },
    {
      id: 'c',
      movieGradeName: 'movieGrade3',
    },
    {
      id: 'd',
      movieGradeName: 'movieGrade4',
    },
    {
      id: 'e',
      movieGradeName: 'movieGrade5',
    },
    {
      id: 'f',
      movieGradeName: 'movieGrade6',
    },
  ];

  // addInnitCharge(
  //   siteGradeId: string,
  //   seatGradleId: string,
  //   movieGradleId: string
  // ) {
  //   let propertiesInit : Record<
  //   string,
  //   Record<string, Record<string, number>>
  // > = {};
  //   this.siteGradleList.forEach((site) => {
  //     this.seatGradleList.forEach((seat) => {
  //       this.movieGradleList.forEach((movie) => {
  //         const recordSiteMap: Record<
  //           string,
  //           Record<string, Record<string, number>>
  //         > = {};
  //         const recordSeatMap: Record<string, Record<string, number>> = {};
  //         const recordMovieMap: Record<string, number> = {};
  //         recordMovieMap[movie.id] = 0;
  //         recordSeatMap[seat.id] = recordMovieMap;
  //         recordSiteMap[site.id] = recordSeatMap;
  //         propertiesInit = recordSiteMap;
  //       });
  //     });
  //   });
  //   // this.chargeInfoForm.addControl(this.fb.control(siteGradeId))
  //   // this.chargeInfoForm.controls[siteGradeId] = recordSeatMap;
  //   // console.log(this.chargeInfoForm.controls[siteGradeId])
  // }

  // // get propertyControls(): FormArray {
  // //   return this.chargeInfoForm.get('properties') as FormArray;
  // // }
}
