package com.movieplus.controller;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BachController {

//	@Scheduled(cron = "*/10 * * * * *")
	public void second() throws Exception {
		System.out.println("====>> print method()..." + (new Date()).toString());
	}
}