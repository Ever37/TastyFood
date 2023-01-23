package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "requerimiento_permiso_grupo", catalog = "proyecto_final")
public class RequerimientoPermisoGrupo implements java.io.Serializable {

    private Long idRequerimientoPermisoGrupo;
    private Grupo grupo;
    private RequerimientoPermiso requerimientoPermiso;

    public RequerimientoPermisoGrupo() {
    }

    public RequerimientoPermisoGrupo(Grupo grupo,
	    RequerimientoPermiso requerimientoPermiso) {
	this.grupo = grupo;
	this.requerimientoPermiso = requerimientoPermiso;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_requerimiento_permiso_grupo", unique = true, nullable = false)
    public Long getIdRequerimientoPermisoGrupo() {
	return this.idRequerimientoPermisoGrupo;
    }
    public void setIdRequerimientoPermisoGrupo(Long idRequerimientoPermisoGrupo) {
	this.idRequerimientoPermisoGrupo = idRequerimientoPermisoGrupo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    public Grupo getGrupo() {
	return this.grupo;
    }
    public void setGrupo(Grupo grupo) {
	this.grupo = grupo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_requerimiento_permiso", nullable = false)
    public RequerimientoPermiso getRequerimientoPermiso() {
	return this.requerimientoPermiso;
    }
    public void setRequerimientoPermiso(
	    RequerimientoPermiso requerimientoPermiso) {
	this.requerimientoPermiso = requerimientoPermiso;
    }

}
