import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-operator-header',
  templateUrl: './operator-header.component.html',
  styleUrls: ['./operator-header.component.scss'],
})
export class OperatorHeaderComponent implements OnInit {
  @Input()
  sideBars: any[] = [
    { displayName: 'Dashboard', routerLink: 'operator/dashboard' },
    { displayName: 'Member Info', routerLink: 'operator/member-info' },
    { displayName: 'Genre Type', routerLink: 'operator/genre-type' },
    { displayName: 'Movie Info', routerLink: 'operator/movie-info' },
    { displayName: 'Site Info', routerLink: 'operator/site-info' },
    { displayName: 'Charge Plan', routerLink: 'operator/charge-plan' },
    { displayName: 'Movie Gradle', routerLink: 'operator/movie-gradle' },
    { displayName: 'Site Gradle', routerLink: 'operator/site-gradle' },
    { displayName: 'Seat Gradle', routerLink: 'operator/seat-gradle' },
  ];

  constructor() {}

  ngOnInit(): void {}
}
