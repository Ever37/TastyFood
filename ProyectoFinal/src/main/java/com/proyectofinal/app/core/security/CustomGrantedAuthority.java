package com.proyectofinal.app.core.security;

import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = -1861768715791073221L;
    private String authority;

    public CustomGrantedAuthority(String rol) {
	this.authority = rol;
    }

    @Override
    public String getAuthority() {
	return this.authority;
    }
}
