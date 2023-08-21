package com.movieplus.domain.payload.response;

import java.util.List;

import com.movieplus.domain.common.dto.DirectorDto;
import com.movieplus.domain.common.dto.GenreTypeDto;
import com.movieplus.domain.common.dto.ProductionInfoDto;
import com.movieplus.domain.common.dto.ShowTimeDto;
import com.movieplus.domain.common.dto.StarInfoDto;
import com.movieplus.domain.common.dto.TrailerDto;

import lombok.Data;

@Data
public class RetrieveSiteInfoResponse {
	private String id;
	private String siteName;
	private String localtion;
	private List<MovieInfo> movieInfo;
	
	@Data
	public static class MovieInfo{
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
		private List<DirectorDto> directors;
		private List<StarInfoDto> starInfos;
		private ProductionInfoDto productionInfo;
		private List<ShowTimeDto> showTimes;
		
	}

}
