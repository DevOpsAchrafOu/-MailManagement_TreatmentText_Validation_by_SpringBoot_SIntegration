package com.api.mail.manag.exception;

import org.springframework.http.HttpStatus;

//400 || 404 || 409 || 417 
//class anbsract Exception pour organisier mes exception personnale 
public abstract class GlobalException extends RuntimeException {

	private static final long serialVersionUID = 1L;// est obligier par RuntimeException(super)

	public GlobalException(String msg) {
		super(msg);
	}

	public abstract HttpStatus getCodeStatus();

}
