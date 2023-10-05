package com.movieplus.controller.ajax;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DynamicGetEntityController {
	
	@Getter
	@Setter
	public static class DynamicGetEntity{
		private String entityName;
		private String conditionStr;
	}
	
	public String doDynamicGetEntity() {
		return "";
	}
}
