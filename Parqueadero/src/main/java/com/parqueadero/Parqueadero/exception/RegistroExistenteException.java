package com.parqueadero.Parqueadero.exception;

public class RegistroExistenteException extends ParqueaderoException {
	private static final long serialVersionUID = 1L;
	public RegistroExistenteException(String message) {
        super(message);
    }
}