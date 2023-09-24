package com.movieplus.domain.common.exception;

public class InternalAPIException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InternalAPIException(String message) {
		super(message);
	}

	public InternalAPIException(Throwable cause) {
		super(cause);
	}

}
