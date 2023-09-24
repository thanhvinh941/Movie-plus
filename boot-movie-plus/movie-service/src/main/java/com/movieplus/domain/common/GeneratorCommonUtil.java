package com.movieplus.domain.common;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.movieplus.domain.payload.response.ApiResponse;

public class GeneratorCommonUtil {

	private GeneratorCommonUtil() {
		throw new IllegalStateException("Utility class");
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
		condCol = Util.convertSnake(key) + " = " + val;
		return condCol;
	}
}
