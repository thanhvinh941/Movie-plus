package com.movieplus.domain.common;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageManager {

	private final MessageSource messageSource;
	
	/**
	 * getMessage
	 * 
	 * @param messageKey
	 * @param messageArgs
	 * @return
	 */
	public String getMessage(String messageKey, Object[] messageArgs) {
		return messageSource.getMessage(messageKey, messageArgs, new Locale("vi"));
	}

}
