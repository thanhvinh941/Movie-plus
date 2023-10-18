package com.movieplus.domain.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.config.common.util.XYZUtil;
import com.movieplus.controller.external.operator.GetRoomInfoDetailController.GetRoomInfoDetailRequest;
import com.movieplus.controller.external.operator.GetRoomInfoDetailController.GetRoomInfoDetailResponse;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.entity.RoomInfo;
import com.movieplus.domain.entity.RoomSeat;
import com.movieplus.domain.entity.SeatGradle;
import com.movieplus.domain.entity.SeatMaster;
import com.movieplus.domain.entity.ShowTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetRoomInfoDetailService {

	private final CustomRepository customRepository;
	private final MessageManager messageManager;
	
	public void execute(GetRoomInfoDetailRequest request, GetRoomInfoDetailResponse response) throws Exception {
		List<RoomInfo> roomInfos = getRoomInfo(request.getId());
		if(CollectionUtils.isEmpty(roomInfos)) {
			throw new ClientException(messageManager.getMessage("PARAM_INVALID", null));
		}
		List<RoomSeat> roomSeats = getRoomSeat(request.getId());
		List<String> seatIds = roomSeats.stream().map(RoomSeat::getSeatId).toList();
		List<SeatMaster> seatMasters = getSeatMaster(seatIds);
		Map<String, SeatMaster> seatMasterMap = seatMasters.stream()
				.collect(Collectors.toMap(SeatMaster::getId, Function.identity()));

		List<String> seatGradleIds = seatMasters.stream().map(SeatMaster::getSeatGradleId).toList();
		List<SeatGradle> seatGradles = getSeatGradle(seatGradleIds);
		Map<String, SeatGradle> seatGradleMap = seatGradles.stream()
				.collect(Collectors.toMap(SeatGradle::getId, Function.identity()));
		List<ShowTime> showTimes = getShowTime(request.getId());

		List<GetRoomInfoDetailResponse.ShowTime> showTimesResponse = showTimes.stream().map(x -> {
			GetRoomInfoDetailResponse.ShowTime showTime = new GetRoomInfoDetailResponse.ShowTime();
			BeanUtils.copyProperties(x, showTimes);
			return showTime;
		}).toList();
		BeanUtils.copyProperties(roomInfos.get(0), response);
		response.setShowTimes(showTimesResponse);

		List<GetRoomInfoDetailResponse.RoomSeat> roomSeatsResponse = roomSeats.stream().map(x -> {
			GetRoomInfoDetailResponse.RoomSeat roomSeat = new GetRoomInfoDetailResponse.RoomSeat();
			BeanUtils.copyProperties(x, roomSeat);

			GetRoomInfoDetailResponse.SeatMaster seatMaster = new GetRoomInfoDetailResponse.SeatMaster();
			SeatMaster seatMasterResource = seatMasterMap.get(x.getSeatId());
			BeanUtils.copyProperties(seatMasterResource, seatMaster);

			SeatGradle seatGradleResource = seatGradleMap.get(seatMasterResource.getSeatGradleId());
			GetRoomInfoDetailResponse.SeatGradle seatGradle = new GetRoomInfoDetailResponse.SeatGradle();
			BeanUtils.copyProperties(seatGradleResource, seatGradle);
			
			seatMaster.setSeatGradle(seatGradle);
			roomSeat.setSeatMaster(seatMaster);
			return roomSeat;
		}).toList();
		response.setRoomSeats(roomSeatsResponse);
	}

	private List<RoomInfo> getRoomInfo(String roomId) throws Exception {
		String roomInfoCondition = String.format("id = '%s' and del_flg = %d", roomId, 0);
		return customRepository.selectByCondition(RoomInfo.class, roomInfoCondition);
	}

	private List<ShowTime> getShowTime(String roomId) throws Exception {
		String showTimeCondition = String.format("id = '%s' and del_flg = %d", roomId, 0);
		return customRepository.selectByCondition(ShowTime.class, showTimeCondition);
	}

	private List<SeatGradle> getSeatGradle(List<String> seatGradleIds) throws Exception {
		if (CollectionUtils.isEmpty(seatGradleIds)) {
			return List.of();
		}
		String seatGradleCondition = String.format("id in (%s) and del_flg = %d and member_visible_flg = %d",
				XYZUtil.buildQueryIn(seatGradleIds), 0, 1);
		return customRepository.selectByCondition(SeatGradle.class, seatGradleCondition);
	}

	private List<RoomSeat> getRoomSeat(String roomId) throws Exception {
		String roomSeatCondition = String.format("room_id = '%s' and del_flg = %d", roomId, 0);
		return customRepository.selectByCondition(RoomSeat.class, roomSeatCondition);
	}

	private List<SeatMaster> getSeatMaster(List<String> seatIds) throws Exception {
		if (CollectionUtils.isEmpty(seatIds)) {
			return List.of();
		}
		String seatMasterCondition = String.format("id in (%s) and del_flg = %d", XYZUtil.buildQueryIn(seatIds), 0);
		return customRepository.selectByCondition(SeatMaster.class, seatMasterCondition);
	}

}
