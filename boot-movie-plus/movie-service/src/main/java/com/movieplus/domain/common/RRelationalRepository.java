package com.movieplus.domain.common;

public class RRelationalRepository {

//    public Flux<Map<String, Object>> findByQueryFlux(final String entityName, final List<String> listField, final String conditionStr, final List<String> orders, final Integer limit, final Integer offset, boolean forupdateFlg){
//    	String sqlQuery = new SQL() {{
//			SELECT(getSelectSQL(listField));
//			FROM(getTableName(entityName));
//			if (StringUtils.isNotEmpty(conditionStr)) {
//				WHERE(conditionStr);
//			}
//			if (orders != null) {
//				for (String order : orders) {
//					ORDER_BY(order);
//				}
//			}
//		}}.toString();
//		
//		if (limit != null && limit > 0) {
//			sqlQuery += String.format(" LIMIT %s", limit);
//			if (offset != null && offset > 0) {
//				sqlQuery += String.format(" OFFSET %s", offset);
//			}
//		}
//		
//		
//		// FOR UPDATE
//		if (forupdateFlg) {
//			sqlQuery += " FOR UPDATE";
//		}
//		
//		return client.sql(sqlQuery)
//				.fetch()
//				.all();
//    }
//    
//    public Flux<Map<String, Object>> findByQueryFlux(Class<?> entityClass, final String conditionStr, final List<String> orders, final Integer limit, final Integer offset, boolean forupdateFlg) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//    	String entityName = entityClass.getSimpleName();
//    	List<String> listField = getFieldList(entityClass);
//    	return findByQueryFlux(entityName, listField, conditionStr, orders, limit, offset, forupdateFlg);
//    }
//    
//    public Mono<Map<String, Object>> findByQueryMono(final String entityName, final List<String> listField, final String conditionStr, final List<String> orders, final Integer limit, final Integer offset, boolean forupdateFlg){
//    	String sqlQuery = new SQL() {{
//			SELECT(getSelectSQL(listField));
//			FROM(getTableName(entityName));
//			if (StringUtils.isNotEmpty(conditionStr)) {
//				WHERE(conditionStr);
//			}
//			if (orders != null) {
//				for (String order : orders) {
//					ORDER_BY(order);
//				}
//			}
//		}}.toString();
//		
//		if (limit != null && limit > 0) {
//			sqlQuery += String.format(" LIMIT %s", limit);
//			if (offset != null && offset > 0) {
//				sqlQuery += String.format(" OFFSET %s", offset);
//			}
//		}
//		
//		// FOR UPDATE
//		if (forupdateFlg) {
//			sqlQuery += " FOR UPDATE";
//		}
//		
//		return client.sql(sqlQuery)
//				.fetch()
//				.first();
//    }
//    
//    private List<String> getFieldList(Class<?> entityClass) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//    	Constructor<?> constructor = entityClass.getDeclaredConstructor();
//		Method getFieldList = constructor.newInstance().getClass().getMethod("getFieldList");
//		List<String> fieldList = (List<String>) getFieldList.invoke(constructor.newInstance());
//		return fieldList;
//	}
//    
//    private String getSelectSQL(List<String> fieldList) {
//		String sql = "";
//		for(String field : fieldList) {
//			sql += " " + Util.convertSnake(field) + " ,";
//		}
//		sql = StringUtils.removeEnd(sql, "," );
//		return sql;
//	}
//	
//	private String getTableName(String entityName) {
//		return Util.convertSnake(entityName);
//	}
	
}
