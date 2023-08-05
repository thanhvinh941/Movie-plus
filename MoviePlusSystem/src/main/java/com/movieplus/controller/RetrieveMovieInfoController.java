package com.movieplus.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.service.RetrieveMovieInfoService;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class RetrieveMovieInfoController {
	
	@Data
	public static class RetrieveMovieInfoRequest{
		@Nonnull
		private String movieId;
	}

	@Data
	public static class RetrieveMovieInfoResponse{
		private String id;
		private String movieName;
		private String movieSubName;
		private long durationMin;
		private String description;
		private String thumnail;
		private Long yearReleaseAt;
		private List<String> banners;
		private List<Trailer> trailers;
		private List<GenreType> genreType;
		private List<Director> directors;
		private List<StarInfo> starInfos;
		private ProductionInfo productionInfo;
		private List<Site> sites;
		
		@Data
		public static class ShowTime{
			private String id;
			private LocalDateTime startTime;
			private LocalDateTime endTime;
		}
		
		@Data
		public static class Site{
			private String id;
			private String siteName;
			private String localtion;
			private List<ShowTime> showTimes;
		}
		
		@Data
		public static class ProductionInfo{
			private String id;
			private String productionName;
		}
		
		@Data
		public static class GenreType{
			private String id;
			private String displayName;
		}
		
		@Data
		public static class Director{
			private String id;
			private String directorName;
		}
		
		@Data
		public static class StarInfo{
			private String id;
			private String starName;
			private String starAvatar;
		}
		
		@Data
		public static class Trailer{
			private String trailerUrl;
			private String trailerTitle;
		}
	}
	
	
	private final RetrieveMovieInfoService service;
	
	@PostMapping("retrieveMovieInfo")
	@ResponseBody
	public String doRetrieveMovieInfo(@Validated @RequestBody RetrieveMovieInfoRequest request) {
		try {
			RetrieveMovieInfoResponse response = new RetrieveMovieInfoResponse();
			
			service.execute(request, response);
			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
