package com.parqueadero.Parqueadero.exception;

public class EmailExistsException extends ParqueaderoException {
	private static final long serialVersionUID = 1L;  
	public EmailExistsException(String message) {
	        super(message);
	    }
}
