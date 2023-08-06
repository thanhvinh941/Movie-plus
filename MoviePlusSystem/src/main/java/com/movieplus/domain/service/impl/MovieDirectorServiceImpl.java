package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperCommonUtil;
import com.movieplus.domain.db.read.RMovieDirectorMapper;
import com.movieplus.domain.entity.MovieDirector;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.MovieDirectorRepository;
import com.movieplus.domain.service.MovieDirectorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieDirectorServiceImpl implements MovieDirectorService {
	
	private final MovieDirectorRepository repository;
	private final RMovieDirectorMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<MovieDirector> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperCommonUtil.writeValueAsString(records));
			List<MovieDirector> movieDirectors = repository.saveAll(records);
			return movieDirectors.stream()
					.map(MovieDirector::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}
	
	@Override
	public List<MovieDirector> getMovieDirector(GetInternalApiRequest request) {
		try {
			log.info("Do getMovieDirector with request: {}", ObjectMapperCommonUtil.writeValueAsString(request));
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<MovieDirector>>() {});
		} catch (Exception e) {
			log.error("ERROR getMovieDirector: {}", e);
			return List.of();
		}
	}

}
