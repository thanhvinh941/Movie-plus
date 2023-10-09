package com.movieplus.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.CustomRepository;
import com.movieplus.domain.common.ObjectMapperUtil;
import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.payload.request.UpdateInternalApiRequest;
import com.movieplus.domain.repository.GenreTypeRepository;
import com.movieplus.domain.service.GenreTypeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreTypeServiceImpl implements GenreTypeService {

	private final GenreTypeRepository repository;
	private final ObjectMapper objectMapper;
	private final CustomRepository customRepository;

	@Override
	public List<String> save(List<GenreType> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperUtil.writeValueAsString(records));
			List<GenreType> movieInfos = repository.saveAll(records);
			return movieInfos.stream().map(GenreType::getId).toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

	@Override
	public List<GenreType> getGenreType(GetInternalApiRequest request) {
		try {
			log.info("Do getGenreType with request: {}", ObjectMapperUtil.writeValueAsString(request));
			Object results = customRepository.selectByCondition(GenreType.class, request.getConditionStr(),
					request.getOrderBys(), request.getLimit(), request.getOffset(), false);
			return objectMapper.convertValue(results, new TypeReference<List<GenreType>>() {
			});
		} catch (Exception e) {
			log.error("ERROR getGenreType: {}", e);
			return List.of();
		}
	}

	@Override
	public boolean update(UpdateInternalApiRequest<String> request) {
//		try {
//			log.info("Do update with request: {}", ObjectMapperUtil.writeValueAsString(request));
//			return customRepository.update(request.getParams(), GenreType.class, request.getId());
//		} catch (Exception e) {
//			log.error("ERROR update: {}", e);
			return false;
//		}
	}

}
