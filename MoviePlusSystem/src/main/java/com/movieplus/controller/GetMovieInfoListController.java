package com.movieplus.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class GetMovieInfoListController {

	@Data
	public static class GetMovieListRequest{
		private String genreTypeId;
		private String productionId;
		private String directorId;
		private String starId;
	}
	
	@PostMapping("/list-movie")
	public String getAllListMovies(@RequestBody GetMovieListRequest request) throws IOException {
		return new String(Files.readAllBytes(Paths.get("src/main/resources/data/list-movie.json")));
	}
}
