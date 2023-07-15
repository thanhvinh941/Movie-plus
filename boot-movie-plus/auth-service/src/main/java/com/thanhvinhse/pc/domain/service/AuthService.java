package com.thanhvinhse.pc.domain.service;

import com.thanhvinhse.pc.domain.common.ApiResponseEntity;
import com.thanhvinhse.pc.domain.payload.dto.request.LoginRequest;

public interface AuthService {

	ApiResponseEntity loginExecute(LoginRequest loginRequest, ApiResponseEntity response);

}
