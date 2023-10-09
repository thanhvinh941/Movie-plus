import { Data } from '@angular/router';
import { async } from '@angular/core/testing';
import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormControl,
} from '@angular/forms';
import { DirectionalData } from '../data/directional.data';
import {
  EndPoint,
  MovieService,
} from 'src/app/common/config/endpoint.constants';

@Component({
  selector: 'app-charge-info-creat',
  templateUrl: './charge-info-creat.component.html',
  styleUrls: ['./charge-info-creat.component.css'],
})
export class ChargeInfoCreatComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private directionalData: DirectionalData
  ) {}
  properties: { [key: string]: number } = {};
  chargeInfoForm = this.fb.group(this.properties);
  ngOnInit(): void {
    let value: { [key: string]: number } = {};
    this.directionalData
      .getDirectionalValue(
        MovieService.END_POINT,
        MovieService.TABLE.MOVIE_GRADLE,
        0,
        0,
        '',
        ['id', 'movieGradeName'],
        false,
        {}
      )
      .subscribe({
        next: (res) => {
          this.movieGradleList = res.data;
        },
      });
    this.siteGradleList.forEach((site) => {
      this.seatGradleList.forEach((seat) => {
        this.movieGradleList.forEach((movie) => {
          let key = movie.id + '/' + seat.id + '/' + site.id;
          value[key] = 0;
        });
      });
    });

    this.chargeInfoForm = this.fb.group(value);
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
}
