package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.db.read.RMovieDirectorMapper;
import com.movieplus.domain.entity.MovieBanner;
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
			List<MovieDirector> movieDirectors = repository.saveAll(records);
			return movieDirectors.stream()
					.map(MovieDirector::getId)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	@Override
	public List<MovieDirector> getMovieDirector(GetInternalApiRequest request) throws Exception {
		try {
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			List<MovieDirector> movieDirectors = objectMapper.convertValue(results, new TypeReference<List<MovieDirector>>() {});
			return movieDirectors;
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
