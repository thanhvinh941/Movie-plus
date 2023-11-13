package com.movieplus.domain.elk;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MovieIndex {
	private String id;

	private String movieName;

	private Double movieSubName;

	private String durationMin;

	private String description;

	private List<String> banners;

	private List<GenreType> genreType;

	private List<Trailer> trailer;

	@Getter
	@Setter
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class GenreType {
		private String genreId;

		private String displayName;
	}

	@Getter
	@Setter
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class Trailer {
		private String trailerTitle;

		private String trailerUrl;
	}
}
