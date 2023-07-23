package com.movieplus.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.service.LoginService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestController
@RequiredArgsConstructor
public class LoginController {

	@Getter
	@Setter
	public class LoginRequest{
		private String userName;
		private String password;
	}
	
	private final LoginService loginService;
	
	@PostMapping("/login")
	public @ResponseBody String doLogin(@RequestBody LoginRequest loginRequest) {
		
		try {
			String result = loginService.doLogin(loginRequest);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "";
	}
}
