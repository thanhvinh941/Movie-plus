package com.movieplus.domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.controller.external.RetrieveSiteInfoController.RetrieveSiteInfoRequest;
import com.movieplus.domain.common.CallInternalAPIService;
import com.movieplus.domain.common.MovieConstance.KeyTypeReturn;
import com.movieplus.domain.common.TypeReferenceConstance;
import com.movieplus.domain.common.UrlConstance;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.dto.ShowTimeDto;
import com.movieplus.domain.common.exception.InternalAPIException;
import com.movieplus.domain.entity.SiteInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.payload.response.RetrieveSiteInfoResponse;
import com.movieplus.domain.service.common.GetShowTimeDetailInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetrieveSiteInfoService {

	private final String[] logTitle = { "RetrieveSiteInfo" };
	private final SiteInfoService siteInfoService;
	private final CallInternalAPIService callInternalAPIService;
	private final GetShowTimeDetailInfoService getShowTimeDetailInfoService;

	public void execute(RetrieveSiteInfoRequest request, RetrieveSiteInfoResponse response)
			throws InternalAPIException {
		try {
			String siteInfoId = request.getSiteId();
			SiteInfo siteInfo = getSiteInfo(siteInfoId);
			if (Objects.isNull(siteInfo)) {

			}

			BeanUtils.copyProperties(siteInfo, response);
			Map<String, List<ShowTimeDto>> showTimeResources = getShowTimeDetailInfoService
					.getShowTimeDetail(siteInfoId, null, KeyTypeReturn.MOVIE);
			Set<String> movieIds = showTimeResources.keySet();
			List<RetrieveSiteInfoResponse.MovieInfo> movieInfosResponse = new ArrayList<>();
			for (String movieId : movieIds) {
				RetrieveSiteInfoResponse.MovieInfo movieInfo = new RetrieveSiteInfoResponse.MovieInfo();
				MovieDetailInfoDto movieDetailInfoDto = getMovieDetailInfo(movieId);

				BeanUtils.copyProperties(movieDetailInfoDto, movieInfo);

				List<ShowTimeDto> showTimeDtos = showTimeResources.getOrDefault(movieId, List.of());
				movieInfo.setShowTimes(showTimeDtos);
				movieInfosResponse.add(movieInfo);
			}
			response.setMovieInfo(movieInfosResponse);
		} catch (Exception e) {
			throw new InternalAPIException(e);
		}
	}

	private MovieDetailInfoDto getMovieDetailInfo(String movieId) throws InternalAPIException {
		Map<String, Object> apiRequest = new HashMap<>();
		apiRequest.put("movieId", movieId);

		try {
			return callInternalAPIService.callPostMenthodForObject(apiRequest, CallInternalAPIService.MOVIE_SERVICE,
					UrlConstance.GET_MOVIE_DETAIL_INFO, TypeReferenceConstance.movieDetail, true);
		} catch (Exception e) {
			log.error("{} ERROR CALL getMovieDetailInfo: ", logTitle, e);
			throw new InternalAPIException(e);
		}
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
