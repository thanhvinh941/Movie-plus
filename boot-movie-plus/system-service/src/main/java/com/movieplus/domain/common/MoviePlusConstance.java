package com.movieplus.domain.common;

public interface MoviePlusConstance {

	enum KeyTypeReturn {
		MOVIE, SITE
	}
	
	//Socket topic start
	public final String SOCKET_ROOM = "/topic/room";
    public final String SOCKET_NOTIFY = "/topic/notify";
    //Socket topic end

    //Kafka topic start
    public final String KAFKA_ROOM = "room";
    public final String KAFKA_NOTIFY = "notify";
    public final String KAFKA_WEBSOCKET = "websocket";
    //Kafka topic end
}
