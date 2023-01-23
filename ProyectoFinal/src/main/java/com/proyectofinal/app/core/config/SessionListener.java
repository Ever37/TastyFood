package com.proyectofinal.app.core.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
	System.out.println("==== Session is created ====");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
	System.out.println("==== Session is destroyed ====");
    }
}