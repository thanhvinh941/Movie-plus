package com.movieplus.domain.payload.response;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetrieveMovieResponse {
	
	private String id;
	
	private String movieName;
	
	private String description;
	
	private List<Trailer> trailers;
	
	private List<String> photos;
	
	private String image;
	
	private String thumnail;
	
	private List<Genre> genres;

	private double rating;
	
	private Production production;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate realestAt;
	

	@Getter
	@Setter
	public class Production{
		private String id;
		private String productionName;
	}
	
	@Getter
	@Setter
	public class Genre{
		private String id;
		private String displayName;
	}
	
	@Getter
	@Setter
	public class Trailer{
		private String trailerUrl;
		private String trailerTitle;
	}
}
