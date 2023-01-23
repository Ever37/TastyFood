package com.proyectofinal.app.exceptions;

public class AccessDeniedException extends Exception {

    private static final long serialVersionUID = -3707669293871907346L;
    
    public AccessDeniedException() {
	super();
    }

    public AccessDeniedException(String msg) {
	super(msg);
    }

    public AccessDeniedException(String msg, Throwable cause) {
	super(msg, cause);
    }

    public AccessDeniedException(Throwable cause) {
	super(cause);
    }
    
}
