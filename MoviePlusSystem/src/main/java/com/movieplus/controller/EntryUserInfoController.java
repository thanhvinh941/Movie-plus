package com.movieplus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.service.RegistService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class EntryUserInfoController {

	@Getter
	@Setter
	public static class RegistRequest{
		private String username;
		private String email;
		private String fname;
		private String lname;
		private String password;
	}

	private final RegistService registService;
	
	@PostMapping("/regist")
	@ResponseBody
	public String doRegist(@RequestBody RegistRequest request) {
		
		try {
			String result = registService.doRegist(request);
			return GeneratorCommonUtil.getResponseBodySuccess(result);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
