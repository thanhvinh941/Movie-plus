import { Time } from "@angular/common";

export interface ShowTime {
  id : number;
  hallId : number;
  movieId : number;
  dateShow : Date;
  timeStart : Time;
  timeEnd : Time;
}
