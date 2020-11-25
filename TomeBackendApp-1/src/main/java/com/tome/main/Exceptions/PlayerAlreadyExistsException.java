package com.tome.main.Exceptions;


public class PlayerAlreadyExistsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7704881319799176131L;

	public PlayerAlreadyExistsException() {
        super();
    }

    public PlayerAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PlayerAlreadyExistsException(final String message) {
        super(message);
    }

    public PlayerAlreadyExistsException(final Throwable cause) {
        super(cause);
    }

}
