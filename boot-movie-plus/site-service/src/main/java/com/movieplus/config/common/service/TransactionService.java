package com.movieplus.config.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.util.GeneratorUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public abstract class TransactionService {

	@PersistenceContext
	private EntityManager entityManager;

	public abstract String getApiId();

	public abstract String getMicroServiceId();
	
	public abstract Object doProc(Object params) throws Exception;

	private String getServiceId() {
		return getMicroServiceId() + "#" + getApiId();
	}
	
	public Object execute(Object params) throws Exception {
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Object res = null;

		try {
			entityTransaction.begin();

			res = doProc(params);
		} catch (ClientException e) {
			log.error("{} - ClientException: ", getServiceId(), e);
			entityTransaction.rollback();
		} catch (Exception e) {
			log.error("{} - Exception: ", getServiceId(), e);
			entityTransaction.rollback();
		} finally {
			entityTransaction.commit();
		}

		return res;
	}
}
