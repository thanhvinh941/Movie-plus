package com.movieplus.domain.common;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CustomRepositoryImpl<T, ID> implements CustomRepository<T, ID> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<String> updateAll(Map<ID, Map<String, Object>> params, T tableName) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<? extends Object> query = cb.createQuery(tableName.getClass());
		Root<? extends Object> user = query.from(tableName.getClass());
		Path<String> emailPath = user.get("email");
		cb.equal(emailPath, "email");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(Map<String, Object> params, T tableName, ID key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void uDeleteAll(T tableName, List<ID> key) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<?> selectByCondition(T tableName, String conditionStr, List<String> listFields,
			Map<String, Object> orderBy, Integer limit, Integer offset, boolean isForUpdate) {
		Query queryEmployees = entityManager.createNativeQuery(
			    "Select * from EMP_TABLE where Salary < #salary", tableName.getClass()
			);
		queryEmployees.setParameter("salary", 50000);
		return queryEmployees.getResultList();
	}

}
