package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieplus.domain.entity.MovieInfo;

@Repository
public interface MovieInfoRepository extends JpaRepository<MovieInfo, String>{

}
