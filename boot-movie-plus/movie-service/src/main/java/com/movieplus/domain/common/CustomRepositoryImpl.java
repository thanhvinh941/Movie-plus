package com.movieplus.domain.common;

import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class CustomRepositoryImpl<T, ID> implements CustomRepository<T, ID> {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean update(Map<String, Object> params, Class<T> tableName, ID key) {
		String setQuery = getSetQuery(params) + getUpdateTime();
		String whereQuery = String.format("id = '%s'", key);
		String sqlQuery = String.format("UPDATE %s SET %s WHERE %s", Util.convertSnake(tableName.getSimpleName()),
				setQuery, whereQuery);

		Query updateSql = entityManager.createNativeQuery(sqlQuery, tableName);
		return updateSql.executeUpdate() > 0;
	}

	private String getUpdateTime() {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return ", update_time = '" + LocalDateTime.now().format(formatters) + "'";
	}

	private String getSetQuery(Map<String, Object> params) {
		String setStr = "";
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			setStr += String.format("%s = %s", Util.convertSnake(entry.getKey()),
					getStringValueOfObject(entry.getValue())) + ", ";
		}
		setStr = StringUtils.removeEnd(setStr, ", ");

		return setStr;
	}

	private String getStringValueOfObject(Object value) {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		if (value instanceof String) {
			return "'" + value.toString() + "'";
		} else if (value instanceof LocalDate || value instanceof LocalDateTime) {
			return "'" + ((LocalDateTime) value).format(formatters) + "'";
		} else {
			return value.toString();
		}
	}

	@Override
	public void uDeleteAll(T tableName, List<ID> key) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Object> selectByCondition(Class<?> targetTable, String conditionStr, List<String> listFields,
			Map<String, String> orderBys, Integer limit, Integer offset, boolean isForUpdate) {
		String selectColumn = CollectionUtils.isEmpty(listFields) ? "*" : String.join(", ", listFields);
		String sqlQuery = String.format("SELECT %s FROM %s", selectColumn,
				Util.convertSnake(targetTable.getSimpleName()));

		if (!StringUtils.isBlank(conditionStr)) {
			sqlQuery += String.format(" WHERE %s", conditionStr);
		}

		if (!CollectionUtils.isEmpty(orderBys)) {
			String orderbyStr = "";
			for (Map.Entry<String, String> entry : orderBys.entrySet()) {
				orderbyStr += Util.convertSnake(entry.getKey()) + " " + entry.getValue() + ", ";
			}
			orderbyStr += StringUtils.removeEnd(orderbyStr, ", ");
			sqlQuery += String.format(" ORDER BY %s", orderbyStr);
		}

		if (limit != null && limit > 0) {
			sqlQuery += String.format(" LIMIT %s", limit);
			if (offset != null && offset > 0) {
				sqlQuery += String.format(" OFFSET %s", offset);
			}
		}

		if (isForUpdate) {
			sqlQuery += " FOR UPDATE";
		}

		Query selectSql = entityManager.createNativeQuery(sqlQuery);

		return selectSql.getResultList();
	}

	@Override
	public Long count(Class<T> tableName, String conditionStr) {
		String sqlQuery = String.format("SELECT COUNT(id) FROM %s", Util.convertSnake(tableName.getSimpleName()));
		if (!StringUtils.isBlank(conditionStr)) {
			sqlQuery += String.format(" WHERE %s", conditionStr);
		}

		Query countSql = entityManager.createNativeQuery(sqlQuery);
		return (Long) countSql.getSingleResult();
	}

	@Override
	public List<Object> selectByCondition(Class<?> targetTable, String conditionStr, List<String> listFields) {
		return selectByCondition(targetTable, conditionStr, listFields, null, null, null, false);
	}

	@Override
	public List<Object> selectByCondition(Class<?> targetTable, String conditionStr) {
		return selectByCondition(targetTable, conditionStr, null, null, null, null, false);
	}

}
