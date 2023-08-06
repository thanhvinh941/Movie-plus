package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieplus.domain.entity.ShowTime;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, String>{

}
