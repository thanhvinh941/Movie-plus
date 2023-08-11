package com.movieplus.controller.webui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.movieplus.domain.entity.MovieInfo;

@Controller
@RequestMapping("/webui/movie")
public class MovieWebUIController {

	@GetMapping("/create")
	public ModelAndView initCreen(ModelAndView mav) {
		
		mav.addObject("movieInfo", new MovieInfo());
		mav.addObject("genreType", mav);
		mav.setViewName("/movie/create");
		return mav;
	}
}
