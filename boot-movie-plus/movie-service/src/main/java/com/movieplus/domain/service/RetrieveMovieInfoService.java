package com.movieplus.domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.movieplus.controller.external.RetrieveMovieInfoController.RetrieveMovieInfoRequest;
import com.movieplus.domain.common.CallInternalAPIService;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MoviePlusConstance.KeyTypeReturn;
import com.movieplus.domain.common.TypeReferenceConstance;
import com.movieplus.domain.common.UrlConstance;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.dto.ShowTimeDto;
import com.movieplus.domain.common.exception.InternalAPIException;
import com.movieplus.domain.dto.SiteInfoDTO;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.payload.response.RetrieveMovieInfoResponse;
import com.movieplus.domain.payload.response.RetrieveMovieInfoResponse.Site;
import com.movieplus.domain.service.common.GetMovieDetailInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetrieveMovieInfoService {
	
	private final String[] logTitle  = {"RetrieveMovieInfoService"};
	private final CallInternalAPIService callInternalAPIService;
	private final GetMovieDetailInfoService getMovieDetailInfoService;
	
	public void execute(RetrieveMovieInfoRequest request, RetrieveMovieInfoResponse response) throws InternalAPIException {
		String movieInfoId = request.getMovieId();
		MovieDetailInfoDto movieDetailInfoDto = getMovieDetailInfoService.getMovieDetailInfo(movieInfoId);
		Map<String, List<ShowTimeDto>> showTimeResources = getShowTimeDetail(movieInfoId, KeyTypeReturn.SITE);
		Set<String> siteIds = showTimeResources.keySet();
		List<SiteInfoDTO> siteInfos = getSiteInfo(siteIds);
		List<RetrieveMovieInfoResponse.Site> responseSite = siteInfos.stream()
				.map(siteInfo -> {
					List<ShowTimeDto> sTimes = showTimeResources.getOrDefault(siteInfo.getId(), List.of());
					RetrieveMovieInfoResponse.Site site = new RetrieveMovieInfoResponse.Site();
					BeanUtils.copyProperties(siteInfo, site);
					site.setShowTimes(sTimes);
					return site;
				})
				.toList();
		
		generatorResponse(movieDetailInfoDto, responseSite, response);
	}

	private Map<String, List<ShowTimeDto>> getShowTimeDetail(String movieInfoId, KeyTypeReturn keyTypeReturn) throws InternalAPIException {
		Map<String, Object> apiRequest = new HashMap<>();
		apiRequest.put("movieId", movieInfoId);
		apiRequest.put("keyTypeReturn", keyTypeReturn);
		
		try {
			return callInternalAPIService.callPostMenthodForObject(apiRequest, CallInternalAPIService.SITE_SERVICE, UrlConstance.GET_SITE_INFO, TypeReferenceConstance.commonShowTime, true);
		} catch (Exception e) {
			log.error("{} ERROR CALL getShowTimeDetail: ", logTitle, e);
			throw new InternalAPIException(e);
		}
	}

	private void generatorResponse(MovieDetailInfoDto movieDetailInfoDto, List<Site> responseSite,
			RetrieveMovieInfoResponse response) {
		BeanUtils.copyProperties(movieDetailInfoDto, response);
		response.setSites(responseSite);
	}

	private List<SiteInfoDTO> getSiteInfo(Set<String> siteIds) throws InternalAPIException {
		if(CollectionUtils.isEmpty(siteIds)) {
			return List.of();
		}
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();
		String conditionStr = String.format(" id in (%s)", GeneratorCommonUtil.joiningListString(siteIds));
		apiRequest.setConditionStr(conditionStr);

		try {
			return callInternalAPIService.callPostMenthodForObject(apiRequest, CallInternalAPIService.SITE_SERVICE, UrlConstance.GET_SITE_INFO, TypeReferenceConstance.siteListType, true);
		} catch (Exception e) {
			log.error("{} ERROR CALL getSiteInfo: ", logTitle, e);
			throw new InternalAPIException(e);
		}
	}

}
