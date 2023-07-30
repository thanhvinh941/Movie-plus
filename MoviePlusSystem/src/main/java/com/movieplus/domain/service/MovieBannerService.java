package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.MovieBanner;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface MovieBannerService {

	List<String> save(List<MovieBanner> records) throws Exception;

	List<MovieBanner> getMovieBanner(GetInternalApiRequest request) throws Exception;

}
