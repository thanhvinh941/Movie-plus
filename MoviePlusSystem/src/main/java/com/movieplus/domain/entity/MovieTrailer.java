package com.movieplus.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieTrailer {

	private String id;
	private String movieId;
	private String trailerUrl;
	private String trailerTitle;
}
