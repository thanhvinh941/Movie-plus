package com.movieplus.domain.common;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaListenerRequest {
	private String id;
	private String topic;
	private Map<String, Object> data; 
}
