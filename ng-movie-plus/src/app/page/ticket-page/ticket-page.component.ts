import { ShowTime } from 'src/app/data/show-time';
import { TicketServiceService } from './../../service/ticket-service.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-ticket-page',
  templateUrl: './ticket-page.component.html',
  styleUrls: ['./ticket-page.component.css']
})
export class TicketPageComponent implements OnInit{

  showTime$!: Observable<ShowTime>;

  constructor(private readonly ticketService : TicketServiceService){}

  ngOnInit(): void {
    this.showTime$ = this.ticketService.getData()
  }
}
