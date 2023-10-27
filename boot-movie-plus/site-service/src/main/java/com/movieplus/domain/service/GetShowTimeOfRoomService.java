package com.movieplus.domain.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.movieplus.config.common.constant.EndPointConstant;
import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.config.common.service.CallInternalAPIService;
import com.movieplus.controller.external.operator.GetShowTimeOfRoomController.GetShowTimeOfRoomRequest;
import com.movieplus.controller.external.operator.GetShowTimeOfRoomController.GetShowTimeOfRoomResponse;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.exception.InternalAPIException;
import com.movieplus.domain.entity.ShowTime;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetShowTimeOfRoomService {

	private final CustomRepository customRepository;
	private final CallInternalAPIService apiService;

	public void execute(GetShowTimeOfRoomRequest request, GetShowTimeOfRoomResponse response) throws Exception {
		List<ShowTime> showTimes = getShowTime(request);

		List<com.movieplus.controller.external.operator.GetShowTimeOfRoomController.ShowTime> showTimesResponse = showTimes.stream().map(x -> {
			com.movieplus.controller.external.operator.GetShowTimeOfRoomController.ShowTime showTime = new com.movieplus.controller.external.operator.GetShowTimeOfRoomController.ShowTime();
			BeanUtils.copyProperties(x, showTime);
			MovieDetailInfoDto detailInfoDto = getMovieDetailInfo(x.getMovieId());
			showTime.setMovieInfo(detailInfoDto);
			return showTime;
		}).toList();
		response.setRecords(showTimesResponse);
	}

	private List<ShowTime> getShowTime(GetShowTimeOfRoomRequest request) throws Exception {
		String showTimeCondition = String.format("room_id = '%s'", request.getId());
		if (request.isIgnoreDelFlg()) {
			showTimeCondition += String.format(" and del_flg = %d", 0);
		}
		return customRepository.selectByCondition(ShowTime.class, showTimeCondition, request.getOrderBys(),
				request.getPageSize(), request.getPageSize() * request.getPage(), false);
	}

	private MovieDetailInfoDto getMovieDetailInfo(String movieId) {
		Map<String, Object> request = new HashMap<>();
		request.put("movieId", movieId);
		try {
			return apiService.callPostMenthodForObject(
					request, CallInternalAPIService.MOVIE_SERVICE, EndPointConstant.MovieService.PREFIX_PATH_URI
							+ EndPointConstant.INTERNAL_PATH_URI + "/getMovieDetailInfo",
					new TypeReference<MovieDetailInfoDto>() {
					});
		} catch (InternalAPIException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
