package com.movieplus.domain.payload.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMovieListResponse {
	private List<GenreType> genreType;

	@Getter
	@Setter
	public static class GenreType{
		private String id;
		private String displayName;
		private List<Movie> movies;

		@Getter
		@Setter
		public static class Movie{
			private String id;
			private String movieName;
			private String movieSubName;
			private String thumnail;
			private int yearReleaseAt;
		}
	}
}
