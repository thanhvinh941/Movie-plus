package com.movieplus.domain.payload.response;

import java.util.List;

import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.dto.ShowTimeDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetrieveSiteInfoResponse {
	private String id;
	private String siteName;
	private String localtion;
	private List<MovieInfo> movieInfo;
	
	@Getter
	@Setter
	public static class MovieInfo extends MovieDetailInfoDto{
		private List<ShowTimeDto> showTimes;
	}

}
