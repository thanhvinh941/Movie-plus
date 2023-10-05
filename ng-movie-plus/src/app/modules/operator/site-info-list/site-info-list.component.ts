import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { SiteInfo } from '../models/site-info';

@Component({
  selector: 'app-site-info-list',
  templateUrl: './site-info-list.component.html',
  styleUrls: ['./site-info-list.component.css']
})
export class SiteInfoListComponent implements OnInit{
  siteInfoList$!: Observable<SiteInfo>;
  
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  
}
