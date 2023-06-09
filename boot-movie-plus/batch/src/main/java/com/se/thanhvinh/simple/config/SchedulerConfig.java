package com.se.thanhvinh.simple.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class SchedulerConfig {
	private static final Logger log = LoggerFactory.getLogger(SchedulerConfig.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 500)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}
}
