import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Breadcrumb } from '../../../common/data/breadcrumb';
import { BreadcrumbService } from '../../service/breadcrumb.service';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrls: ['./breadcrumb.component.css']
})
export class BreadcrumbComponent {
  breadcrumbs$: Observable<Breadcrumb[]>;

  constructor(private readonly breadcrumbService: BreadcrumbService) {
    this.breadcrumbs$ = this.breadcrumbService.breadcrumbs$;
    console.log(this.breadcrumbs$)
  }
}
