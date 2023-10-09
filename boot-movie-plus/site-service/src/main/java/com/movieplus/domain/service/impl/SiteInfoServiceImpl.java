package com.movieplus.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperUtil;
import com.movieplus.domain.entity.SiteInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.SiteInfoRepository;
import com.movieplus.domain.service.SiteInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SiteInfoServiceImpl implements SiteInfoService{

	private final SiteInfoRepository repository;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<SiteInfo> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperUtil.writeValueAsString(records));
			List<SiteInfo> siteInfos = repository.saveAll(records);
			return siteInfos.stream()
					.map(SiteInfo::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

	@Override
	public List<SiteInfo> getSiteInfo(GetInternalApiRequest request) throws Exception {
//		try {
//			log.info("Do getSiteInfo with request: {}", ObjectMapperUtil.writeValueAsString(request));
//			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
//			return objectMapper.convertValue(results, new TypeReference<List<SiteInfo>>() {});
//		} catch (Exception e) {
//			log.error("ERROR getSiteInfo: {}", e);
//		}
			return List.of();
	}
}
