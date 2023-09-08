package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieplus.domain.entity.MovieTrailer;

public interface MovieTrailerRepository extends JpaRepository<MovieTrailer, String>{

}
