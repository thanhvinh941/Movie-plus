import { AbstractAPIResponse } from 'src/app/shared/models/APIResponse';
import { MovieInfoDetail } from './dto/MovieInfoDetailDto';
import { ShowTime } from './dto/ShowTimeDto';

interface MovieInfo extends MovieInfoDetail {
  id: string;
  movieName: string;
  movieSubName: string;
  durationMin: string;
  description: string;
  thumnail: string;
  yearReleaseAt: number;
  banners: string[];
  trailers: { trailerUrl: string; trailerTitle: string }[];
  genreType: { id: string; displayName: string }[];
  directors: any;
  starInfos: any;
  productionInfo: any;
  sites: {
    id: string;
    siteName: string;
    localtion: string;
    showTimes: ShowTime[];
  };
}

export interface RetrieveMovieInfoResponse extends AbstractAPIResponse {
  data: MovieInfo;
}
