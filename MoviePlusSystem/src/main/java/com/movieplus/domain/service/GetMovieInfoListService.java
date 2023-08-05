package com.movieplus.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.controller.GetMovieInfoListController.GetMovieListRequest;
import com.movieplus.controller.GetMovieInfoListController.GetMovieListResponse;
import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.entity.MovieInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetMovieInfoListService {

	private final GenreTypeService genreTypeService;
	private final MovieInfoService movieInfoService;
	private final MovieGenreService movieGenreService;

	public void execute(GetMovieListRequest request, GetMovieListResponse response) {
		List<GenreType> genreTypes = getGenreType(request.getGenreTypeIds());
		List<String> genreTypeIds = genreTypes.stream()
				.map(GenreType::getId)
				.collect(Collectors.toList());
		
		List<MovieGenre> movieGenres = getMovieGenre2(genreTypeIds);
		Map<String, List<MovieGenre>> mapMovieGenresByGenre = movieGenres.stream()
				.collect(Collectors.groupingBy(MovieGenre::getGenreId));
		
		List<String> movieIds = movieGenres.stream()
				.map(MovieGenre::getMovieId)
				.distinct()
				.collect(Collectors.toList());
		
		List<MovieInfo> searchTermMovieInfos = getMovieInfo(request.getSearchTerm(), movieIds);
		
		generatorRespons(searchTermMovieInfos, mapMovieGenresByGenre, genreTypes, response);
	}


	private List<MovieInfo> getMovieInfo(String searchTerm, List<String> movieIds) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		conditionStr = String.format(" id in (%s)", joiningListString(movieIds));
		if(!searchTerm.isBlank()) {
			conditionStr += " movie_name like '%" + searchTerm + "%' or movie_sub_name like '%" + searchTerm + "%' ";
		}
		apiRequest.setConditionStr(conditionStr);
		apiRequest.setOrderBys(List.of("year_release_at ASC"));
		
		try {
			return movieInfoService.getMovieInfo(apiRequest);
		} catch (Exception e) {
			log.error("ERROR call getMovieInfo", e);
		}
		return List.of();
	}


	private List<MovieGenre> getMovieGenre2(List<String> genreTypeIds) {
		if(genreTypeIds.isEmpty()) {
			return List.of();
		}
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = String.format(" genre_id in (%s)", joiningListString(genreTypeIds));
		apiRequest.setConditionStr(conditionStr);
		try {
			return movieGenreService.getMovieGenre(apiRequest);
		} catch (Exception e) {

		}
		return List.of();
	}

	private void generatorRespons(List<MovieInfo> searchTermMovieInfos, Map<String, List<MovieGenre>> mapMovieGenres,
			List<GenreType> genreTypes, GetMovieListResponse response) {
		List<GetMovieListResponse.GenreType> genreTypeResponses = new ArrayList<>();
		for(GenreType genreType: genreTypes) {
			GetMovieListResponse.GenreType genreTypeResponse = new GetMovieListResponse.GenreType();
			BeanUtils.copyProperties(genreType, genreTypeResponse);
			
			List<MovieGenre> movieGenres = mapMovieGenres.getOrDefault(genreType.getId(), List.of());
			List<String> movieIds = movieGenres.stream()
					.map(MovieGenre::getMovieId).
					collect(Collectors.toList());
			
			List<MovieInfo> movieInfos = searchTermMovieInfos.stream()
					.filter(movie -> movieIds.contains(movie.getId()))
					.collect(Collectors.toList());
			
			List<GetMovieListResponse.Movie> movieReponse = movieInfos.stream().map(movieInfo -> {
					GetMovieListResponse.Movie movie = new 	GetMovieListResponse.Movie();
					BeanUtils.copyProperties(movieInfo, movie);
					return movie;
				})
				.collect(Collectors.toList());
			genreTypeResponse.setMovies(movieReponse);
			genreTypeResponses.add(genreTypeResponse);
		}
		
		response.setGenreType(genreTypeResponses);
	}

	private List<GenreType> getGenreType(List<String> genresTypeIds) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		if(!genresTypeIds.isEmpty()) {
			conditionStr = String.format(" id in (%s)", joiningListString(genresTypeIds));
		}
		apiRequest.setConditionStr(conditionStr);
		apiRequest.setOrderBys(List.of("order_score ASC"));
		try {
			return genreTypeService.getGenreType(apiRequest);
		} catch (Exception e) {

		}
		return List.of();
	}

	private String joiningListString(List<String> datas) {
		return datas.stream().map(data -> "'" + data + "'").collect(Collectors.joining(", "));
	}

}
