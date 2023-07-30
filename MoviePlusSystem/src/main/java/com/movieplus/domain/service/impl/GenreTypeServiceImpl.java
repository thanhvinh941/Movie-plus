package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.db.read.RGenreTypeMapper;
import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.GenreTypeRepository;
import com.movieplus.domain.service.GenreTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreTypeServiceImpl implements GenreTypeService {

	private final GenreTypeRepository repository;
	private final RGenreTypeMapper genreTypeMapper;
	private final ObjectMapper objectMapper;

	@Override
	public List<String> save(List<GenreType> records) throws Exception {
		try {
			List<GenreType> movieInfos = repository.saveAll(records);
			return movieInfos.stream().map(GenreType::getId).collect(Collectors.toList());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<GenreType> getGenreType(GetInternalApiRequest request) throws Exception {
		try {
			List<Map<String, Object>> results = genreTypeMapper.selectWhere(request.getConditionStr(),
					request.getLimit(), request.getOffset(), request.getOrderBys());
			List<GenreType> genreTypes = objectMapper.convertValue(results, new TypeReference<List<GenreType>>() {
			});
			return genreTypes;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
