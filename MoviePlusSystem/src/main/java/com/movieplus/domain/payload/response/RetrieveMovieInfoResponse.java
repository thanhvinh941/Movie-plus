package com.movieplus.domain.payload.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieplus.domain.common.dto.DirectorDto;
import com.movieplus.domain.common.dto.GenreTypeDto;
import com.movieplus.domain.common.dto.ProductionInfoDto;
import com.movieplus.domain.common.dto.ShowTimeDto;
import com.movieplus.domain.common.dto.StarInfoDto;
import com.movieplus.domain.common.dto.TrailerDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetrieveMovieInfoResponse {
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
	private List<Site> sites;

	@Data
	public static class Site {
		private String id;
		private String siteName;
		private String localtion;
		private List<ShowTimeDto> showTimes;
	}
}
