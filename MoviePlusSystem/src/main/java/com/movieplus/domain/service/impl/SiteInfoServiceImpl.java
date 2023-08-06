package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.db.read.RSiteInfoMapper;
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
	private final RSiteInfoMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<SiteInfo> records) throws Exception {
		try {
			List<SiteInfo> siteInfos = repository.saveAll(records);
			return siteInfos.stream()
					.map(SiteInfo::getId)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public List<SiteInfo> getSiteInfo(GetInternalApiRequest request) throws Exception {
		try {
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			List<SiteInfo> siteInfos = objectMapper.convertValue(results, new TypeReference<List<SiteInfo>>() {});
			return siteInfos;
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
