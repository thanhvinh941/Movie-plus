import { Injectable } from '@angular/core';
import { ShowTime } from '../data/show-time';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketServiceService {

  getData(): Observable<ShowTime> {
    return of().pipe()
  }

  constructor() { }
}
