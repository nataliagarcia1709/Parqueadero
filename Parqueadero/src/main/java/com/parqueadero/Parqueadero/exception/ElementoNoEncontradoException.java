package com.parqueadero.Parqueadero.exception;

public class ElementoNoEncontradoException extends ParqueaderoException {
	private static final long serialVersionUID = 1L;
	public ElementoNoEncontradoException(String message) {
        super(message);
    }
}