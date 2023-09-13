package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieplus.domain.entity.DirectorInfo;

public interface DirectorInfoRepository extends JpaRepository<DirectorInfo, String>{

}
