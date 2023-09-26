package com.movieplus.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.movieplus.controller.external.operator.GetMovieInfoListController.GetMovieInfoListRequest;
import com.movieplus.domain.common.CustomRepository;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.entity.MovieInfo;
import com.movieplus.domain.payload.response.PaginationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetMovieInfoListService {

	private final CustomRepository<MovieInfo, String> movieInfoRepository;
	
	
	public void execute(GetMovieInfoListRequest request, PaginationResponse<MovieDetailInfoDto> response) {
		Long totalRecords = getRotalRecordsMovieInfo(request);
		List<MovieInfo> movieInfos = getMovieInfoList(request);
		
	}

	private Long getRotalRecordsMovieInfo(GetMovieInfoListRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<MovieInfo> getMovieInfoList(GetMovieInfoListRequest request) {
		String conditionStr = String.format(" (movie_name like '%%s%' or movie_sub_name = '%%s%')", request.getSearchTerm(),  request.getSearchTerm());
		if(request.isIgnoreDelFlg()) {
			conditionStr += String.format(" and del_flg = %d", 0);
		}
		return movieInfoRepository.selectByCondition(MovieInfo.class, conditionStr, null, null, request.getPageSize(), request.getPage() * request.getPageSize(), false);
	}

}
