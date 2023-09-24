package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface GenreTypeService {

	List<String> save(List<GenreType> records) throws Exception;

	List<GenreType> getGenreType(GetInternalApiRequest request);

}
