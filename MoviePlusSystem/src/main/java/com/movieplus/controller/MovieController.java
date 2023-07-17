package com.movieplus.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.payload.GetMovieListRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

	private final ObjectMapper objectMapper;
	
	@PostMapping("/list-movie")
	public String getAllListMovies(@RequestBody GetMovieListRequest request) throws IOException {
		return new String(Files.readAllBytes(Paths.get("src/main/resources/data/list-movie.json")));
	}
}
