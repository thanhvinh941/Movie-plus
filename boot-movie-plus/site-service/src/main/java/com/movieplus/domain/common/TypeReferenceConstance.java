package com.movieplus.domain.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;

public interface TypeReferenceConstance {
	
	public static TypeReference<MovieDetailInfoDto> movieDetail = new TypeReference<MovieDetailInfoDto>() {};
}
