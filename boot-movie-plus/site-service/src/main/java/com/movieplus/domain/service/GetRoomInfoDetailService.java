package com.movieplus.domain.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.config.common.util.XYZUtil;
import com.movieplus.controller.external.operator.GetRoomInfoDetailController.GetRoomInfoDetailRequest;
import com.movieplus.controller.external.operator.GetRoomInfoDetailController.GetRoomInfoDetailResponse;
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

	private final ObjectMapper objectMapper;
	private final CustomRepository customRepository;

	public void execute(GetRoomInfoDetailRequest request, GetRoomInfoDetailResponse response) throws Exception {
		List<RoomInfo> roomInfos = getRoomInfo(request.getId());
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
			copyProperty(x, showTime);
			return showTime;
		}).toList();
		BeanUtils.copyProperties(roomInfos.get(0), response);
		response.setShowTimes(showTimesResponse);

		List<GetRoomInfoDetailResponse.RoomSeat> roomSeatsResponse = roomSeats.stream().map(x -> {
			GetRoomInfoDetailResponse.RoomSeat roomSeat = new GetRoomInfoDetailResponse.RoomSeat();
			copyProperty(x, roomSeat);

			GetRoomInfoDetailResponse.SeatMaster seatMaster = new GetRoomInfoDetailResponse.SeatMaster();
			SeatMaster seatMasterResource = seatMasterMap.get(x.getSeatId());
			copyProperty(seatMasterResource, seatMaster);

			SeatGradle seatGradleResource = seatGradleMap.get(seatMasterResource.getId());
			GetRoomInfoDetailResponse.SeatGradle seatGradle = new GetRoomInfoDetailResponse.SeatGradle();
			copyProperty(seatGradleResource, seatGradle);
			seatMaster.setSeatGradle(seatGradle);
			roomSeat.setSeatMaster(seatMaster);

			return roomSeat;
		}).toList();
		response.setRoomSeats(roomSeatsResponse);
	}

	private List<RoomInfo> getRoomInfo(String roomId) throws Exception {
		String roomInfoCondition = String.format("id = '%s' and del_flg = %d", roomId, 0);
		List<?> roomInfoResults = customRepository.selectByCondition(RoomInfo.class, roomInfoCondition);
		List<RoomInfo> roomInfos = roomInfoResults.stream().map(x -> {
			RoomInfo roomInfo = new RoomInfo();
			copyProperty(x, roomInfo);
			return roomInfo;
		}).toList();

		return roomInfos;
	}

	private List<ShowTime> getShowTime(String roomId) throws Exception {
		String showTimeCondition = String.format("id = '%s' and del_flg = %d", roomId, 0);
		List<?> showTimeResults = customRepository.selectByCondition(ShowTime.class, showTimeCondition);
		List<ShowTime> showTimes = showTimeResults.stream().map(x -> {
			ShowTime showTime = new ShowTime();
			copyProperty(x, showTime);
			return showTime;
		}).toList();

		return showTimes;
	}

	private List<SeatGradle> getSeatGradle(List<String> seatGradleIds) throws Exception {
		if(CollectionUtils.isEmpty(seatGradleIds)) {
			return List.of();
		}
		String seatGradleCondition = String.format("id in (%s) and del_flg = %d and member_visible_flg = %d",
				XYZUtil.buildQueryIn(seatGradleIds), 0, 1);
		List<?> seatGradleResults = customRepository.selectByCondition(SeatGradle.class, seatGradleCondition);
		List<SeatGradle> seatGradles = seatGradleResults.stream().map(x -> {
			SeatGradle seatGradle = new SeatGradle();
			copyProperty(x, seatGradle);
			return seatGradle;
		}).toList();

		return seatGradles;
	}

	private List<RoomSeat> getRoomSeat(String roomId) throws Exception {
		String roomSeatCondition = String.format("room_id = '%s' and del_flg = %d", roomId, 0);
		List<?> roomSeatResults = customRepository.selectByCondition(RoomSeat.class, roomSeatCondition);
		List<RoomSeat> roomSeats = roomSeatResults.stream().map(x -> {
			RoomSeat roomSeat = new RoomSeat();
			copyProperty(x, roomSeat);
			return roomSeat;
		}).toList();
		return roomSeats;
	}

	private List<SeatMaster> getSeatMaster(List<String> seatIds) throws Exception {
		if(CollectionUtils.isEmpty(seatIds)) {
			return List.of();
		}
		String seatMasterCondition = String.format("id in (%s) and del_flg = %d", XYZUtil.buildQueryIn(seatIds), 0);
		List<?> seatMasterResults = customRepository.selectByCondition(SeatMaster.class, seatMasterCondition);
		List<SeatMaster> seatMasters = seatMasterResults.stream().map(x -> {
			SeatMaster seatMaster = new SeatMaster();
			copyProperty(x, seatMaster);
			return seatMaster;
		}).toList();

		return seatMasters;
	}

	public void copyProperty(Object resource, Object trg) {
		Map<String, Object> resourceMap = objectMapper.convertValue(resource, Map.class);
		for (Map.Entry<String, Object> entry : resourceMap.entrySet()) {
			copyProperty(entry, trg);
		}
	}

	public void copyProperty(Map.Entry<String, Object> entry, Object trg) {
		try {
			BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);
			Class<?> propertyType = trgWrap.getPropertyType(entry.getKey());
			Object value = entry.getValue();
			if (propertyType != null && propertyType.equals(LocalDateTime.class)
					&& (entry.getValue() instanceof Timestamp)) {
				value = LocalDateTime.ofInstant(((Timestamp) entry.getValue()).toInstant(), ZoneOffset.ofHours(0));
			}
			trgWrap.setPropertyValue(entry.getKey(), value);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
