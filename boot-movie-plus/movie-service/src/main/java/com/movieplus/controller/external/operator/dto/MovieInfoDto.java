package com.movieplus.controller.external.operator.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieInfoDto {
	private String id;
	
	private String movieName;
	
	private String movieSubName;
	
	private long durationMin;
	
	private String description;
	
	private String thumnail;

	private String productionId;
	
	private Long yearReleaseAt;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;
	
	private String updateUser;
	
	private Byte delFlg;

	private List<MovieGenre> genreTypes; 
	
	@Getter
	@Setter
	public static class MovieGenre{
		private String id;
		private String genreId;
		private String genreDisplayName;
	}
}
