package com.movieplus.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.controller.external.RetriveShowTimeController.RetriveShowTimeRequest;
import com.movieplus.domain.common.CallInternalAPIService;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.TypeReferenceConstance;
import com.movieplus.domain.common.UrlConstance;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.dto.ShowTimeDto;
import com.movieplus.domain.common.exception.InternalAPIException;
import com.movieplus.domain.entity.RoomInfo;
import com.movieplus.domain.entity.RoomSeat;
import com.movieplus.domain.entity.SeatMaster;
import com.movieplus.domain.entity.ShowTime;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.payload.response.RetriveShowTimeResponse;
import com.movieplus.domain.payload.response.RetriveShowTimeResponse.Seat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetriveShowTimeService {

	private final String[] logTitle = { "RetriveShowTime" };
	private final ShowTimeService showTimeService;
	private final RoomInfoService roomInfoService;
	private final RoomSeatService roomSeatService;
	private final SeatMasterService seatMasterService;
	private final CallInternalAPIService callInternalAPIService;

	public void execute(RetriveShowTimeRequest request, RetriveShowTimeResponse response) throws InternalAPIException {
		ShowTime showTime = getShowTime(request.getShowTimeId());
		if (Objects.isNull(showTime)) {

		}
		String roomId = showTime.getRoomId();
		RoomInfo roomInfo = getRoomInfo(roomId);
		if (Objects.isNull(roomInfo)) {

		}

		MovieDetailInfoDto movieDetailInfoDto = getMovieDetailInfo(showTime.getMovieId());

		List<RoomSeat> roomSeats = getRoomSeat(roomId);
		List<String> seatMasterIds = roomSeats.stream().map(RoomSeat::getSeatId).toList();
		List<SeatMaster> seatMasters = getSeatMaster(seatMasterIds);
		Map<Integer, List<SeatMaster>> seatMasterMaps = seatMasters.stream()
				.collect(Collectors.groupingBy(SeatMaster::getSeatRow));
		List<Map<String, List<Seat>>> seatMatrix = new ArrayList<>();
		seatMasterMaps.forEach((key, values) -> {
			Collections.sort(values, new Comparator<SeatMaster>() {
				@Override
				public int compare(SeatMaster o1, SeatMaster o2) {
					return o1.getSeatColume().compareTo(o2.getSeatColume());
				}
			});
			Map<String, List<Seat>> map = new HashMap<>();
			List<Seat> seats = values.stream().map(v -> {
				Seat seat = new RetriveShowTimeResponse.Seat();
				seat.setId(v.getId());
				seat.setSize(v.getSeatSize());
				return seat;
			}).toList();
			map.put(String.valueOf((char) (key + 64)), seats);
			seatMatrix.add(map);
		});

		ShowTimeDto.RoomInfo roomInfoResponse = new ShowTimeDto.RoomInfo();
		BeanUtils.copyProperties(roomInfo, roomInfoResponse);

		response.setEndTime(showTime.getEndTime());
		response.setStartTime(showTime.getStartTime());
		response.setId(request.getShowTimeId());
		response.setMovieDetailInfoDto(movieDetailInfoDto);
		response.setRoomInfo(roomInfoResponse);
		response.setSeatMatrix(seatMatrix);
	}

	private List<SeatMaster> getSeatMaster(List<String> seatMasterIds) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = String.format("id in (%s)", GeneratorCommonUtil.joiningListString(seatMasterIds));
		apiRequest.setConditionStr(conditionStr);

		try {
			return seatMasterService.getSeatMaster(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getRoomSeat", logTitle, e);
		}
		return List.of();
	}

	private List<RoomSeat> getRoomSeat(String roomId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = String.format(" room_id = '%s'", roomId);
		apiRequest.setConditionStr(conditionStr);

		try {
			return roomSeatService.getRoomSeat(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getRoomSeat", logTitle, e);
		}
		return List.of();
	}

	private RoomInfo getRoomInfo(String roomId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = String.format("id = '%s'", roomId);
		apiRequest.setConditionStr(conditionStr);

		try {
			return roomInfoService.getRoomInfo(apiRequest).get(0);
		} catch (Exception e) {
			log.error("{} ERROR call getRoomInfo", logTitle, e);
		}
		return null;
	}

	private ShowTime getShowTime(String showTimeId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = String.format("id = '%s'", showTimeId);
		apiRequest.setConditionStr(conditionStr);

		try {
			return showTimeService.getShowTime(apiRequest).get(0);
		} catch (Exception e) {
			log.error("{} ERROR call getShowTime", logTitle, e);
		}
		return null;
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

}
