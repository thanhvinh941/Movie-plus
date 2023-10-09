package com.movieplus.domain.common;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomRepositoryImpl implements CustomRepository {

	private final String[] logTitle = { "CustomRepository" };

	@PersistenceContext
	private EntityManager entityManager;
	private final ObjectMapper objectMapper;
	private final MessageManager messageManager;

//	@Override

//	public boolean update(Map<String, Object> params, Class<T> tableName, ID key) {
//		String setQuery = getSetQuery(params) + getUpdateTime();
//		String whereQuery = String.format("id = '%s'", key);
//		String sqlQuery = String.format("UPDATE %s SET %s WHERE %s", Util.convertSnake(tableName.getSimpleName()),
//				setQuery, whereQuery);
//
//		Query updateSql = entityManager.createNativeQuery(sqlQuery, tableName);
//		return updateSql.executeUpdate() > 0;
//	}

	private String getUpdateTime() {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return ", update_time = '" + LocalDateTime.now().format(formatters) + "'";
	}

//	private String getSetQuery(Map<String, Object> params) {
//		String setStr = "";
//		for (Map.Entry<String, Object> entry : params.entrySet()) {
//			setStr += String.format("%s = %s", Util.convertSnake(entry.getKey()),
//					getStringValueOfObject(entry.getValue())) + ", ";
//		}
//		setStr = StringUtils.removeEnd(setStr, ", ");
//
//		return setStr;
//	}

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
	public List<?> selectByCondition(Class<?> targetTable, String conditionStr, Map<String, String> orderBys,
			Integer limit, Integer offset, boolean isForUpdate) throws Exception {
		return selectByCondition(targetTable.getSimpleName(), conditionStr, Util.getFieldNames(targetTable), orderBys,
				limit, offset, isForUpdate);
	}

	public List<?> selectByCondition(String tableName, String conditionStr, final List<String> listFields,
			Map<String, String> orderBys, Integer limit, Integer offset, boolean isForUpdate) throws Exception {
		String selectColumn = listFields.stream().map(s -> Util.convertSnake(s)).collect(Collectors.joining(", "));
		String sqlQuery = String.format("SELECT %s FROM %s", selectColumn, Util.convertSnake(tableName));

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

		try {
			List<?> resultList = selectSql.getResultList();
			List<Map<String, Object>> response = resultList.stream().map(r -> {
				if (listFields.size() > 1) {
					Map<String, Object> map = new HashMap<>();
					List<?> result = objectMapper.convertValue(r, List.class);
					for (int i = 0; i < listFields.size(); i++) {
						map.put(listFields.get(i), result.get(i));
					}
					return map;
				}

				return new HashMap<String, Object>() {
					{
						put(listFields.get(0), r);
					}
				};
			}).toList();

			return response;
		} catch (Exception e) {
			log.error("{} ERROR execute: {}", logTitle, e);
			String errorMessage = messageManager.getMessage("SQL_Unknow_Exception", null);
			if (e instanceof SQLGrammarException) {
				Object[] datas = { tableName, listFields };
				errorMessage = messageManager.getMessage("SQL_Grammar_Exception", datas);
			}

			throw new Exception(errorMessage);
		}
	}

	@Override
	public List<?> selectByCondition(Class<?> targetTable, String conditionStr) throws Exception {
		return selectByCondition(targetTable, conditionStr, null, null, null, false);
	}

	@Override
	public List<?> selectByCondition(String tableName, String conditionStr, List<String> listFields) throws Exception {
		return selectByCondition(tableName, conditionStr, listFields, null, null, null, false);
	}

	@Override
	public List<?> selectByCondition(Class<?> targetTable) throws Exception {
		return selectByCondition(targetTable.getSimpleName(), null, null, null, null, null, false);
	}

	@Override
	public Long count(String tableName, String conditionStr) {
		String sqlQuery = String.format("SELECT COUNT(id) FROM %s", Util.convertSnake(tableName));
		if (!StringUtils.isBlank(conditionStr)) {
			sqlQuery += String.format(" WHERE %s", conditionStr);
		}

		Query countSql = entityManager.createNativeQuery(sqlQuery);
		return (Long) countSql.getSingleResult();
	}

	@Override
	public Long count(Class<?> targetTable, String conditionStr) {
		return count(targetTable.getSimpleName(), conditionStr);
	}

	@Override
	@Modifying
	@Transactional
	public String insertRecords(String tableName, Map<String, Object> records) throws Exception {

		String id = UUID.randomUUID().toString();
		String columes = String.format("%s, ", "id");
		String values = "?, ";
		for (Map.Entry<String, Object> entry : records.entrySet()) {
			columes += Util.convertSnake(entry.getKey()) + ", ";
			values += "? , ";
		}

		columes = StringUtils.removeEnd(columes, ", ");
		values = StringUtils.removeEnd(values, ", ");

		String sqlQuery = String.format("INSERT INTO %s (%s) VALUES (%s)", Util.convertSnake(tableName), columes,
				values);
		Query insertSql = entityManager.createNativeQuery(sqlQuery);
		int index = 1;
		insertSql.setParameter(index, id);
		for (Object value : records.values()) {
			index += 1;
			insertSql.setParameter(index, value);
		}

		try {
			insertSql.executeUpdate();
		} catch (Exception e) {
			String errorMessage = messageManager.getMessage("SQL_Unknow_Exception", null);
			if(e.getCause() instanceof SQLException) {
				errorMessage = e.getCause().getCause().getMessage();
			}
			log.error("", e);
			throw new Exception(errorMessage);
		}

		return id;
	}

}
