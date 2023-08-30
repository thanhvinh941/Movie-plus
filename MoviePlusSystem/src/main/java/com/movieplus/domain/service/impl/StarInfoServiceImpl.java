package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperCommonUtil;
import com.movieplus.domain.db.read.RStarInfoMapper;
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
			log.info("Do save with request: {}", ObjectMapperCommonUtil.writeValueAsString(records));
			List<StarInfo> starInfos = repository.saveAll(records);
			return starInfos.stream()
					.map(StarInfo::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

	@Override
	public List<StarInfo> getStarInfo(GetInternalApiRequest request) throws Exception {
		try {
			log.info("Do getStarInfo with request: {}", ObjectMapperCommonUtil.writeValueAsString(request));
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<StarInfo>>() {});
		} catch (Exception e) {
			log.error("ERROR getStarInfo: {}", e);
			return List.of();
		}
	}
}
