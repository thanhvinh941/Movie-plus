package com.se.thanhvinh.simple.controller.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.se.thanhvinh.simple.domain.common.GeneratorCommonUtil;

@ControllerAdvice
public class GlobalExceptionHandler{
	
	@ExceptionHandler(BindException.class)
	public @ResponseBody String process(BindException bindException) {
	    List<ObjectError> errors = bindException.getAllErrors();
	    List<String> errorStrings = errors.stream().map(error->error.getDefaultMessage()).collect(Collectors.toList());
		return GeneratorCommonUtil.getResponseBodyError(errorStrings);
	}
}
