package com.movieplus.domain.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.movieplus.domain.common.MoviePlusConstance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

	@KafkaListener(topics = { MoviePlusConstance.KAFKA_ROOM })
	public void roomConsume(String message) {
		log.info("Room Received message: " + message);
	}

	@KafkaListener(topics = { MoviePlusConstance.KAFKA_NOTIFY })
	public void notifyConsume(String message) {
		log.info("Motify Received message: " + message);
	}
}
