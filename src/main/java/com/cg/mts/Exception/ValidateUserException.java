package com.cg.mts.Exception;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateUserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<FieldError> errors;
	
	public ValidateUserException() {
		super();
		this.errors = null;

	}

	public ValidateUserException(String arg0) {
		super(arg0);
		this.errors = null;
	}
	
	public ValidateUserException(List<FieldError> errors) {
		this.errors=errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
	

}
