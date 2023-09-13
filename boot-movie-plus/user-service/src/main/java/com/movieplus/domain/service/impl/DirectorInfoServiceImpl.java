package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperUtil;
import com.movieplus.domain.db.read.RDirectorInfoMapper;
import com.movieplus.domain.entity.DirectorInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.DirectorInfoRepository;
import com.movieplus.domain.service.DirectorInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectorInfoServiceImpl implements DirectorInfoService{

	private final DirectorInfoRepository repository;
	private final RDirectorInfoMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<DirectorInfo> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperUtil.writeValueAsString(records));
			List<DirectorInfo> directorInfos = repository.saveAll(records);
			return directorInfos.stream()
					.map(DirectorInfo::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

	@Override
	public List<DirectorInfo> getDirectorInfo(GetInternalApiRequest request) {
		try {
			log.info("Do getDirectorInfo with request: {}", ObjectMapperUtil.writeValueAsString(request));
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<DirectorInfo>>() {});
		} catch (Exception e) {
			log.error("ERROR getDirectorInfo: {}", e);
			return List.of();
		}
	}
}
