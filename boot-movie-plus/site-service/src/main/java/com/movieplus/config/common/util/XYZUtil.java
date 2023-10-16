package com.movieplus.config.common.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class XYZUtil {

	private XYZUtil() {
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

	public static List<Map<String, Object>> transferObjectToMap(List<Object> results, List<String> fields) {
		return results.stream().map(r -> {
			List<?> result = new ObjectMapper().convertValue(r, List.class);
			Map<String, Object> map = new HashMap<>();
			for (int i = 0; i < fields.size(); i++) {
				map.put(fields.get(i), result.get(i));
			}
			return map;
		}).toList();
	}

	public static List<String> getFieldNames(Class<?> clazz) {
		List<String> fieldNames = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fieldNames.add(fields[i].getName());
		}
		return fieldNames;
	}
	
	@SuppressWarnings("serial")
	public static class MillisOrLocalDateTimeDeserializer extends LocalDateTimeDeserializer {
		public MillisOrLocalDateTimeDeserializer() {
			super(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		}

		@Override
		public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
			if (parser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
				long value = parser.getValueAsLong();
				Instant instant = Instant.ofEpochMilli(value);

				return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
			}

			return super.deserialize(parser, context);
		}

	}
	
	public static ObjectMapper initObjectMapper(){
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addDeserializer(LocalDateTime.class, new MillisOrLocalDateTimeDeserializer());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(javaTimeModule);
		return objectMapper;
	}
	
	public static void copyProperty(Object resource, Object trg) {
		ObjectMapper objectMapper = initObjectMapper();
		Map<String, Object> resourceMap = objectMapper.convertValue(resource, new TypeReference<Map<String, Object>>() {});
		for (Map.Entry<String, Object> entry : resourceMap.entrySet()) {
			copyProperty(entry, trg);
		}
	}
	
	private static void copyProperty(Map.Entry<String, Object> entry, Object trg) {
		try {			
			BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);
			Class<?> propertyType = trgWrap.getPropertyType(entry.getKey());
			Object value = entry.getValue();
			if (propertyType != null && propertyType.equals(LocalDateTime.class) && (entry.getValue() instanceof Timestamp)) {
				value = LocalDateTime.ofInstant(((Timestamp) entry.getValue()).toInstant(), ZoneOffset.ofHours(0));
			}
			trgWrap.setPropertyValue(entry.getKey(), value);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
