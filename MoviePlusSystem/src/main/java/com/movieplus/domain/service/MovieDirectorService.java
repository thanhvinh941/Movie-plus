package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.MovieDirector;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface MovieDirectorService {

	List<MovieDirector> getMovieDirector(GetInternalApiRequest apiRequest) throws Exception;

	List<String> save(List<MovieDirector> records) throws Exception;

}
