package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.db.read.RRoomInfoMapper;
import com.movieplus.domain.entity.RoomInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.RoomInfoRepository;
import com.movieplus.domain.service.RoomInfoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomInfoServiceImpl implements RoomInfoService {
	
	private final RoomInfoRepository repository;
	private final RRoomInfoMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<RoomInfo> getRoomInfo(GetInternalApiRequest request) throws Exception {
		try {
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			List<RoomInfo> roomInfos = objectMapper.convertValue(results, new TypeReference<List<RoomInfo>>() {});
			return roomInfos;
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public List<String> save(List<RoomInfo> records) throws Exception {
		try {
			List<RoomInfo> roomInfos = repository.saveAll(records);
			return roomInfos.stream()
					.map(RoomInfo::getId)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
