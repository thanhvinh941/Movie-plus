package com.movieplus.config.common.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.util.XYZUtil;
import com.movieplus.domain.common.MessageManager;

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
	private static String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	@PersistenceContext
	private EntityManager entityManager;
	private final MessageManager messageManager;

	private String getUpdateTime() {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return "update_time = '" + LocalDateTime.now().format(formatters) + "'";
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

	@SuppressWarnings("serial")
	public List<?> selectByCondition(String tableName, String conditionStr, final List<String> listFields,
			Map<String, String> orderBys, Integer limit, Integer offset, boolean isForUpdate) throws Exception {
		String selectColumn = listFields.stream().map(s -> XYZUtil.convertSnake(s)).collect(Collectors.joining(", "));
		String sqlQuery = String.format("SELECT %s FROM %s", selectColumn, XYZUtil.convertSnake(tableName));

		if (!StringUtils.isBlank(conditionStr)) {
			sqlQuery += String.format(" WHERE %s", conditionStr);
		}

		if (!CollectionUtils.isEmpty(orderBys)) {
			String orderbyStr = "";
			for (Map.Entry<String, String> entry : orderBys.entrySet()) {
				orderbyStr += XYZUtil.convertSnake(entry.getKey()) + " " + entry.getValue() + ", ";
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
			return resultList.stream().map(r -> {
				if (listFields.size() > 1) {
					Map<String, Object> map = new HashMap<>();
					Object[] result = (Object[]) r;
					for (int i = 0; i < listFields.size(); i++) {
						map.put(listFields.get(i), result[i]);
					}
					return map;
				}

				return new HashMap<String, Object>() {
					{
						put(listFields.get(0), r);
					}
				};
			}).toList();
		} catch (Exception e) {
			log.error("{} ERROR execute: {}", logTitle, e);
			if (e instanceof SQLGrammarException) {
				Object[] datas = { tableName, listFields };
				throw new ClientException(messageManager.getMessage("SQL_Grammar_Exception", datas));
			}
			throw new Exception(messageManager.getMessage("SQL_Unknow_Exception", null));
		}
	}
	
	@SuppressWarnings("serial")
	public <T> List<T> selectByCondition(Class<T> targetTable, String conditionStr,
			Map<String, String> orderBys, Integer limit, Integer offset, boolean isForUpdate) throws Exception {
		String tableName = targetTable.getSimpleName();
		String sqlQuery = String.format("SELECT * FROM %s", XYZUtil.convertSnake(tableName));

		if (!StringUtils.isBlank(conditionStr)) {
			sqlQuery += String.format(" WHERE %s", conditionStr);
		}

		if (!CollectionUtils.isEmpty(orderBys)) {
			String orderbyStr = "";
			for (Map.Entry<String, String> entry : orderBys.entrySet()) {
				orderbyStr += XYZUtil.convertSnake(entry.getKey()) + " " + entry.getValue() + ", ";
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

		Query selectSql = entityManager.createNativeQuery(sqlQuery, targetTable);

		try {
			return selectSql.getResultList();
		} catch (Exception e) {
			log.error("{} ERROR execute: {}", logTitle, e);
			if (e instanceof SQLGrammarException) {
				Object[] datas = { tableName };
				throw new ClientException(messageManager.getMessage("SQL_Grammar_Exception", datas));
			}
			throw new Exception(messageManager.getMessage("SQL_Unknow_Exception", null));
		}
	}

	@Override
	public List<?> selectByCondition(String tableName, String conditionStr, List<String> listFields) throws Exception {
		return selectByCondition(tableName, conditionStr, listFields, null, null, null, false);
	}

	@Override
	public Long count(String tableName, String conditionStr) {
		String sqlQuery = String.format("SELECT COUNT(id) FROM %s", XYZUtil.convertSnake(tableName));
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
	public String insertRecords(String tableName, Map<String, Object> records) throws Exception {
		Date nowStamp = new Date();
		String columes = "id, ";
		String values = "?,";
		String uuidStr = UUID.randomUUID().toString();
		String id = nowStamp.getTime() + uuidStr.substring(12);
		for (Map.Entry<String, Object> entry : records.entrySet()) {
			if(Objects.isNull(entry.getValue())) {
				continue;
			}
			columes = StringUtils.join(columes, XYZUtil.convertSnake(entry.getKey()) + ",");
			values = StringUtils.join(values, "?,");
		}

		columes = StringUtils.removeEnd(columes, ",");
		values = StringUtils.removeEnd(values, ",");

		String sqlQuery = String.format("INSERT INTO %s (%s) VALUES (%s)", XYZUtil.convertSnake(tableName), columes,
				values);
		Query insertSql = entityManager.createNativeQuery(sqlQuery);
		int index = 1;
		insertSql.setParameter(index, id);
		for (Object value : records.values()) {
			if(Objects.isNull(value)) {
				continue;
			}
			index += 1;
			insertSql.setParameter(index, value);
		}

		try {
			insertSql.executeUpdate();
		} catch (Exception e) {
			String errorMessage = messageManager.getMessage("SQL_Unknow_Exception", null);
			if (e.getCause() instanceof SQLException) {
				throw new ClientException(e);
			}
			log.error("{} ERROR execute: {}", logTitle, e);
			throw new Exception(errorMessage);
		}
		
		return id;
	}

	@Override
	@Modifying
	public String updateRecords(String tableName, Map<String, Object> records, String id) throws Exception {
		String setValue = "";
		if(Objects.isNull(id)) {
			throw new ClientException(messageManager.getMessage("SQL_Update_Param_Invalid", logTitle));
		}
		String condition = String.format("id = '%s'", id);
		for (Map.Entry<String, Object> entry : records.entrySet()) {
			setValue += String.format("%s = %s,", XYZUtil.convertSnake(entry.getKey()), getStringValueOfObject(entry.getValue()));
		}
		setValue += getUpdateTime();
		
		String sqlQuery = String.format("UPDATE %s SET %s WHERE %s", XYZUtil.convertSnake(tableName), setValue, condition);
		Query updateQuery = entityManager.createNativeQuery(sqlQuery);
		try {
			updateQuery.executeUpdate();
		} catch (Exception e) {
			String errorMessage = messageManager.getMessage("SQL_Unknow_Exception", null);
			if (e.getCause() instanceof SQLException) {
				throw new ClientException(e);
			}
			log.error("{} ERROR execute: {}", logTitle, e);
			throw new Exception(errorMessage);
		}

		return id;
	}

	@Override
	public boolean deleteById(String tableName, String id) throws Exception {
		String sqlQuery = String.format("UPDATE %s SET del_flg = 1, %s WHERE id = '%s'", tableName, getUpdateTime(), id);
		Query updateQuery = entityManager.createNativeQuery(sqlQuery);
		try {
			updateQuery.executeUpdate();
		} catch (Exception e) {
			String errorMessage = messageManager.getMessage("SQL_Unknow_Exception", null);
			if (e.getCause() instanceof SQLException) {
				throw new ClientException(e);
			}
			
			log.error("{} ERROR execute: {}", logTitle, e);
			throw new Exception(errorMessage);
		}
		
		return true;
	}

	@Override
	@Modifying
	public String insertRecords(Object entity) throws Exception {
		String tableName = entity.getClass().getSimpleName();
		Map<String, Object> records = initObjectMapper().convertValue(entity, Map.class);
		return insertRecords(tableName, records);
	}

	@Override
	public <T> List<T> selectByCondition(Class<T> targetTable, String conditionStr) throws Exception {
		return selectByCondition(targetTable, conditionStr, null, null, null, false);
	}

	@Override
	public <T> List<T> selectByCondition(Class<T> targetTable) throws Exception {
		return selectByCondition(targetTable, null);
	}
	
	private static ObjectMapper initObjectMapper() {
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeCustomSerializer());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(javaTimeModule);
		return objectMapper;
	}
	
	public static class LocalDateTimeCustomSerializer extends JsonSerializer<LocalDateTime> {

		@Override
		public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			try {
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
				gen.writeString(value.format(dateFormatter));
			} catch (DateTimeParseException e) {
				System.err.println(e);
				gen.writeString("");
			}
		}
		
	}

}
