export class MovieService {
  public static END_POINT = 'http://localhost:8083/movie/api';
  public static TABLE = {
    MOVIE_INFO: 'MovieInfo',
    GENRE_TYPE: 'GenreType',
    MOVIE_GRADLE: 'MovieGradle',
  };
}

export class SiteService {
  public static END_POINT = 'http://localhost:8084/site/api';
  public static TABLE = {
    ROOM_INFO: 'RoomInfo',
    SEAT_GRADLE: 'SeatGradle',
    SHOW_TIME: 'ShowTime',
    SITE_GRADLE: 'SiteGradle',
    SITE_INFO: 'SiteInfo',
    SEAT_MASTER: 'SeatMaster',
  };
}

export class ChargeService {
  public static END_POINT = 'http://localhost:8085/price/api';

  public static TABLE = {
    CHARGE_INFO_SET: 'ChargeInfoSet',
    CHARGE_INFO_PLAN: 'ChargeInfoPlan',
  };
}

export class EndPoint {
  public static ADMIN_END_POINT = '/admin';
  public static USER_END_POINT = '/user';
}
