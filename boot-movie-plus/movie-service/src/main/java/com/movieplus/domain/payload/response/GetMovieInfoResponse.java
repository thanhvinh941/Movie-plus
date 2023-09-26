package com.movieplus.domain.payload.response;

import java.util.List;

import com.movieplus.domain.common.dto.DirectorDto;
import com.movieplus.domain.common.dto.GenreTypeDto;
import com.movieplus.domain.common.dto.ProductionInfoDto;
import com.movieplus.domain.common.dto.StarInfoDto;
import com.movieplus.domain.common.dto.TrailerDto;

import lombok.Data;

@Data
public class GetMovieInfoResponse {
	private String id;
	private String movieName;
	private String movieSubName;
	private long durationMin;
	private String description;
	private String thumnail;
	private Long yearReleaseAt;
	private List<String> banners;
	private List<TrailerDto> trailers;
	private List<GenreTypeDto> genreType;
	private List<DirectorDto> directors; // TODO add type of director
	private List<StarInfoDto> starInfos; // TODO add caster
	private ProductionInfoDto productionInfo;
}
