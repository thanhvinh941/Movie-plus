package com.movieplus.domain.common;

import java.util.List;
import java.util.Map;

public interface CustomRepository<T, ID> {

	List<String> updateAll(Map<ID, Map<String, Object>> params, T tableName);

	String update(Map<String, Object> params, T tableName, ID key);

	void uDeleteAll(T tableName, List<ID> key);

	List<?> selectByCondition(T tableName, String conditionStr, List<String> listFields, Map<String, Object> orderBy,
			Integer limit, Integer offset, boolean isForUpdate);
}
