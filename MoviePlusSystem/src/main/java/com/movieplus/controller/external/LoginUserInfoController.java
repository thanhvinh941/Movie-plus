package com.movieplus.controller.external;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.payload.request.LoginRequest;
import com.movieplus.domain.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginUserInfoController {

	private final LoginService loginService;
	
	@PostMapping("/login")
	@ResponseBody
	public String doLogin(@RequestBody LoginRequest loginRequest) {
		
		try {
			Object result = loginService.doLogin(loginRequest);
			return GeneratorCommonUtil.getResponseBodySuccess(result);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
		
	}
}
