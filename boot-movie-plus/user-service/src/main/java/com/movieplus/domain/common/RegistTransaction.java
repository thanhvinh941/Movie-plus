package com.movieplus.domain.common;

import java.util.Map;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistTransaction {

	private final PlatformTransactionManager transactionManager;

	String doRegist(Map<String, Object> params, Class<?> targetClass) {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

		return transactionTemplate.execute(new TransactionCallback<String>() {
			@Override
			public String doInTransaction(TransactionStatus status) {
				return null;
			}
		});
	}
}
