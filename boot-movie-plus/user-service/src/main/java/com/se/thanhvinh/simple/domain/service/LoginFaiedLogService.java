package com.se.thanhvinh.simple.domain.service;

import java.time.LocalDateTime;

import com.se.thanhvinh.simple.domain.payload.dto.LoginFaiedLogDto;

public interface LoginFaiedLogService {

	LoginFaiedLogDto getLoginFaiedLog(String email, LocalDateTime nowStamp);

}
