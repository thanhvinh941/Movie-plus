package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperUtil;
import com.movieplus.domain.db.read.RSeatMasterMapper;
import com.movieplus.domain.entity.SeatMaster;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.SeatMasterRepository;
import com.movieplus.domain.service.SeatMasterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeatMasterServiceImpl implements SeatMasterService {
	private final SeatMasterRepository repository;
	private final RSeatMasterMapper mapper;
	private final ObjectMapper objectMapper;

	@Override
	public List<SeatMaster> getSeatMaster(GetInternalApiRequest request) {
		try {
			log.info("Do getSeatMaster with request: {}", ObjectMapperUtil.writeValueAsString(request));
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<SeatMaster>>() {});
		} catch (Exception e) {
			log.error("ERROR getSeatMaster: {}", e);
			return List.of();
		}
	}

	@Override
	public List<String> save(List<SeatMaster> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperUtil.writeValueAsString(records));
			List<SeatMaster> showTimes = repository.saveAll(records);
			return showTimes.stream()
					.map(SeatMaster::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

}
