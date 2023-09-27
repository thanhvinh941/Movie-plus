import { AbstractAPIResponse } from 'src/app/shared/models/APIResponse';
import { MovieInfoDetail } from './dto/MovieInfoDetailDto';
import { ShowTime } from 'src/app/common/data/show-time';

interface SiteInfo {
  id: string;
  siteName: string;
  localtion: string;
  movieInfo: MovieInfo[];
}

interface MovieInfo extends MovieInfoDetail {
  showTimes: ShowTime[];
}

export interface RetrieveMovieInfoResponse extends AbstractAPIResponse {
  data: SiteInfo;
}
