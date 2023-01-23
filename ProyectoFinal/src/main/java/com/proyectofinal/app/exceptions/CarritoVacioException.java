package com.proyectofinal.app.exceptions;

public class CarritoVacioException extends Exception  {

    private static final long serialVersionUID = -3707669293871907346L;
    
    public CarritoVacioException() {
    	super();
    }

    public CarritoVacioException(String msg) {
    	super(msg);
    }

    public CarritoVacioException(String msg, Throwable cause) {
    	super(msg, cause);
    }

    public CarritoVacioException(Throwable cause) {
    	super(cause);
    }
}
