package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.db.read.RMovieTrailerMapper;
import com.movieplus.domain.entity.MovieTrailer;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.MovieTrailerRepository;
import com.movieplus.domain.service.MovieTrailerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieTrailerServiceImpl implements MovieTrailerService{

	private final MovieTrailerRepository repository;
	private final RMovieTrailerMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<MovieTrailer> records) throws Exception {
		try {
			List<MovieTrailer> movieTrailers = repository.saveAll(records);
			return movieTrailers.stream()
					.map(MovieTrailer::getId)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public List<MovieTrailer> getMovieTrailer(GetInternalApiRequest request) throws Exception {
		try {
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			List<MovieTrailer> movieTrailers = objectMapper.convertValue(results, new TypeReference<List<MovieTrailer>>() {});
			return movieTrailers;
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	
}
