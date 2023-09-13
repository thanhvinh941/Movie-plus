package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperUtil;
import com.movieplus.domain.db.read.RShowTimeMapper;
import com.movieplus.domain.entity.ShowTime;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.ShowTimeRepository;
import com.movieplus.domain.service.ShowTimeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService{

	private final ShowTimeRepository repository;
	private final RShowTimeMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<ShowTime> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperUtil.writeValueAsString(records));
			List<ShowTime> showTimes = repository.saveAll(records);
			return showTimes.stream()
					.map(ShowTime::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

	@Override
	public List<ShowTime> getShowTime(GetInternalApiRequest request) {
		try {
			log.info("Do getShowTime with request: {}", ObjectMapperUtil.writeValueAsString(request));
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<ShowTime>>() {});
		} catch (Exception e) {
			log.error("ERROR getShowTime: {}", e);
			return List.of();
		}
	}
}
