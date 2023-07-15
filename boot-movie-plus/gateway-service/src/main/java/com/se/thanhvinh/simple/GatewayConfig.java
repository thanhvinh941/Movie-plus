package com.se.thanhvinh.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {

	@Autowired
	AuthFilters filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("user-service", 
						r -> r.path("/user/**")
						.filters(
								f -> f.rewritePath("/(?<path>.*)", "/$\\{path}")
								.filter(filter))
						.uri("http://localhost:8081"))
				
				
				.build();
	}

}
