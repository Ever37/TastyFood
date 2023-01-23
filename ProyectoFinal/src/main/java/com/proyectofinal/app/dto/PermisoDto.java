package com.proyectofinal.app.dto;

public class PermisoDto {

    private Integer idPermiso;
    private String descripcion;
    private String descripcionCompleta;

    public PermisoDto() {

    }

    public PermisoDto(Integer idPermiso, String descripcion,
	    String descripcionCompleta) {
	this.idPermiso = idPermiso;
	this.descripcion = descripcion;
	this.descripcionCompleta = descripcionCompleta;
    }

    public Integer getIdPermiso() {
	return this.idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
	this.idPermiso = idPermiso;
    }

    public String getDescripcion() {
	return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public void setDescripcionCompleta(String descripcionCompleta) {
	this.descripcionCompleta = descripcionCompleta;
    }

    public String getDescripcionCompleta() {
	return this.descripcionCompleta;
    }

}
