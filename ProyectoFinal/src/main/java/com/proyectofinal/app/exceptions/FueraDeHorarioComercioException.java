package com.proyectofinal.app.exceptions;

public class FueraDeHorarioComercioException extends Exception {

    private static final long serialVersionUID = -3707669293871907346L;
    
    public FueraDeHorarioComercioException() {
    	super();
    }

    public FueraDeHorarioComercioException(String msg) {
    	super(msg);
    }

    public FueraDeHorarioComercioException(String msg, Throwable cause) {
    	super(msg, cause);
    }

    public FueraDeHorarioComercioException(Throwable cause) {
    	super(cause);
    }
    
}
