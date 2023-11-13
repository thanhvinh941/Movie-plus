package com.movieplus.domain.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.config.common.util.XYZUtil;
import com.movieplus.controller.external.operator.GetSeatInRoomController.GetSeatInRoomRequest;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.entity.RoomSeat;
import com.movieplus.domain.entity.SeatGradle;
import com.movieplus.domain.entity.SeatMaster;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class GetSeatInRoomService {
	private final CustomRepository customRepository;
	private final MessageManager messageManager;
	
	public Object execute(GetSeatInRoomRequest request) throws Exception {
		List<RoomSeat> roomSeats = getRoomSeat(request);
		
		List<String> seatIds = roomSeats.stream().map(RoomSeat::getSeatId).toList();
		List<SeatMaster> seatMasters = getSeatMaster(seatIds);
		Map<String, SeatMaster> seatMasterMap = seatMasters.stream()
				.collect(Collectors.toMap(SeatMaster::getId, Function.identity()));
		
		List<String> seatGradleIds = seatMasters.stream().map(SeatMaster::getSeatGradleId).toList();
		List<SeatGradle> seatGradles = getSeatGradle(seatGradleIds);
		Map<String, SeatGradle> seatGradleMap = seatGradles.stream()
				.collect(Collectors.toMap(SeatGradle::getId, Function.identity()));
		
		List<com.movieplus.controller.external.operator.GetSeatInRoomController.RoomSeat> roomSeatsResponse = roomSeats.stream().map(x -> {
			com.movieplus.controller.external.operator.GetSeatInRoomController.RoomSeat roomSeat = new com.movieplus.controller.external.operator.GetSeatInRoomController.RoomSeat();
			BeanUtils.copyProperties(x, roomSeat);

			com.movieplus.controller.external.operator.GetSeatInRoomController.SeatMaster seatMaster = new com.movieplus.controller.external.operator.GetSeatInRoomController.SeatMaster();
			SeatMaster seatMasterResource = seatMasterMap.get(x.getSeatId());
			BeanUtils.copyProperties(seatMasterResource, seatMaster);

			SeatGradle seatGradleResource = seatGradleMap.get(seatMasterResource.getSeatGradleId());
			com.movieplus.controller.external.operator.GetSeatInRoomController.SeatGradle seatGradle = new com.movieplus.controller.external.operator.GetSeatInRoomController.SeatGradle();
			BeanUtils.copyProperties(seatGradleResource, seatGradle);
			
			seatMaster.setSeatGradle(seatGradle);
			roomSeat.setSeatMaster(seatMaster);
			return roomSeat;
		}).toList();
		
		return roomSeatsResponse; //.stream().collect(Collectors.groupingBy(x->x.getSeatMaster().getSeatRow()));
	}
	
	private List<SeatGradle> getSeatGradle(List<String> seatGradleIds) throws Exception {
		if (CollectionUtils.isEmpty(seatGradleIds)) {
			return List.of();
		}
		String seatGradleCondition = String.format("id in (%s) and del_flg = %d and member_visible_flg = %d",
				XYZUtil.buildQueryIn(seatGradleIds), 0, 1);
		return customRepository.selectByCondition(SeatGradle.class, seatGradleCondition);
	}

	private List<RoomSeat> getRoomSeat(GetSeatInRoomRequest request) throws Exception {
		String roomSeatCondition = String.format("room_id = '%s'", request.getId());
		if(request.isIgnoreDelFlg()) {
			roomSeatCondition += String.format(" and del_flg = %d", 0);
		}
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
