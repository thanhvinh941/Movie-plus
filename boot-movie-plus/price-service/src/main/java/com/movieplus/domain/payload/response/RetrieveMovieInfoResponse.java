package com.movieplus.domain.payload.response;

import java.util.List;

import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.dto.ShowTimeDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetrieveMovieInfoResponse extends MovieDetailInfoDto{
	private List<Site> sites;

	@Getter
	@Setter
	public static class Site {
		private String id;
		private String siteName;
		private String localtion;
		private List<ShowTimeDto> showTimes;
	}
}
