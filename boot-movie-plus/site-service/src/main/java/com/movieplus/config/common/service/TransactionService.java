package com.movieplus.config.common.service;

import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.stereotype.Service;

import com.movieplus.config.common.exception.ClientException;

import jakarta.persistence.EntityManager;
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
		SessionImplementor sessionImp = (SessionImplementor) entityManager.getDelegate();
	    var transaction = sessionImp.getTransaction();

		Object res = null;

		try {
			transaction.begin();

			res = doProc(params);
		} catch (ClientException e) {
			log.error("{} - ClientException: ", getServiceId(), e);
			transaction.rollback();
		} catch (Exception e) {
			log.error("{} - Exception: ", getServiceId(), e);
			transaction.rollback();
		} finally {
			transaction.commit();
		}

		return res;
	}
	
}
