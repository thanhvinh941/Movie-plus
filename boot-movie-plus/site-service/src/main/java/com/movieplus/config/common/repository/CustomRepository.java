package com.movieplus.config.common.repository;

import java.util.List;
import java.util.Map;

import com.movieplus.domain.entity.SeatMaster;

public interface CustomRepository {

//	boolean update(Map<String, Object> params, Class<T> tableName, ID key);
//
//	void uDeleteAll(T tableName, List<ID> key);

	/*
	 * @param Class<T> targetTable
	 * @param String conditionStr
	 * @param Map<String, String> orderBys
	 * @param Integer limit
	 * @param Integer offset
	 * @param boolean isForUpdate
	 * */
	public <T> List<T> selectByCondition(Class<T> targetTable, String conditionStr, Map<String, String> orderBys,
			Integer limit, Integer offset, boolean isForUpdate) throws Exception;

	List<?> selectByCondition(String tableName, String conditionStr, List<String> listFields,
			Map<String, String> orderBys, Integer limit, Integer offset, boolean isForUpdate) throws Exception;

	List<?> selectByCondition(String tableName, String conditionStr, List<String> listFields) throws Exception;

	<T> List<T> selectByCondition(Class<T> targetTable, String conditionStr) throws Exception;

	<T> List<T> selectByCondition(Class<T> targetTable) throws Exception;

	Long count(Class<?> targetTable, String conditionStr);

	Long count(String tableName, String conditionStr);

	String insertRecords(String tableName, Map<String, Object> records) throws Exception;

	String updateRecords(String tableName, Map<String, Object> records, String id) throws Exception;

	boolean deleteById(String tableName, String id) throws Exception;

	String insertRecords(Object entity) throws Exception;
}
