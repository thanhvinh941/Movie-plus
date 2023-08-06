package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
			List<ShowTime> showTimes = repository.saveAll(records);
			return showTimes.stream()
					.map(ShowTime::getId)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public List<ShowTime> getShowTime(GetInternalApiRequest request) throws Exception {
		try {
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			List<ShowTime> showTimes = objectMapper.convertValue(results, new TypeReference<List<ShowTime>>() {});
			return showTimes;
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
