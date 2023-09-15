package com.movieplus.domain.repository.reactive;

import java.util.List;
import java.util.Map;

import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.movieplus.domain.common.RRelationalRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MovieInfoRelationalRepository implements RRelationalRepository{

	DatabaseClient client = DatabaseClient.create(null);
	
	@Override
	public Flux<Map<String, Object>> findByQueryFlux(Class<?> tableName, List<String> listField,String query, Map<String, Object> parameters) {
		String sqlQuery = "";
		Query query2 = Query.query(null);
		Select select = Select.builder().select(null);
		return client.sql(sqlQuery)
				.fetch()
				.all();
	}

	@Override
	public Mono<Map<String, Object>> findByQueryMono(Class<?> tableName, List<String> listField, String query, Map<String, Object> parameters) {
		String sqlQuery = "";
		return client.sql(sqlQuery)
				.fetch()
				.first();
	}

	private String getTableName(String entityName) {
		return convertSnake(entityName);
	}
	
	public static String convertSnake(String str) {
		if (str.contains("_") || str.isEmpty()) {
			return str;
		} else {
			
			//Pascal の場合まずCamelCaseにする
			if(Character.isUpperCase(str.charAt(0))) {
				char c[] = str.toCharArray();
				c[0] += 32;
				str = new String(c);
				//str = CaseUtils.toCamelCase(str, false, null);				
			}
			//camelcaseからsnakecase にする
			str=str.replaceAll("([^A-Z])([A-Z0-9])", "$1_$2") // standard replace
            .replaceAll("([A-Z]+)([A-Z0-9][^A-Z]+)", "$1_$2") // last letter after full uppercase.
             .replaceAll("([0-9]+)([a-zA-Z]+)", "$1_$2").toLowerCase(); // letters after numbers
			
		}
		return str;
	}
}
