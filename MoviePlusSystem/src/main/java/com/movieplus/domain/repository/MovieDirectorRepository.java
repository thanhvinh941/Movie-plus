package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieplus.domain.entity.MovieDirector;

@Repository
public interface MovieDirectorRepository extends JpaRepository<MovieDirector, String>{

}
