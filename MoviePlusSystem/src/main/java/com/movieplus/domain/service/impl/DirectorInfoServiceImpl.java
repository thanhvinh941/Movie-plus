package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
			List<DirectorInfo> directorInfos = repository.saveAll(records);
			return directorInfos.stream()
					.map(DirectorInfo::getId)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public List<DirectorInfo> getDirectorInfo(GetInternalApiRequest request) throws Exception {
		try {
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			List<DirectorInfo> directorInfos = objectMapper.convertValue(results, new TypeReference<List<DirectorInfo>>() {});
			return directorInfos;
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
