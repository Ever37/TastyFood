package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "permiso", catalog = "proyecto_final")
public class Permiso implements java.io.Serializable {


 public enum TipoPermiso {

	SA(0, "SA"), RU(1, "RU"), CU(2, "CU"), SU(3, "SU");

	private final Integer idPermiso;
	private final String descripcion;

	private TipoPermiso(Integer idPermiso, String descripcion) {
	    this.idPermiso = idPermiso;
	    this.descripcion = descripcion;
	}

	public Integer getIdPermiso() {
	    return this.idPermiso;
	}

	public String getDescripcion() {
	    return this.descripcion;
	}

 }
 
	/*
	 * Principales
	 */
 private Integer idPermiso;
 private String descripcion;
 private String descripcionCompleta;
 
 /*
  * Relaciones
  */
 private Set<RequerimientoPermiso> requerimientoPermisos = new HashSet<RequerimientoPermiso>(0);

 public Permiso() {
 }

 @Id
 @GeneratedValue(strategy = IDENTITY)
 @Column(name = "id_permiso", unique = true, nullable = false)
 public Integer getIdPermiso() {
	return this.idPermiso;
 }
 public void setIdPermiso(Integer idPermiso) {
	this.idPermiso = idPermiso;
 }

 @Column(name = "descripcion", nullable = false, length = 20)
 public String getDescripcion() {
	return this.descripcion;
 }
 public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
 }

 @Column(name = "descripcionCompleta", nullable = false, length = 45)
 public String getDescripcionCompleta() {
	return this.descripcionCompleta;
 }
 public void setDescripcionCompleta(String descripcionCompleta) {
	this.descripcionCompleta = descripcionCompleta;
 }

 @OneToMany(fetch = FetchType.LAZY, mappedBy = "permiso")
 public Set<RequerimientoPermiso> getRequerimientoPermisos() {
	return this.requerimientoPermisos;
 }
 public void setRequerimientoPermisos(
	    Set<RequerimientoPermiso> requerimientoPermisos) {
	this.requerimientoPermisos = requerimientoPermisos;
 }

}
