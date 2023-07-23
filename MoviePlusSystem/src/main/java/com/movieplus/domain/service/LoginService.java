package com.movieplus.domain.service;

import com.movieplus.controller.LoginController.LoginRequest;

public interface LoginService {

	String doLogin(LoginRequest loginRequest);

}
