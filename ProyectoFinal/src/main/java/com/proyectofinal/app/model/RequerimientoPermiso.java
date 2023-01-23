package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "requerimiento_permiso", catalog = "proyecto_final")
public class RequerimientoPermiso implements java.io.Serializable {

    private Long idRequerimientoPermiso;
    private Permiso permiso;
    private Requerimiento requerimiento;
    private String rol;
    private Set<RequerimientoPermisoGrupo> requerimientoPermisoGrupos = new HashSet<RequerimientoPermisoGrupo>(
	    0);

    public RequerimientoPermiso() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_requerimiento_permiso", unique = true, nullable = false)
    public Long getIdRequerimientoPermiso() {
	return this.idRequerimientoPermiso;
    }
    public void setIdRequerimientoPermiso(Long idRequerimientoPermiso) {
	this.idRequerimientoPermiso = idRequerimientoPermiso;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permiso", nullable = false)
    public Permiso getPermiso() {
	return this.permiso;
    }
    public void setPermiso(Permiso permiso) {
	this.permiso = permiso;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_requerimiento", nullable = false)
    public Requerimiento getRequerimiento() {
	return this.requerimiento;
    }
    public void setRequerimiento(Requerimiento requerimiento) {
	this.requerimiento = requerimiento;
    }

    @Column(name = "rol", nullable = false, length = 20)
    public String getRol() {
	return this.rol;
    }
    public void setRol(String rol) {
	this.rol = rol;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "requerimientoPermiso")
    public Set<RequerimientoPermisoGrupo> getRequerimientoPermisoGrupos() {
	return this.requerimientoPermisoGrupos;
    }
    public void setRequerimientoPermisoGrupos(
	    Set<RequerimientoPermisoGrupo> requerimientoPermisoGrupos) {
	this.requerimientoPermisoGrupos = requerimientoPermisoGrupos;
    }

}
