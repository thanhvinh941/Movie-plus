package com.movieplus.config.common.repository;

import java.util.List;
import java.util.Map;

public interface CustomRepository{

//	boolean update(Map<String, Object> params, Class<T> tableName, ID key);
//
//	void uDeleteAll(T tableName, List<ID> key);

	List<?> selectByCondition(Class<?> targetTable, String conditionStr,
			Map<String, String> orderBys, Integer limit, Integer offset, boolean isForUpdate) throws Exception;
	
	List<?> selectByCondition(String tableName, String conditionStr, List<String> listFields,
			Map<String, String> orderBys, Integer limit, Integer offset, boolean isForUpdate) throws Exception;

	List<?> selectByCondition(String tableName, String conditionStr, List<String> listFields) throws Exception;
	
	List<?> selectByCondition(Class<?> targetTable, String conditionStr) throws Exception;
	
	List<?> selectByCondition(Class<?> targetTable) throws Exception;
	
	Long count(Class<?> targetTable, String conditionStr);
	
	Long count(String tableName, String conditionStr);

	String insertRecords(String tableName, Map<String, Object> records) throws Exception;

	String updateRecords(String tableName, Map<String, Object> records, String id) throws Exception;
	
	boolean deleteById(String tableName, String id) throws Exception;
}
