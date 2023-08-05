package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.db.read.RStarInfoMapper;
import com.movieplus.domain.entity.MovieBanner;
import com.movieplus.domain.entity.StarInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.StarInfoRepository;
import com.movieplus.domain.service.StarInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StarInfoServiceImpl implements StarInfoService{

	private final StarInfoRepository repository;
	private final RStarInfoMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<StarInfo> records) throws Exception {
		try {
			List<StarInfo> starInfos = repository.saveAll(records);
			return starInfos.stream()
					.map(StarInfo::getId)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public List<StarInfo> getStarInfo(GetInternalApiRequest request) throws Exception {
		try {
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			List<StarInfo> starInfos = objectMapper.convertValue(results, new TypeReference<List<StarInfo>>() {});
			return starInfos;
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
