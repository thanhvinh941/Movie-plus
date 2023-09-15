package com.movieplus.domain.common;

import java.util.List;
import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RRelationalRepository {

    public Flux<Map<String, Object>> findByQueryFlux(Class<?> tableName, List<String> listField, String query, Map<String, Object> parameters);
    
    public Mono<Map<String, Object>> findByQueryMono(Class<?> tableName, List<String> listField, String query, Map<String, Object> parameters);
}
