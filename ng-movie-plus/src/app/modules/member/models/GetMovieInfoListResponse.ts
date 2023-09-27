import { AbstractAPIResponse } from 'src/app/shared/models/APIResponse';

interface GenreType {
  id: string;
  displayName: string;
  movies: {
    id: string;
    movieName: string;
    movieSubName: string;
    thumnail: string;
    yearReleaseAt: number;
  }[];
}

export interface GetMovieInfoListResponse extends AbstractAPIResponse {
  data: { genreTypes: GenreType[] };
}
