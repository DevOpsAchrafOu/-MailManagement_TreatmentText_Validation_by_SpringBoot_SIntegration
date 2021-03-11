package com.api.mail.manag.exception;

import org.springframework.http.HttpStatus;

//execption personnale; on peut utiliser lors element n'est pas trouvé
public class NotFoundElementException extends GlobalException{

	private static final long serialVersionUID = 1L;//est obligier par RuntimeException(super)

	public NotFoundElementException(String msg) {
		super(msg);
	}

	@Override
	public HttpStatus getCodeStatus() {
		return HttpStatus.NOT_FOUND;//404
	}

}
