export interface MovieInfoDetail {
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
}
