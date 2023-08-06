package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieplus.domain.entity.RoomSeat;

@Repository
public interface RoomSeatRepository extends JpaRepository<RoomSeat, String>{

}
