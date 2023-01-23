package com.proyectofinal.app.exceptions;

public class TodosItemsCarritoMismoComercio  extends Exception {

    private static final long serialVersionUID = -3707669293871907346L;
    
    public TodosItemsCarritoMismoComercio() {
    	super();
    }

    public TodosItemsCarritoMismoComercio(String msg) {
    	super(msg);
    }

    public TodosItemsCarritoMismoComercio(String msg, Throwable cause) {
    	super(msg, cause);
    }

    public TodosItemsCarritoMismoComercio(Throwable cause) {
    	super(cause);
    }
}
