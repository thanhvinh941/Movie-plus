import { AbstractAPIResponse } from 'src/app/shared/models/APIResponse';
import { ShowTime } from './dto/ShowTimeDto';
import { MovieDetail } from 'src/app/common/data/movie-detail';

interface ShowTimeDetail extends ShowTime {
  movieInfo: MovieDetail;
  seatMatrixs: {
    [key: string]: { id: string; size: number; status: number }[];
  }[];
}

export interface RetrieveShowTimeResponse extends AbstractAPIResponse {
  data: ShowTimeDetail;
}
