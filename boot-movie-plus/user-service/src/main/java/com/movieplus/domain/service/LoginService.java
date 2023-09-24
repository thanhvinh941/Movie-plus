package com.movieplus.domain.service;

import com.movieplus.domain.payload.request.LoginRequest;

public interface LoginService {

	Object doLogin(LoginRequest loginRequest) throws Exception;

}
