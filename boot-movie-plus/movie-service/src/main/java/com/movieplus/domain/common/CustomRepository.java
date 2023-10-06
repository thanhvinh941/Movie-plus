package com.movieplus.domain.common;

import java.util.List;
import java.util.Map;

public interface CustomRepository<T, ID> {

	boolean update(Map<String, Object> params, Class<T> tableName, ID key);

	void uDeleteAll(T tableName, List<ID> key);

	List<?> selectByCondition(Class<?> targetTable, String conditionStr, List<String> listFields,
			Map<String, String> orderBys, Integer limit, Integer offset, boolean isForUpdate);

	List<?> selectByCondition(Class<?> targetTable, String conditionStr, List<String> listFields);

	List<?> selectByCondition(Class<?> targetTable, String conditionStr);

	Long count(Class<T> tableName, String conditionStr);
}
