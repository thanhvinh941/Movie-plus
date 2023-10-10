package com.movieplus.config.common.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import com.movieplus.domain.common.ObjectMapperUtil;

import lombok.Getter;
import lombok.Setter;

public class GeneratorUtil {

	private GeneratorUtil() {
		throw new IllegalStateException("Utility class");
	}
	
	public static class ExternalAPI<T>{
		
		@Getter
		@Setter
		public static class ExternalApiResponse<T>{
			private T results;
			private List<String> errors;
		}
		
		public static <T> ResponseEntity<ExternalApiResponse<T>> createSuccessResponse(T data){
			HttpStatus defaultStatus = HttpStatus.OK;
			if(data instanceof Collection<?> && CollectionUtils.isEmpty((Collection<?>) data)) {
				defaultStatus = HttpStatus.NO_CONTENT;
			}
			
			if(data instanceof String || data instanceof Boolean) {
				defaultStatus = HttpStatus.CREATED;
			}
			
			ExternalApiResponse<T> response = new ExternalApiResponse<T>();
			response.setResults(data);
			
			return ResponseEntity.status(defaultStatus).body(response);
		}
		
		public static <T> ResponseEntity<ExternalApiResponse<T>> createErrorClientResponse(List<String> errors){
			HttpStatus defaultStatus = HttpStatus.BAD_REQUEST;
			
			ExternalApiResponse<T> response = new ExternalApiResponse<>();
			response.setErrors(errors);
			
			return ResponseEntity.status(defaultStatus).body(response);
		}
		
		public static <T> ResponseEntity<ExternalApiResponse<T>> createErrorServerResponse(List<String> errors){
			HttpStatus defaultStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			
			ExternalApiResponse<T> response = new ExternalApiResponse<>();
			response.setErrors(errors);
			
			return ResponseEntity.status(defaultStatus).body(response);
		}
	}
	
	public static class InternalAPI{
		
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
		for (Map.Entry<String,Object> entry : condition.entrySet()) {
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
}
