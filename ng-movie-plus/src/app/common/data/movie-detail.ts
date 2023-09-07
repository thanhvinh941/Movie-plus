import { Movie } from './movie-list';

interface Trailer {
  trailerUrl: string;
  trailerTitle: string;
}

interface GenreType {
  id: string;
  displayName: string;
}

interface Site {
  id: string;
  siteName: string;
  localtion: string;
  showTimes: ShowTime[];
}

interface ShowTime {
  id: string;
  startTime: string;
  endTime: string;
  roomInfo: RoomInfo;
}

interface RoomInfo {
  id: string;
  roomName: string;
}

export interface MovieDetail extends Movie {
  banners: string[];
  trailers: Trailer[];
  genreType: GenreType[];
  sites: Site[];
}
