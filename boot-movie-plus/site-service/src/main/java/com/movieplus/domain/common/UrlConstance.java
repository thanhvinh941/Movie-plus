package com.movieplus.domain.common;

public interface UrlConstance {
	
	public static final String PREFIX_INTERNAL = "/internal";
	
	// Movie Service Start
	public static final String PREFIX_MOVIE = "/site";
	public static final String GET_MOVIE_DETAIL_INFO = PREFIX_INTERNAL + PREFIX_MOVIE + "/getMovieDetailInfo";
	// Movie Service End
}
