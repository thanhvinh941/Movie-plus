import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { SiteInfo } from '../../models/site-info';

@Component({
  selector: 'app-site-info-list',
  templateUrl: './site-info-list.component.html',
  styleUrls: ['./site-info-list.component.css'],
})
export class SiteInfoListComponent implements OnInit {
  editCache: { [key: string]: { edit: boolean; data: SiteInfo } } = {};
  siteInfoList$!: Observable<SiteInfo[]>;
  pagination!: {
    page: number;
    pageSize: number;
    totalPages: number;
    totalRecords: number;
  };

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  startEdit(id: string): void {
    this.editCache[id].edit = true;
  }
}
