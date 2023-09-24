package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.MovieInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface MovieInfoService {

	List<String> save(List<MovieInfo> records) throws Exception;

	List<MovieInfo> getMovieInfo(GetInternalApiRequest request);

}
