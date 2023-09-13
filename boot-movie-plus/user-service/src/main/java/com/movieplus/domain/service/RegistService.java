package com.movieplus.domain.service;

import com.movieplus.controller.external.RegistUserInfoController.RegistRequest;

public interface RegistService {

	String doRegist(RegistRequest request) throws Exception;

}
