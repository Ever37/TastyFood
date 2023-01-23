package com.proyectofinal.app.dto;

import java.util.Date;
import org.apache.commons.lang3.text.WordUtils;

public class UsuarioDto {

    private Long idUsuario;
    private GrupoDto grupo;
    private String nombreUsuario;
    private String contrasena;
    private String contrasenaAntigua;
    private String email;
    private String telefono;
    private String celular;
    private String nombre;
    private String apellido;
    private String fechaAlta;
    private Date fechaBaja;
    private String notificaciones;
    private Double valoracion;
	private String observaciones;

    public UsuarioDto() {

    }
    
    public UsuarioDto(Long idUsuario, String apellido, String nombre, String contrasena,
    		String email, String nombreUsuario, GrupoDto grupo, String telefono, String celular,
    		String notificaciones, String fechaAlta, Double valoracion) {

    	this.idUsuario = idUsuario;
    	this.apellido = apellido;
    	this.nombre = nombre;
    	this.contrasena = contrasena;
    	this.email = email;
    	this.nombreUsuario = nombreUsuario;
    	this.grupo = grupo;
    	this.telefono = telefono;
    	this.celular = celular;
    	this.notificaciones = notificaciones;
    	this.fechaAlta = fechaAlta;
    	this.valoracion = valoracion;
    }

    public UsuarioDto(Long idUsuario, GrupoDto grupo,
	    String nombreUsuario, String contrasena, String email,
	    String nombre, String apellido) {
	this.idUsuario = idUsuario;
	this.grupo = grupo;
	this.nombreUsuario = nombreUsuario;
	this.contrasena = contrasena;
	this.email = email;
	this.nombre = nombre;
	this.apellido = apellido;

    }

    public Long getIdUsuario() {
	return this.idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
	this.idUsuario = idUsuario;
    }

    public GrupoDto getGrupo() {
	return this.grupo;
    }
    public void setGrupo(GrupoDto grupo) {
	this.grupo = grupo;
    }

    public String getNombreUsuario() {
	return this.nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenaAntigua() {
	return this.contrasenaAntigua;
    }
    public void setContrasenaAntigua(String contrasenaAntigua) {
	this.contrasenaAntigua = contrasenaAntigua;
    }

    public String getContrasena() {
	return this.contrasena;
    }
    public void setContrasena(String contrasena) {
	this.contrasena = contrasena;
    }

    public String getEmail() {
	return this.email;
    }
    public void setEmail(String email) {
	this.email = email;
    }

    public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNombre() {
	return this.nombre;
    }
    public void setNombre(String nombre) {
	this.nombre = WordUtils.capitalize(nombre.toLowerCase());
    }

    public String getApellido() {
	return this.apellido;
    }
    public void setApellido(String apellido) {
	this.apellido = WordUtils.capitalize(apellido.toLowerCase());
    }

    public Date getFechaBaja() {
	return this.fechaBaja;
    }
    public void setFechaBaja(Date fechaBaja) {
	this.fechaBaja = fechaBaja;
    }
    
    public String getNotificaciones() {
		return notificaciones;
	}
	public void setNotificaciones(String notificaciones) {
		this.notificaciones = notificaciones;
	}
	
	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public Double getValoracion() {
		return valoracion;
	}

	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public static class BuilderUsuarioDto {

	private Long idUsuario;
	private GrupoDto grupo;
	private String nombreUsuario;
	private String contrasena;
	private String email;
	private String nombre;
	private String apellido;

	public BuilderUsuarioDto hasIdUsuario(Long idUsuario) {
	    this.idUsuario = idUsuario;
	    return this;
	}

	public BuilderUsuarioDto hasGrupo(GrupoDto grupo) {
	    this.grupo = grupo;
	    return this;
	}

	public BuilderUsuarioDto hasNombreUsuario(String nombreUsuario) {
	    this.nombreUsuario = nombreUsuario;
	    return this;
	}

	public BuilderUsuarioDto hasContrasena(String contrasena) {
	    this.contrasena = contrasena;
	    return this;
	}

	public BuilderUsuarioDto hasEmail(String email) {
	    this.email = email;
	    return this;
	}

	public BuilderUsuarioDto hasNombre(String nombre) {
	    this.nombre = nombre;
	    return this;
	}

	public BuilderUsuarioDto hasApellido(String apellido) {
	    this.apellido = apellido;
	    return this;
	}

	public UsuarioDto build() {
	    UsuarioDto dto = new UsuarioDto(this.idUsuario,
		    this.grupo, this.nombreUsuario, this.contrasena, this.email,
		    this.nombre, this.apellido);
	    return dto;
	}

    }

}
