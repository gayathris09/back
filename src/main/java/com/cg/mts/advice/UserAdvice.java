package com.cg.mts.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.mts.Exception.LoginException;
import com.cg.mts.dto.ErrorMessageDto;
import com.cg.mts.util.LoginConstants;


@RestControllerAdvice
public class UserAdvice{

	@ExceptionHandler(LoginException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ErrorMessageDto handleLoginException(LoginException ex) {
		return new ErrorMessageDto(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessageDto handleHeaderException(MissingRequestHeaderException ex) {
		return new ErrorMessageDto(HttpStatus.BAD_REQUEST.toString(), LoginConstants.MISSING_REQUEST_HEADER);
	}

	
}
