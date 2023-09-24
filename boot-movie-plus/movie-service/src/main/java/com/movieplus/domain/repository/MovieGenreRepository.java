package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieplus.domain.entity.MovieGenre;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, String>{

}
