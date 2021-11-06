package com.tome.main.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A tree with that id does not exist")
public class TreeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9216101173982921934L;

}
