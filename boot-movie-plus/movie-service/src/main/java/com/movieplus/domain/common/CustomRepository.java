package com.movieplus.domain.common;

import java.util.List;
import java.util.Map;

public interface CustomRepository<T, ID> {

	boolean update(Map<String, Object> params, Class<T> tableName, ID key);

	void uDeleteAll(T tableName, List<ID> key);

	List<T> selectByCondition(Class<T> tableName, String conditionStr, List<String> listFields, /*field|sort_type*/Map<String, String> orderBy,
			Integer limit, Integer offset, boolean isForUpdate);
	
	Long count(Class<T> tableName, String conditionStr);
}
