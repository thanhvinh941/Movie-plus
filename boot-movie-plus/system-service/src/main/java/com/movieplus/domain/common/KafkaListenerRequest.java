package com.movieplus.domain.common;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaListenerRequest {
	private String id;
	private String topic;
	private List<Map<String, Object>> data; 
}
