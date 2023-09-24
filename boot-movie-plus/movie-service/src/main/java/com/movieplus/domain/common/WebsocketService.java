package com.movieplus.domain.common;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebsocketService {
	
	private final String[] logTitle = {"Websocket"};
    private final SimpMessagingTemplate simpMessagingTemplate;
    
	public void sendToTopic(String topicPath, String id, Object response) throws JsonProcessingException {
		String responseStr = ObjectMapperUtil.writeValueAsString(response);
		log.info("{} send to topic: {} with request: {}",logTitle, topicPath, responseStr);
		simpMessagingTemplate.convertAndSend(topicPath + "/" + id, responseStr);
	}
}
