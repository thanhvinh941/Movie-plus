package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperCommonUtil;
import com.movieplus.domain.db.read.RMovieStarMapper;
import com.movieplus.domain.entity.MovieStar;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.MovieStarRepository;
import com.movieplus.domain.service.MovieStarService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieStarServiceImpl implements MovieStarService {
	
	private final MovieStarRepository repository;
	private final RMovieStarMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<MovieStar> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperCommonUtil.writeValueAsString(records));
			List<MovieStar> movieStars = repository.saveAll(records);
			return movieStars.stream()
					.map(MovieStar::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}
	
	@Override
	public List<MovieStar> getMovieStar(GetInternalApiRequest request) {
		try {
			log.info("Do save with request: {}", ObjectMapperCommonUtil.writeValueAsString(request));
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<MovieStar>>() {});
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			return List.of();
		}
	}

}
