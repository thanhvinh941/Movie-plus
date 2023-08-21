package com.movieplus.domain.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.controller.RetrieveMovieInfoController.RetrieveMovieInfoRequest;
import com.movieplus.domain.common.GetMovieDetailInfoService;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.dto.ShowTimeDto;
import com.movieplus.domain.entity.ShowTime;
import com.movieplus.domain.entity.SiteInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.payload.response.RetrieveMovieInfoResponse;
import com.movieplus.domain.payload.response.RetrieveMovieInfoResponse.Site;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetrieveMovieInfoService {
	
	private final String CLASS_NAME = "RetrieveMovieInfoService";
	private final SiteInfoService siteInfoService;
	private final ShowTimeService showTimeService;
	private final GetMovieDetailInfoService getMovieDetailInfoService;
	
	public void execute(RetrieveMovieInfoRequest request, RetrieveMovieInfoResponse response) {
		String movieInfoId = request.getMovieId();
		MovieDetailInfoDto movieDetailInfoDto = getMovieDetailInfoService.getMovieDetailInfo(movieInfoId);
		List<ShowTime> showTimes = getShowTime(movieInfoId);
		List<String> siteIds = showTimes.stream()
				.map(ShowTime::getSiteId)
				.toList();
		List<SiteInfo> siteInfos = getSiteInfo(siteIds);
		List<RetrieveMovieInfoResponse.Site> responseSite = siteInfos.stream()
				.map(s -> {
					List<ShowTime> showTimeOfSite = showTimes.stream()
							.filter(st -> st.getSiteId().equals(s.getId()))
							.toList();
					List<ShowTimeDto> sTimes =  showTimeOfSite.stream()
							.map(st -> {
								ShowTimeDto showTime = new ShowTimeDto();
								BeanUtils.copyProperties(st, showTime);
								showTime.setEndTime(st.getEndTime());
								showTime.setStartTime(st.getStartTime());
								return showTime;
							})
							.toList();
					RetrieveMovieInfoResponse.Site site = new RetrieveMovieInfoResponse.Site();
					BeanUtils.copyProperties(s, site);
					site.setShowTimes(sTimes);
					
					return site;
				})
				.toList();
		
		generatorResponse(movieDetailInfoDto, responseSite, response);
	}

	private void generatorResponse(MovieDetailInfoDto movieDetailInfoDto, List<Site> responseSite,
			RetrieveMovieInfoResponse response) {
		BeanUtils.copyProperties(movieDetailInfoDto, response);
		response.setSites(responseSite);
	}

	private List<SiteInfo> getSiteInfo(List<String> siteIds) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		if(!siteIds.isEmpty()) {
			conditionStr = String.format(" id in (%s)", joiningListString(siteIds));
		}
		apiRequest.setConditionStr(conditionStr);
		try {
			return siteInfoService.getSiteInfo(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getSiteInfo", CLASS_NAME, e);
		}
		return List.of();
	}

	private List<ShowTime> getShowTime(String movieInfoId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		LocalDate nowStamp = LocalDate.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String nowStr = nowStamp.format(dateTimeFormatter);
		
		String conditionStr = null;
		conditionStr = String.format(" movie_id = '%s' AND start_time >= '%s'", movieInfoId, nowStr);
		apiRequest.setConditionStr(conditionStr);
		
		try {
			return showTimeService.getShowTime(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getShowTime", CLASS_NAME, e);
		}
		return List.of();
	}

	private String joiningListString(List<String> datas) {
		return datas.stream().map(data -> "'" + data + "'").collect(Collectors.joining(", "));
	}
}
