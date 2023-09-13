package com.movieplus.domain.kafka;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.KafkaListenerRequest;
import com.movieplus.domain.common.ObjectMapperUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerCommon {

	private final String[] logTitle = {"KafkaProducer"};
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    public void doPublish(String topic, KafkaListenerRequest request) {
    	String message = ObjectMapperUtil.writeValueAsString(request);
    	CompletableFuture<SendResult<String, String>> future  = kafkaTemplate.send(topic, message);
    	future.whenComplete((result, throwable) -> {
    		if (result != null){
    			log.info("{} doPublish success for topic: {} with message: {}", logTitle, topic, message);
            } else if (throwable != null){
            	log.error("{} doPublish error for topic: {} with message: {}", logTitle, topic, message);
            }
        });
    }
}
