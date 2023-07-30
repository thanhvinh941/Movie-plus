package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieplus.domain.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String>{

}
