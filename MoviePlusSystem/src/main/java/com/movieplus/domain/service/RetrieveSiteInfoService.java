package com.movieplus.domain.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.controller.RetrieveSiteInfoController.RetrieveSiteInfoRequest;
import com.movieplus.domain.common.GetMovieDetailInfoService;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.dto.ShowTimeDto;
import com.movieplus.domain.entity.ShowTime;
import com.movieplus.domain.entity.SiteInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.payload.response.RetrieveSiteInfoResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetrieveSiteInfoService {

	private final SiteInfoService siteInfoService;
	private final ShowTimeService showTimeService;
	private final GetMovieDetailInfoService getMovieDetailInfoService;
	
	public void execute(RetrieveSiteInfoRequest request, RetrieveSiteInfoResponse response) {
		String siteInfoId = request.getSiteId();
		SiteInfo siteInfo = getSiteInfo(siteInfoId);
		if (Objects.isNull(siteInfo)) {

		}
		BeanUtils.copyProperties(siteInfo, response);
		List<ShowTime> showTimes = getShowTime(siteInfoId);
		Map<String, List<ShowTime>> mapShowTimeByMovieId = showTimes.stream().collect(Collectors.groupingBy(ShowTime::getMovieId));
		List<String> movieIds = showTimes.stream()
				.map(ShowTime::getMovieId)
				.toList();
		List<RetrieveSiteInfoResponse.MovieInfo> movieInfosResponse = movieIds.stream().map(movieId -> {
			RetrieveSiteInfoResponse.MovieInfo movieInfo = new RetrieveSiteInfoResponse.MovieInfo();
			MovieDetailInfoDto movieDetailInfoDto = getMovieDetailInfoService.getMovieDetailInfo(movieId);
			BeanUtils.copyProperties(movieDetailInfoDto, movieInfo);
			List<ShowTime> showTimeOfMovies = mapShowTimeByMovieId.getOrDefault(movieId, List.of());
			List<ShowTimeDto> showTimeResponse = showTimeOfMovies.stream()
					.map(showTimeOfMovie->{
						ShowTimeDto showTime = new ShowTimeDto();
						showTime.setEndTime(showTimeOfMovie.getEndTime());
						showTime.setStartTime(showTimeOfMovie.getStartTime());
						showTime.setId(showTimeOfMovie.getId());
						return showTime;
					}).toList();
			movieInfo.setShowTimes(showTimeResponse);
			return movieInfo;
		}).toList();
		response.setMovieInfo(movieInfosResponse);
	}

	private List<ShowTime> getShowTime(String siteInfoId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		LocalDate nowStamp = LocalDate.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String nowStr = nowStamp.format(dateTimeFormatter);
		
		String conditionStr = null;
		conditionStr = String.format(" site_id = '%s' AND start_time >= '%s'", siteInfoId, nowStr);
		apiRequest.setConditionStr(conditionStr);
		apiRequest.setOrderBys(List.of("start_time ASC"));
		try {
			return showTimeService.getShowTime(apiRequest);
		} catch (Exception e) {
			log.error("ERROR call getShowTime", e);
		}
		return List.of();
	}

	private SiteInfo getSiteInfo(String siteInfoId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		conditionStr = String.format(" id = '%s'", siteInfoId);
		apiRequest.setConditionStr(conditionStr);
		try {
			return siteInfoService.getSiteInfo(apiRequest).get(0);
		} catch (Exception e) {
			log.error("ERROR call getSiteInfo {}", e);
		}
		return null;
	}

}
