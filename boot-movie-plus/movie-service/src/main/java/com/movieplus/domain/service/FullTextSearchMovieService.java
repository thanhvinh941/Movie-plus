package com.movieplus.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.controller.external.FullTextSearchMovieController.FullTextSearchMovieRequest;
import com.movieplus.domain.elk.MovieIndex;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FullTextSearchMovieService {

	private final ElasticsearchClient elasticsearchClient;
	private final ObjectMapper objectMapper;

	public Object execute(final FullTextSearchMovieRequest request) throws Exception {
		String term = request.getTerm();
		SearchResponse<Object> search = elasticsearchClient.search(s -> s.index("movie_index")
				.query(q -> q.multiMatch(
						m -> m.fields(List.of("movie_sub_name", "movie_name", "description", "genre_type.display_name"))
								.query(term))),
				Object.class);
		List<Map<String, Object>> response = new ArrayList<>();
		for (Hit<Object> hit : search.hits().hits()) {
			Map<String, Object> record = objectMapper.convertValue(hit.source(), Map.class);
			response.add(Map.of("name", record.get("movie_name"), "id", hit.id()));
			log.info("{}", objectMapper.writeValueAsString(record));
		}
		return response;
	}

}
