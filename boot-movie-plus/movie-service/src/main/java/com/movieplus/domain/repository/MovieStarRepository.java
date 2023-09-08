package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieplus.domain.entity.MovieStar;

@Repository
public interface MovieStarRepository extends JpaRepository<MovieStar, String>{

}
