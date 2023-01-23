package com.proyectofinal.app.exceptions;

public class CompraMinimaExcepcion extends Exception  {

	    private static final long serialVersionUID = -3707669293871907346L;
	    
	    public CompraMinimaExcepcion() {
	    	super();
	    }

	    public CompraMinimaExcepcion(String msg) {
	    	super(msg);
	    }

	    public CompraMinimaExcepcion(String msg, Throwable cause) {
	    	super(msg, cause);
	    }

	    public CompraMinimaExcepcion(Throwable cause) {
	    	super(cause);
	    }
}
