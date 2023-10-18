package com.movieplus.config.common.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.movieplus.domain.common.ObjectMapperUtil;

import lombok.Getter;
import lombok.Setter;

public class GeneratorUtil {

	private static String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private GeneratorUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static class ExternalAPI {

		public static ResponseEntity<String> createSuccessResponse(Object data) throws JsonProcessingException {
			HttpStatus defaultStatus = HttpStatus.OK;
			if (data instanceof Collection<?> && CollectionUtils.isEmpty((Collection<?>) data)) {
				defaultStatus = HttpStatus.NO_CONTENT;
			}
			
			if (data instanceof String || data instanceof Boolean) {
				defaultStatus = HttpStatus.CREATED;
			}
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("status", 1);
			map.put("data", data);
			return createResponse(map, defaultStatus);
		}

		public static ResponseEntity<String> createErrorClientResponse(List<String> errors) throws JsonProcessingException {
			HttpStatus defaultStatus = HttpStatus.BAD_REQUEST;
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("status", 0);
			map.put("errors", errors);
			return createResponse(map, defaultStatus);
		}

		public static ResponseEntity<String> createErrorServerResponse(List<String> errors)
				throws JsonProcessingException {
			HttpStatus defaultStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("status", 0);
			map.put("errors", errors);
			return createResponse(map, defaultStatus);
		}
		
		public static ResponseEntity<String> createResponse(Map<String, Object> data, HttpStatus httpStatus) throws JsonProcessingException{
			return new ResponseEntity<>(initObjectMapper().writeValueAsString(data), httpStatus);
		}
	}

	public static class InternalAPI {

		@Getter
		@Setter
		public static class ApiResponse {

			private Object data;
			private int status;
			private List<String> errors;
		}

		public static String getResponseBodySuccess(Object data) {
			ApiResponse response = new ApiResponse();
			response.setData(data);
			response.setStatus(1);
			response.setErrors(List.of());

			return ObjectMapperUtil.writeValueAsString(response);
		}

		public static String getResponseBodyError(List<String> errors) {
			ApiResponse response = new ApiResponse();
			response.setData(null);
			response.setStatus(0);
			response.setErrors(errors);

			return ObjectMapperUtil.writeValueAsString(response);
		}
	}

	public static String joiningListString(Collection<String> datas) {
		return datas.stream().map(data -> "'" + data + "'").collect(Collectors.joining(", "));
	}

	public static String getWhereSQL(List<Map<String, Object>> conditions) {
		if (conditions == null) {
			return null;
		} else {
			String sql = "";
			if (!CollectionUtils.isEmpty(conditions)) {
				StringBuilder sqlBuilder = new StringBuilder();
				for (Map<String, Object> condition : conditions) {
					sqlBuilder.append(" ( " + getWhereAndSQL(condition) + " ) OR ");
				}
				sql = StringUtils.removeEnd(sqlBuilder.toString(), " OR ");
			}
			return sql;
		}
	}

	private static String getWhereAndSQL(Map<String, Object> condition) {
		String sql = "";
		StringBuilder sqlBuilder = new StringBuilder();
		for (Map.Entry<String, Object> entry : condition.entrySet()) {
			sqlBuilder.append(getConditionColumn(entry.getKey(), entry.getValue()) + " AND ");
		}
		sql = StringUtils.removeEnd(sqlBuilder.toString(), " AND ");
		return sql;
	}

	private static String getConditionColumn(String key, Object value) {
		String condCol = " ";
		String val = value.toString();
		if (value instanceof String) {
			val = "'" + val + "'";
		}
		condCol = XYZUtil.convertSnake(key) + " = " + val;
		return condCol;
	}

	private static ObjectMapper initObjectMapper() {
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addDeserializer(LocalDateTime.class, new MillisOrLocalDateTimeDeserializer());
		javaTimeModule.addSerializer(Date.class, new DateCustomSerializer());
		javaTimeModule.addSerializer(Timestamp.class, new TimestampCustomSerializer());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(javaTimeModule);
		return objectMapper;
	}

	public static class TimestampCustomSerializer extends JsonSerializer<Timestamp> {

		@Override
		public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
				String s = sdf.format(value);
				gen.writeString(s);
			} catch (DateTimeParseException e) {
				System.err.println(e);
				gen.writeString("");
			}
		}
		
	}
	
	public static class DateCustomSerializer extends JsonSerializer<Date> {

		@Override
		public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
				String s = sdf.format(value);
				gen.writeString(s);
			} catch (DateTimeParseException e) {
				System.err.println(e);
				gen.writeString("");
			}
		}
	}

	@SuppressWarnings("serial")
	public static class MillisOrLocalDateTimeDeserializer extends LocalDateTimeDeserializer {
		public MillisOrLocalDateTimeDeserializer() {
			super(DateTimeFormatter.ofPattern(DATE_PATTERN));
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
}
