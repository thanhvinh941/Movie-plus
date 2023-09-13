package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieplus.domain.entity.UserToken;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, String>{

}
