package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieplus.domain.entity.MovieBanner;

@Repository
public interface MovieBannerRepository extends JpaRepository<MovieBanner, String>{

}
