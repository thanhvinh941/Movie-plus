package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperUtil;
import com.movieplus.domain.db.read.RMovieTrailerMapper;
import com.movieplus.domain.entity.MovieTrailer;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.MovieTrailerRepository;
import com.movieplus.domain.service.MovieTrailerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieTrailerServiceImpl implements MovieTrailerService{

	private final MovieTrailerRepository repository;
	private final RMovieTrailerMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<MovieTrailer> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperUtil.writeValueAsString(records));
			List<MovieTrailer> movieTrailers = repository.saveAll(records);
			return movieTrailers.stream()
					.map(MovieTrailer::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

	@Override
	public List<MovieTrailer> getMovieTrailer(GetInternalApiRequest request) {
		try {
			log.info("Do getMovieTrailer with request: {}", ObjectMapperUtil.writeValueAsString(request));
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<MovieTrailer>>() {});
		} catch (Exception e) {
			log.error("ERROR getMovieTrailer: {}", e);
			return List.of();
		}
	}
	
	
}
