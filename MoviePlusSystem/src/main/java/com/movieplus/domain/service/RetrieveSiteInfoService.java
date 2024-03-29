package com.movieplus.domain.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.controller.external.RetrieveSiteInfoController.RetrieveSiteInfoRequest;
import com.movieplus.domain.common.GetMovieDetailInfoService;
import com.movieplus.domain.common.GetShowTimeDetailInfoService;
import com.movieplus.domain.common.MovieConstance.KeyTypeReturn;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.dto.ShowTimeDto;
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
	private final GetMovieDetailInfoService getMovieDetailInfoService;
	private final GetShowTimeDetailInfoService getShowTimeDetailInfoService;
	
	public void execute(RetrieveSiteInfoRequest request, RetrieveSiteInfoResponse response) {
		String siteInfoId = request.getSiteId();
		SiteInfo siteInfo = getSiteInfo(siteInfoId);
		if (Objects.isNull(siteInfo)) {

		}
		
		BeanUtils.copyProperties(siteInfo, response);
		Map<String, List<ShowTimeDto>> showTimeResources = getShowTimeDetailInfoService.getShowTimeDetail(siteInfoId, null, KeyTypeReturn.MOVIE);
		Set<String> movieIds = showTimeResources.keySet();
		List<RetrieveSiteInfoResponse.MovieInfo> movieInfosResponse = movieIds.stream().map(movieId -> {
			RetrieveSiteInfoResponse.MovieInfo movieInfo = new RetrieveSiteInfoResponse.MovieInfo();
			MovieDetailInfoDto movieDetailInfoDto = getMovieDetailInfoService.getMovieDetailInfo(movieId);
			BeanUtils.copyProperties(movieDetailInfoDto, movieInfo);
			
			List<ShowTimeDto> showTimeDtos = showTimeResources.getOrDefault(movieId, List.of());
			movieInfo.setShowTimes(showTimeDtos);
			return movieInfo;
		}).toList();
		response.setMovieInfo(movieInfosResponse);
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
