package com.proyectofinal.app.core.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1483387699425095226L;
    private Long idUsuario;
    private List<GrantedAuthority> roles;
    private String nombreUsuario;
    private String apellidoNombre;
    private String contrasena;
    private Boolean activo;

    public Long getIdUsuario() {
	return this.idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
	this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
	return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
	return this.contrasena;
    }

    public void setContrasena(String contrasena) {
	this.contrasena = contrasena;
    }

    public Boolean getActivo() {
	return this.activo;
    }

    public void setActivo(Boolean activo) {
	this.activo = activo;
    }

    public List<GrantedAuthority> getRoles() {
	return this.roles;
    }

    public void setRoles(List<GrantedAuthority> aux) {
	this.roles = aux;
    }

    public CustomUserDetails() {
	super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

	return this.roles;

    }

    @Override
    public String getPassword() {
	return this.getContrasena();
    }

    @Override
    public String getUsername() {
	return this.getNombreUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return this.getActivo();
    }

    public String getApellidoNombre() {
	return this.apellidoNombre;
    }

    public void setApellidoNombre(String apellidoNombre) {
	this.apellidoNombre = apellidoNombre;
    }

	@Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((this.idUsuario == null) ? 0 : this.idUsuario.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (this.getClass() != obj.getClass()) {
	    return false;
	}
	CustomUserDetails other = (CustomUserDetails) obj;
	if (this.idUsuario == null) {
	    if (other.idUsuario != null) {
		return false;
	    }
	} else if (!this.idUsuario.equals(other.idUsuario)) {
	    return false;
	}
	return true;
    }

}
