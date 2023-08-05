package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.MovieStar;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface MovieStarService {

	List<MovieStar> getMovieStar(GetInternalApiRequest apiRequest) throws Exception;

	List<String> save(List<MovieStar> records) throws Exception;

}
