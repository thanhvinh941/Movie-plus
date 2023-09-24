package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperUtil;
import com.movieplus.domain.db.read.RGenreTypeMapper;
import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.GenreTypeRepository;
import com.movieplus.domain.service.GenreTypeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreTypeServiceImpl implements GenreTypeService {

	private final GenreTypeRepository repository;
	private final RGenreTypeMapper genreTypeMapper;
	private final ObjectMapper objectMapper;

	@Override
	public List<String> save(List<GenreType> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperUtil.writeValueAsString(records));
			List<GenreType> movieInfos = repository.saveAll(records);
			return movieInfos.stream()
					.map(GenreType::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

	@Override
	public List<GenreType> getGenreType(GetInternalApiRequest request) {
		try {
			log.info("Do getGenreType with request: {}", ObjectMapperUtil.writeValueAsString(request));
			List<Map<String, Object>> results = genreTypeMapper.selectWhere(request.getConditionStr(),request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<GenreType>>() {});
		} catch (Exception e) {
			log.error("ERROR getGenreType: {}", e);
			return List.of();
		}
	}

}
