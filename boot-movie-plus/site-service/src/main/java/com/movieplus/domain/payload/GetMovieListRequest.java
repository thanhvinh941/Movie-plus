package com.movieplus.domain.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMovieListRequest {
	private String term;
	private int pageNumber;
	private int pageSize;
}
