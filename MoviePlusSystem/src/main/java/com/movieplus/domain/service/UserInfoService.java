package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.UserInfo;

public interface UserInfoService {

	UserInfo getUserByUserName(String userName);
	
	List<String> save(List<UserInfo> records) throws Exception;

}
