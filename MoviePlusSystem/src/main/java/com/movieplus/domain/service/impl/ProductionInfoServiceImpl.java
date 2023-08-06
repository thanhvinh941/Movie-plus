package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperCommonUtil;
import com.movieplus.domain.db.read.RProductionInfoMapper;
import com.movieplus.domain.entity.ProductionInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.ProductionInfoRepository;
import com.movieplus.domain.service.ProductionInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductionInfoServiceImpl implements ProductionInfoService {
	
	private final ProductionInfoRepository repository;
	private final RProductionInfoMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<ProductionInfo> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperCommonUtil.writeValueAsString(records));
			List<ProductionInfo> productionInfos = repository.saveAll(records);
			return productionInfos.stream()
					.map(ProductionInfo::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

	@Override
	public List<ProductionInfo> getProduction(GetInternalApiRequest request) {
		try {
			log.info("Do getProduction with request: {}", ObjectMapperCommonUtil.writeValueAsString(request));
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<ProductionInfo>>() {});
		} catch (Exception e) {
			log.error("ERROR getProduction: {}", e);
			return List.of();
		}
	}

}
