package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieplus.domain.entity.RoomSeat;
import com.movieplus.domain.entity.SeatMaster;

@Repository
public interface RoomSeatRepository extends JpaRepository<RoomSeat, String>{

}
