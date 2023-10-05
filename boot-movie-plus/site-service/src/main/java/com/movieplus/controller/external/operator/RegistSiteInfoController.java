package com.movieplus.controller.external.operator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.MessageManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class RegistSiteInfoController {

	private final String[] logTitle = {"RetrieveMovieInfo"}; 
	private final MessageManager messageManager;
	
}
