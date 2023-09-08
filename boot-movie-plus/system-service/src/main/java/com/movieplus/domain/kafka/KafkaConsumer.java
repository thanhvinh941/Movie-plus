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

	@KafkaListener(topics = { MoviePlusConstance.KAFKA_WEBSOCKET })
	public void websocketConsume(String message) {
		log.info("Room Received message: " + message);
	}

}
