package com.tome.main.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A character with that ID does not exist.")
public class CharacterNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9025268036413626440L;
	
	

}
