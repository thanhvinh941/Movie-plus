package com.movieplus.domain.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.movieplus.controller.external.operator.GetMovieInfoListController.GetMovieInfoListRequest;
import com.movieplus.domain.common.CustomRepository;
import com.movieplus.domain.entity.MovieInfo;
import com.movieplus.domain.payload.response.PaginationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetMovieInfoListService {

	private final CustomRepository<MovieInfo, String> movieInfoRepository;
	
	public void execute(GetMovieInfoListRequest request, PaginationResponse<MovieInfo> response) {
		Long totalRecords = getRotalRecordsMovieInfo(request);
		List<MovieInfo> movieInfos = getMovieInfoList(request);
		response.setPage(request.getPage());
		response.setPageSize(request.getPageSize());
		response.setTotalPages(Math.floorMod(totalRecords, request.getPageSize()));
		response.setTotalRecords(totalRecords.intValue());
		response.setRecords(movieInfos);
	}

	private Long getRotalRecordsMovieInfo(GetMovieInfoListRequest request) {
		String conditionStr = buildQuery(request);
		return movieInfoRepository.count(MovieInfo.class, conditionStr);
	}

	private List<MovieInfo> getMovieInfoList(GetMovieInfoListRequest request) {
		String conditionStr = buildQuery(request);
		return movieInfoRepository.selectByCondition(MovieInfo.class, conditionStr, null, request.getOrderBys(), request.getPageSize(), request.getPage() * request.getPageSize(), false);
	}
	
	private String buildQuery(GetMovieInfoListRequest request) {
		String conditionStr = "";
		if(!StringUtils.isBlank(request.getSearchTerm())) {			
			conditionStr += String.format(" (movie_name like '%%%s%%' or movie_sub_name = '%%%s%%')", request.getSearchTerm(),  request.getSearchTerm());
		}
		
		if(request.isIgnoreDelFlg()) {
			conditionStr += String.format(" and del_flg = %d", 0);
		}
		return conditionStr;
	}

}
