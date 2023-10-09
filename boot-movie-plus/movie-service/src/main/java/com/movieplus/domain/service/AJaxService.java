package com.movieplus.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.controller.external.operator.ajax.AddGenreTypeAJAXController.AddGenreTypeAJAXRequest;
import com.movieplus.domain.common.CustomRepository;
import com.movieplus.domain.dto.MovieInfoDTO;
import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.repository.MovieGenreRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
public class AJaxService {

	private final ObjectMapper objectMapper;
	private final MovieGenreRepository movieGenreRepository;
	private final CustomRepository genreTypeRepository;

	@Getter
	@Setter
	private class GetAllGenreTypeResponse {
		private String id;
		private String displayName;
	}

	public String doAddGenreType(AddGenreTypeAJAXRequest request) {
		MovieGenre movieGenre = new MovieGenre();
		movieGenre.setMovieId(request.getMovieId());
		movieGenre.setGenreId(request.getGenreTypeId());
		return movieGenreRepository.save(movieGenre).getId();
	}

	public List<GetAllGenreTypeResponse> getAllGenreType() throws Exception {
		Object result = genreTypeRepository.selectByCondition(GenreType.class, null);
		List<GenreType> genreTypes = objectMapper.convertValue(result, new TypeReference<List<GenreType>>() {});
		List<GetAllGenreTypeResponse> resullt = genreTypes.stream().map(genreType -> {
			GetAllGenreTypeResponse response = new GetAllGenreTypeResponse();
			response.setDisplayName(genreType.getDisplayName());
			response.setId(genreType.getId());
			return response;
		}).toList();
		return resullt;
	}

}
