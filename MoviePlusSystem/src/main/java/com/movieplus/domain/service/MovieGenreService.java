package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface MovieGenreService {

	List<String> save(List<MovieGenre> records) throws Exception;

	List<MovieGenre> getMovieGenre(GetInternalApiRequest request);

}
