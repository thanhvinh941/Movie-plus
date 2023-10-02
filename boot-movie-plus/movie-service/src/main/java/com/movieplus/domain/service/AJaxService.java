package com.movieplus.domain.service;

import org.springframework.stereotype.Service;

import com.movieplus.controller.external.operator.ajax.AddGenreTypeAJAXController.AddGenreTypeAJAXRequest;
import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.repository.MovieGenreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AJaxService {
	
	private final MovieGenreRepository movieGenreRepository;
	
	public String doAddGenreType(AddGenreTypeAJAXRequest request) {
		MovieGenre movieGenre = new MovieGenre();
		movieGenre.setMovieId(request.getMovieId());
		movieGenre.setGenreId(request.getGenreTypeId());
		return movieGenreRepository.save(movieGenre).getId();
	}

}
