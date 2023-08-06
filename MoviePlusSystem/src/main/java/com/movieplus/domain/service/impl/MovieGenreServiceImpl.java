package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperCommonUtil;
import com.movieplus.domain.db.read.RMovieGenreMapper;
import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.MovieGenreRepository;
import com.movieplus.domain.service.MovieGenreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieGenreServiceImpl implements MovieGenreService {
	
	private final MovieGenreRepository repository;
	private final RMovieGenreMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<MovieGenre> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperCommonUtil.writeValueAsString(records));
			List<MovieGenre> movieGenres = repository.saveAll(records);
			return movieGenres.stream()
					.map(MovieGenre::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

	@Override
	public List<MovieGenre> getMovieGenre(GetInternalApiRequest request) {
		try {
			log.info("Do getMovieGenre with request: {}", ObjectMapperCommonUtil.writeValueAsString(request));
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<MovieGenre>>() {});
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			return List.of();
		}
	}

}
