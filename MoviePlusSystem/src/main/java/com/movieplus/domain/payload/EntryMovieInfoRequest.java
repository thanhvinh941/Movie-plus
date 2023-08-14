package com.movieplus.domain.payload;

import java.util.List;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntryMovieInfoRequest{
	private String movieName;
	private String movieSubName;
	private long durationMin;
	private Long yearReleaseAt;
	private String description;
	
	@Nonnull
	private List<String> genreTypeIds;
	private List<String> banners;
	private List<Trailer> trailers;
	
	@Getter
	@Setter
	public class Trailer{
		private String trailerTitle;
		private String trailerUrl;
	}
}
