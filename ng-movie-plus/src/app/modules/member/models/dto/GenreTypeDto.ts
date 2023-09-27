export interface GenreType {
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
