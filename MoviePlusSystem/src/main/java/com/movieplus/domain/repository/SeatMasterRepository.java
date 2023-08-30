package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieplus.domain.entity.SeatMaster;

@Repository
public interface SeatMasterRepository extends JpaRepository<SeatMaster, String>{

}
