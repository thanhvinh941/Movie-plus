package com.movieplus.domain.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.controller.external.operator.SettingRoomSeatController.SettingRoomSeatRequest;
import com.movieplus.domain.entity.RoomSeat;
import com.movieplus.domain.entity.SeatMaster;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettingRoomSeatService {

	private final ObjectMapper objectMapper;
	private final CustomRepository customRepository;

	public boolean execute(SettingRoomSeatRequest request) throws Exception {
		String roomId = request.getRoomId();
		String updateUser = "dummy admin";
		List<SeatMaster> seatMasters = getSeatMaster();
		Map<List<Object>, String> seatMasterMap = seatMasters.stream()
				.collect(
						Collectors.toMap(x -> List.of(x.getSeatRow(), x.getSeatColume(), x.getSeatSize(), x.getSeatGradleId()), 
								SeatMaster::getId)
						);

		List<RoomSeat> roomSeatFromRequest = getRoomSeatFromRequest(request, seatMasterMap, roomId, updateUser);

		return insertAllRoomSeat(roomSeatFromRequest);
	}

	boolean insertAllRoomSeat(List<RoomSeat> roomSeats) {
		try {
			for(RoomSeat roomSeat : roomSeats) {
				customRepository.insertRecords(roomSeat);
			}
		} catch (Exception e) {
			log.error("insertAllRoomSeat", e);
			return false;
		}
		return true;
	}

	private List<RoomSeat> getRoomSeatFromRequest(SettingRoomSeatRequest request,
			Map<List<Object>, String> seatMasterMap, String roomId, String updateUser) {
		return request.getSeatMaster().stream()
				.filter(x -> x.getSeatEnable())
				.map(x -> {
					RoomSeat roomSeat = new RoomSeat();
					List<Object> keySeat = List.of(x.getSeatRow(), x.getSeatColume(), x.getSeatSize(), x.getSeatGradle());
					String seatMasterId = seatMasterMap.getOrDefault(keySeat, null);
					if (Objects.isNull(seatMasterId)) {
						try {
							seatMasterId = insertSeatMaster(keySeat, updateUser);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					roomSeat.setSeatId(seatMasterId);
					roomSeat.setRoomId(roomId);
					roomSeat.setUsableStatus(1);
					roomSeat.setDelFlg(0);
					roomSeat.setUpdateUser(updateUser);
					return roomSeat;
				}).toList();

	}

	private String insertSeatMaster(List<Object> keySeat, String updateUser) throws Exception {
		Integer seatRow = (Integer) keySeat.get(0);
		Integer seatColume = (Integer) keySeat.get(1);
		Integer seatSeatSize = (Integer) keySeat.get(2);
		String seatSeatGradleId = (String) keySeat.get(3);
		SeatMaster seatMaster = new SeatMaster();
		seatMaster.setSeatRow(seatRow);
		seatMaster.setSeatColume(seatColume);
		seatMaster.setSeatSize(seatSeatSize);
		seatMaster.setSeatGradleId(seatSeatGradleId);
		String seatMasterId = customRepository.insertRecords(seatMaster);
		return seatMasterId;
	}

	private List<SeatMaster> getSeatMaster() throws Exception {
		List<?> seatMasterResults = customRepository.selectByCondition(SeatMaster.class);
		List<SeatMaster> seatMasters = seatMasterResults.stream().map(x -> {
			SeatMaster seatMaster = new SeatMaster();
			copyProperty(x, seatMaster);
			return seatMaster;
		}).toList();
		return seatMasters;
	}

	public void copyProperty(Object resource, Object trg) {
		Map<String, Object> resourceMap = objectMapper.convertValue(resource, new TypeReference<Map<String, Object>>() {});
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
