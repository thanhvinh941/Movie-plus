package com.movieplus.domain.kafka;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.KafkaListenerRequest;
import com.movieplus.domain.common.MoviePlusConstance;
import com.movieplus.domain.common.WebsocketService;
import com.movieplus.domain.websocket.dto.TopicRoomRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumerCommon {

	private final WebsocketService websocketService;
	private final ObjectMapper objectMapper;

//	@KafkaListener(topics = { MoviePlusConstance.KAFKA_WEBSOCKET })
//	public void websocketConsume(String message) throws JsonProcessingException {
//		log.info("Websocket Received message: " + message);
//		KafkaListenerRequest request = objectMapper.readValue(message, KafkaListenerRequest.class);
//		String topicTarget = request.getTopic();
//		switch (topicTarget) {
//			case MoviePlusConstance.KAFKA_ROOM: {
//				String roomId = request.getId();
//				List<TopicRoomRequest> topicRoomRequest = objectMapper.convertValue(request.getData(), new TypeReference<List<TopicRoomRequest>>() {});
//				websocketService.sendToTopic(MoviePlusConstance.SOCKET_ROOM, roomId, topicRoomRequest);
//				break;
//			}
//			case MoviePlusConstance.KAFKA_NOTIFY: {
//				//TODO convert and sent to client
//				break;
//			}
//		
//			default:
//				throw new IllegalArgumentException("Unexpected value: " + topicTarget);
//		}
//	}

}
