package com.se.thanhvinh.simple.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.se.thanhvinh.simple.domain.entity.UserVerify;

public interface VerifyRepository extends JpaRepository<UserVerify, String>{

}