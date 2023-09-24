package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.MovieTrailer;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface MovieTrailerService {

	List<String> save(List<MovieTrailer> records) throws Exception;

	List<MovieTrailer> getMovieTrailer(GetInternalApiRequest request);

}
