package com.movieplus.config.common.constant;

public class EndPointConstant {

	public static final String INTERNAL_PATH_URI = "/internal";
	public static final String EXTERNAL_PATH_URI = "/api";
	public static final String PREFIX_ADMIN = ""; //"/admin";
	public static final String PREFIX_USER = "/user";

	public static class MovieService {
		public static final String PREFIX_PATH_URI = "/movie";
		public static final String GET_MOVIEDETAILINFO = "/getMovieDetailInfo";
		
		public enum Table {
			MovieInfo, MovieGradle, MovieTrailer, MovieGenre, MovieDirector, MovieStar, GenreType
		}
	}

	public static class SiteService {
		public static final String PREFIX_PATH_URI = "/site";

		public enum Table {
			RoomInfo, RoomSeat, SeatGradle, SeatMaster, ShowTime, SiteGradle, SiteInfo
		}
	}

	public static class UserService {
		public static final String PREFIX_PATH_URI = "/user";

		public enum Table {
			DirectorInfo, ProductionInfo, StarInfo, UserInfo, UserToken
		}
	}

	public static class PriceService {
		public static final String PREFIX_PATH_URI = "/price";

		public enum Table {
			ChargeInfoSet
		}
	}
}
