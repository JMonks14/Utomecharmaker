package com.tome.main.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Your supplied username and/or password are already in use.")
public class PlayerAlreadyExistsException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
