package com.movieplus.controller.webui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.movieplus.domain.service.MovieWebUIService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Controller
@RequiredArgsConstructor
@RequestMapping("/webui/movie")
public class MovieWebUIController {

	private final MovieWebUIService movieWebUIService;
	
	@Getter
	@Setter
	public static class GenreType{
		private String id;
		private String displayName;
	}
	
	@Getter
	@Setter
	public static class Trailer{
		private String trailerUrl;
		private String trailerTitle;
	}
	
	@Getter
	@Setter
	public static class MovieInfo{
		private String id;
		private String movieName;
		private String movieSubName;
		private long durationMin;
		private String description;
		private String thumnail;
		private Long yearReleaseAt;
		private List<String> banners;
		
	}
	
	@GetMapping("/create")
	public ModelAndView initCreen(ModelAndView mav) {
		List<GenreType> genreTypes = movieWebUIService.getGenreType();
		mav.addObject("movieInfo", new MovieInfo());
		mav.addObject("genreTypes", genreTypes);
		mav.setViewName("/movie/create");
		return mav;
	}
}
