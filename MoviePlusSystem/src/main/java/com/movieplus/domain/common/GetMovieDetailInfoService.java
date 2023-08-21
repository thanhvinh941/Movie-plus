package com.movieplus.domain.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.domain.common.dto.DirectorDto;
import com.movieplus.domain.common.dto.GenreTypeDto;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.dto.StarInfoDto;
import com.movieplus.domain.common.dto.TrailerDto;
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
import com.movieplus.domain.service.DirectorInfoService;
import com.movieplus.domain.service.GenreTypeService;
import com.movieplus.domain.service.MovieBannerService;
import com.movieplus.domain.service.MovieDirectorService;
import com.movieplus.domain.service.MovieGenreService;
import com.movieplus.domain.service.MovieInfoService;
import com.movieplus.domain.service.MovieStarService;
import com.movieplus.domain.service.MovieTrailerService;
import com.movieplus.domain.service.StarInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetMovieDetailInfoService {

	private final String CLASS_NAME = "GetMovieDetailInfoService";
	private final StarInfoService starInfoService;
	private final MovieStarService movieStarService;
	private final GenreTypeService genreTypeService;
	private final MovieInfoService movieInfoService;
	private final MovieGenreService movieGenreService;
	private final MovieBannerService movieBannerService;
	private final DirectorInfoService directorInfoService;
	private final MovieTrailerService movieTrailerService;
	private final MovieDirectorService movieDirectorService;

	public MovieDetailInfoDto getMovieDetailInfo(String movieId) {
		MovieDetailInfoDto movieDetailInfoDto = new MovieDetailInfoDto();
		List<MovieInfo> movieInfos = getMovieInfo(movieId);
		if (movieInfos.isEmpty()) {

		}

		MovieInfo movieInfo = movieInfos.get(0);
		String movieInfoId = movieInfo.getId();

		List<MovieDirector> movieDirectors = getMovieDirector(movieInfoId);
		List<String> directorIds = movieDirectors.stream().map(MovieDirector::getDirectorId).toList();
		List<DirectorInfo> directorInfos = getDirectorInfo(directorIds);
		List<DirectorDto> responseDirectorInfo = directorInfos.stream().map(d -> {
			DirectorDto director = new DirectorDto();
			BeanUtils.copyProperties(d, director);
			return director;
		}).toList();

		List<MovieStar> movieStars = getMovieStar(movieInfoId);
		List<String> starIds = movieStars.stream().map(MovieStar::getStarId).toList();
		List<StarInfo> starInfos = getStarInfo(starIds);
		List<StarInfoDto> responseStar = starInfos.stream().map(s -> {
			StarInfoDto starInfo = new StarInfoDto();
			BeanUtils.copyProperties(s, starInfo);
			return starInfo;
		}).toList();

		List<MovieBanner> movieBanners = getMovieBanner(movieInfoId);
		List<String> responseBanners = movieBanners.stream().map(MovieBanner::getBannerSrc).toList();

		List<MovieTrailer> movieTrailers = getMovieTrailer(movieInfoId);
		List<TrailerDto> responseTrailers = movieTrailers.stream().map(t -> {
			TrailerDto trailer = new TrailerDto();
			BeanUtils.copyProperties(t, trailer);
			return trailer;
		}).toList();

		List<MovieGenre> movieGenres = getMovieGenre(movieInfoId);
		List<String> genreIds = movieGenres.stream().map(MovieGenre::getGenreId).toList();
		List<GenreType> genreTypes = getGenreType(genreIds);
		List<GenreTypeDto> responseGenreTypes = genreTypes.stream().map(g -> {
			GenreTypeDto genreType = new GenreTypeDto();
			BeanUtils.copyProperties(g, genreType);
			return genreType;
		}).toList();

		return generatorResponse(movieDetailInfoDto, movieInfo, responseDirectorInfo, responseStar, responseBanners,
				responseTrailers, responseGenreTypes);
	}

	private MovieDetailInfoDto generatorResponse(MovieDetailInfoDto movieDetailInfoDto, MovieInfo movieInfo,
			List<DirectorDto> responseDirectorInfo, List<StarInfoDto> responseStar, List<String> responseBanners,
			List<TrailerDto> responseTrailers, List<GenreTypeDto> responseGenreTypes) {
		BeanUtils.copyProperties(movieInfo, movieDetailInfoDto);
		movieDetailInfoDto.setBanners(responseBanners);
		movieDetailInfoDto.setTrailers(responseTrailers);
		movieDetailInfoDto.setGenreType(responseGenreTypes);
		movieDetailInfoDto.setDirectors(responseDirectorInfo);
		movieDetailInfoDto.setStarInfos(responseStar);
		return movieDetailInfoDto;
	}

	private List<StarInfo> getStarInfo(List<String> starIds) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		if (!starIds.isEmpty()) {
			conditionStr = String.format(" id in (%s)", joiningListString(starIds));
		}
		apiRequest.setConditionStr(conditionStr);
		try {
			return starInfoService.getStarInfo(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getStarInfo", CLASS_NAME, e);
		}
		return List.of();
	}

	private List<DirectorInfo> getDirectorInfo(List<String> directorIds) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		if (!directorIds.isEmpty()) {
			conditionStr = String.format(" id in (%s)", joiningListString(directorIds));
		}
		apiRequest.setConditionStr(conditionStr);
		try {
			return directorInfoService.getDirectorInfo(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getGenreType", CLASS_NAME, e);
		}
		return List.of();
	}

	private List<GenreType> getGenreType(List<String> genresTypeIds) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		if (!genresTypeIds.isEmpty()) {
			conditionStr = String.format(" id in (%s)", joiningListString(genresTypeIds));
		}
		apiRequest.setConditionStr(conditionStr);
		apiRequest.setOrderBys(List.of("order_score ASC"));
		try {
			return genreTypeService.getGenreType(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getGenreType", CLASS_NAME, e);
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
			log.error("{} ERROR call getMovieStar", CLASS_NAME, e);
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
			log.error("{} ERROR call getMovieDirector", CLASS_NAME, e);
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
			log.error("{} ERROR call getMovieTrailer", CLASS_NAME, e);
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
			log.error("{} ERROR call getMovieBanner", CLASS_NAME, e);
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
			log.error("{} ERROR call getMovieGenre", CLASS_NAME, e);
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
			log.error("{} ERROR call getMovieInfo", CLASS_NAME, e);
		}
		return List.of();
	}

	private String joiningListString(List<String> datas) {
		return datas.stream().map(data -> "'" + data + "'").collect(Collectors.joining(", "));
	}
}
