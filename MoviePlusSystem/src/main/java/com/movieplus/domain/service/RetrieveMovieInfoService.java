package com.movieplus.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.controller.RetrieveMovieInfoController.RetrieveMovieInfoRequest;
import com.movieplus.controller.RetrieveMovieInfoController.RetrieveMovieInfoResponse;
import com.movieplus.domain.entity.DirectorInfo;
import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.entity.MovieBanner;
import com.movieplus.domain.entity.MovieDirector;
import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.entity.MovieInfo;
import com.movieplus.domain.entity.MovieStar;
import com.movieplus.domain.entity.MovieTrailer;
import com.movieplus.domain.entity.StarInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetrieveMovieInfoService {
	
	private final StarInfoService starInfoService;
	private final MovieStarService movieStarService;
	private final GenreTypeService genreTypeService;
	private final MovieInfoService movieInfoService;
	private final MovieGenreService movieGenreService;
	private final MovieBannerService movieBannerService;
	private final DirectorInfoService directorInfoService;
	private final MovieTrailerService movieTrailerService;
	private final MovieDirectorService movieDirectorService;
	
	public void execute(RetrieveMovieInfoRequest request, RetrieveMovieInfoResponse response) {
		List<MovieInfo> movieInfos = getMovieInfo(request.getMovieId());
		if(movieInfos.isEmpty()) {
			
		}
		
		MovieInfo movieInfo = movieInfos.get(0);
		String movieInfoId = movieInfo.getId();
		
		List<MovieDirector> movieDirectors = getMovieDirector(movieInfoId);
		List<String> directorIds = movieDirectors.stream()
				.map(MovieDirector::getDirectorId)
				.collect(Collectors.toList());
		List<DirectorInfo> directorInfos = getDirectorInfo(directorIds);
		List<RetrieveMovieInfoResponse.Director> responseDirectorInfo =  directorInfos.stream()
				.map(d -> {
					RetrieveMovieInfoResponse.Director director = new RetrieveMovieInfoResponse.Director();
					BeanUtils.copyProperties(d, director);
					return director;
				}).collect(Collectors.toList());
		
		List<MovieStar> movieStars = getMovieStar(movieInfoId);
		List<String> starIds = movieStars.stream()
				.map(MovieStar::getStarId)
				.collect(Collectors.toList());
		List<StarInfo> starInfos = getStarInfo(starIds);
		List<RetrieveMovieInfoResponse.StarInfo> responseStar = starInfos.stream()
				.map(s -> {
					RetrieveMovieInfoResponse.StarInfo starInfo = new RetrieveMovieInfoResponse.StarInfo();
					BeanUtils.copyProperties(s, starInfo);
					return starInfo;
				})
				.collect(Collectors.toList());
		
		List<MovieBanner> movieBanners = getMovieBanner(movieInfoId);
		List<String> responseBanners = movieBanners.stream()
				.map(MovieBanner::getBannerSrc)
				.collect(Collectors.toList());
		
		List<MovieTrailer> movieTrailers = getMovieTrailer(movieInfoId);
		List<RetrieveMovieInfoResponse.Trailer> responseTrailers = movieTrailers.stream()
				.map(t -> {
					RetrieveMovieInfoResponse.Trailer trailer = new RetrieveMovieInfoResponse.Trailer();
					BeanUtils.copyProperties(t, trailer);
					return trailer;
				}).collect(Collectors.toList());
		
		List<MovieGenre> movieGenres = getMovieGenre(movieInfoId);
		List<String> genreIds = movieGenres.stream()
				.map(MovieGenre::getGenreId)
				.collect(Collectors.toList());
		List<GenreType> genreTypes = getGenreType(genreIds);
		List<RetrieveMovieInfoResponse.GenreType> responseGenreTypes = genreTypes.stream()
				.map(g -> {
					RetrieveMovieInfoResponse.GenreType genreType = new RetrieveMovieInfoResponse.GenreType();
					BeanUtils.copyProperties(g, genreType);
					return genreType;
				})
				.collect(Collectors.toList());
		
		generatorResponse(movieInfo, responseBanners, responseTrailers, responseGenreTypes, responseDirectorInfo, responseStar, response);
	}

	private void generatorResponse(MovieInfo movieInfo, List<String> responseBanners, 
			List<RetrieveMovieInfoResponse.Trailer> responseTrailers,
			List<RetrieveMovieInfoResponse.GenreType> responseGenreTypes, 
			List<RetrieveMovieInfoResponse.Director> responseDirectorInfo,
			List<RetrieveMovieInfoResponse.StarInfo> responseStar,
			RetrieveMovieInfoResponse response) {
		
		BeanUtils.copyProperties(movieInfo, response);
		response.setBanners(responseBanners);
		response.setTrailers(responseTrailers);
		response.setGenreType(responseGenreTypes);
		response.setDirectors(responseDirectorInfo);
		response.setStarInfos(responseStar);
	}

	private List<StarInfo> getStarInfo(List<String> starIds) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		if(!starIds.isEmpty()) {
			conditionStr = String.format(" id in (%s)", joiningListString(starIds));
		}
		apiRequest.setConditionStr(conditionStr);
		try {
			return starInfoService.getStarInfo(apiRequest);
		} catch (Exception e) {

		}
		return List.of();
	}

	private List<DirectorInfo> getDirectorInfo(List<String> directorIds) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		if(!directorIds.isEmpty()) {
			conditionStr = String.format(" id in (%s)", joiningListString(directorIds));
		}
		apiRequest.setConditionStr(conditionStr);
		try {
			return directorInfoService.getDirectorInfo(apiRequest);
		} catch (Exception e) {

		}
		return List.of();
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

	private List<MovieStar> getMovieStar(String movieInfoId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		conditionStr = String.format(" movie_id = '%s'", movieInfoId);
		apiRequest.setConditionStr(conditionStr);
		
		try {
			return movieStarService.getMovieStar(apiRequest);
		} catch (Exception e) {
			log.error("ERROR call getMovieTrailer", e);
		}
		return List.of();
	}

	private List<MovieDirector> getMovieDirector(String movieInfoId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		conditionStr = String.format(" movie_id = '%s'", movieInfoId);
		apiRequest.setConditionStr(conditionStr);
		
		try {
			return movieDirectorService.getMovieDirector(apiRequest);
		} catch (Exception e) {
			log.error("ERROR call getMovieTrailer", e);
		}
		return List.of();
	}

	private List<MovieTrailer> getMovieTrailer(String movieInfoId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		conditionStr = String.format(" movie_id = '%s'", movieInfoId);
		apiRequest.setConditionStr(conditionStr);
		
		try {
			return movieTrailerService.getMovieTrailer(apiRequest);
		} catch (Exception e) {
			log.error("ERROR call getMovieTrailer", e);
		}
		return List.of();
	}

	private List<MovieBanner> getMovieBanner(String movieInfoId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		conditionStr = String.format(" movie_id = '%s'", movieInfoId);
		apiRequest.setConditionStr(conditionStr);
		
		try {
			return movieBannerService.getMovieBanner(apiRequest);
		} catch (Exception e) {
			log.error("ERROR call getMovieBanner", e);
		}
		return List.of();
	}

	private List<MovieGenre> getMovieGenre(String movieInfoId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = String.format(" movie_id = '%s'", movieInfoId);
		apiRequest.setConditionStr(conditionStr);
		try {
			return movieGenreService.getMovieGenre(apiRequest);
		} catch (Exception e) {

		}
		return List.of();
	}

	private List<MovieInfo> getMovieInfo(String movieId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		conditionStr = String.format(" id = '%s'", movieId);
		apiRequest.setConditionStr(conditionStr);
		
		try {
			return movieInfoService.getMovieInfo(apiRequest);
		} catch (Exception e) {
			log.error("ERROR call getMovieInfo", e);
		}
		return List.of();
	}

	private String joiningListString(List<String> datas) {
		return datas.stream().map(data -> "'" + data + "'").collect(Collectors.joining(", "));
	}
}
