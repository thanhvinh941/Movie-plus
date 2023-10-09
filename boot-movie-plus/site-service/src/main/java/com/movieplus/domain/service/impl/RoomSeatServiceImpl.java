package com.movieplus.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperUtil;
import com.movieplus.domain.entity.RoomSeat;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.RoomSeatRepository;
import com.movieplus.domain.service.RoomSeatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomSeatServiceImpl implements RoomSeatService {
	private final RoomSeatRepository repository;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<RoomSeat> getRoomSeat(GetInternalApiRequest request) {
//		try {
//			log.info("Do getRoomSeat with request: {}", ObjectMapperUtil.writeValueAsString(request));
//			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
//			return objectMapper.convertValue(results, new TypeReference<List<RoomSeat>>() {});
//		} catch (Exception e) {
//			log.error("ERROR getRoomSeat: {}", e);
//		}
		return List.of();
	}

	@Override
	public List<String> save(List<RoomSeat> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperUtil.writeValueAsString(records));
			List<RoomSeat> roomSeats = repository.saveAll(records);
			return roomSeats.stream()
					.map(RoomSeat::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

}
