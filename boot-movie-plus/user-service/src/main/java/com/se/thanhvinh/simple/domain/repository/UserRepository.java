package com.se.thanhvinh.simple.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.se.thanhvinh.simple.domain.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
