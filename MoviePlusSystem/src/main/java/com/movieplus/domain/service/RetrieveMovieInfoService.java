package com.movieplus.domain.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.controller.external.RetrieveMovieInfoController.RetrieveMovieInfoRequest;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.GetMovieDetailInfoService;
import com.movieplus.domain.common.GetShowTimeDetailInfoService;
import com.movieplus.domain.common.MovieConstance.KeyTypeReturn;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.dto.ShowTimeDto;
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
	private final GetMovieDetailInfoService getMovieDetailInfoService;
	private final GetShowTimeDetailInfoService getShowTimeDetailInfoService;
	
	public void execute(RetrieveMovieInfoRequest request, RetrieveMovieInfoResponse response) {
		String movieInfoId = request.getMovieId();
		MovieDetailInfoDto movieDetailInfoDto = getMovieDetailInfoService.getMovieDetailInfo(movieInfoId);
		Map<String, List<ShowTimeDto>> showTimeResources = getShowTimeDetailInfoService.getShowTimeDetail(null, movieInfoId, KeyTypeReturn.SITE);
		Set<String> siteIds = showTimeResources.keySet();
		List<SiteInfo> siteInfos = getSiteInfo(siteIds);
		List<RetrieveMovieInfoResponse.Site> responseSite = siteInfos.stream()
				.map(s -> {
					List<ShowTimeDto> sTimes = showTimeResources.getOrDefault(s.getId(), List.of());
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

	private List<SiteInfo> getSiteInfo(Set<String> siteIds) {
		if(siteIds.isEmpty()) {
			return List.of();
		}
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = String.format(" id in (%s)", GeneratorCommonUtil.joiningListString(siteIds));
		apiRequest.setConditionStr(conditionStr);
		try {
			return siteInfoService.getSiteInfo(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getSiteInfo", CLASS_NAME, e);
		}
		return List.of();
	}

}
