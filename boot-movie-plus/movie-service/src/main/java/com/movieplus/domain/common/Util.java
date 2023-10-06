package com.movieplus.domain.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	private Util() {
		throw new IllegalStateException("Utility class");
	}

	public static String convertSnake(String str) {
		if (str.contains("_") || str.isEmpty()) {
			return str;
		} else {

			if (Character.isUpperCase(str.charAt(0))) {
				char[] c = str.toCharArray();
				c[0] += 32;
				str = new String(c);
			}
			str = str.replaceAll("([^A-Z])([A-Z0-9])", "$1_$2").replaceAll("([A-Z]+)([A-Z0-9][^A-Z]+)", "$1_$2")
					.replaceAll("([0-9]+)([a-zA-Z]+)", "$1_$2").toLowerCase();

		}
		return str;
	}

	public static String buildQueryIn(Collection<String> datas) {
		return datas.stream().map(data -> "'" + data + "'").collect(Collectors.joining(","));
	}
	
	public List<Map<String, Object>> transferObjectToMap(List<Object> results, List<String> fields){
		return results.stream().map(r -> {
			List<?> result = new ObjectMapper().convertValue(r, List.class);
			Map<String, Object> map = new HashMap<>();
			for (int i = 0; i < fields.size(); i++) {
				map.put(fields.get(i), result.get(i));
			}
			return map;
		}).toList();
	}
}
