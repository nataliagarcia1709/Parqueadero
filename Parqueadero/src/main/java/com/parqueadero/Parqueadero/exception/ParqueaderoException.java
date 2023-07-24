package com.parqueadero.Parqueadero.exception;

import java.io.Serializable;

public class ParqueaderoException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public ParqueaderoException(String message) {
        super(message);
    }
}