package com.movieplus.domain.payload.response;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMovieListResponse {

	private List<Movie> movies;
	
	@Getter
	@Setter
	public class Movie {
		private String id;
		
		private String movieName;
		
		private String movieSubName;
		
		private String image;
	
		@JsonFormat(pattern = "yyyy-MM-dd")
		private LocalDate realestAt;
	}
}
