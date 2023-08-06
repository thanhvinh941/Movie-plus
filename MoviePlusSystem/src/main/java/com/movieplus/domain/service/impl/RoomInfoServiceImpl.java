package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperCommonUtil;
import com.movieplus.domain.db.read.RRoomInfoMapper;
import com.movieplus.domain.entity.RoomInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.RoomInfoRepository;
import com.movieplus.domain.service.RoomInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomInfoServiceImpl implements RoomInfoService {
	
	private final RoomInfoRepository repository;
	private final RRoomInfoMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<RoomInfo> getRoomInfo(GetInternalApiRequest request) {
		try {
			log.info("Do getRoomInfo with request: {}", ObjectMapperCommonUtil.writeValueAsString(request));
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<RoomInfo>>() {});
		} catch (Exception e) {
			log.error("ERROR getRoomInfo: {}", e);
			return List.of();
		}
	}

	@Override
	public List<String> save(List<RoomInfo> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperCommonUtil.writeValueAsString(records));
			List<RoomInfo> roomInfos = repository.saveAll(records);
			return roomInfos.stream()
					.map(RoomInfo::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

}
