import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css'],
})
export class PaginationComponent {
  @Input()
  pagination!: {
    page: number;
    pageSize: number;
    totalPages: number;
    totalRecords: number;
  };
}
