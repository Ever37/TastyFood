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
@Table(name = "requerimiento", catalog = "proyecto_final")
public class Requerimiento implements java.io.Serializable {

    private Long idRequerimiento;
    private String descripcion;
    private String alias;
    private Boolean workflow;
    private Set<RequerimientoPermiso> requerimientoPermisos = new HashSet<RequerimientoPermiso>(
	    0);

    public Requerimiento() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_requerimiento", unique = true, nullable = false)
    public Long getIdRequerimiento() {
	return this.idRequerimiento;
    }
    public void setIdRequerimiento(Long idRequerimiento) {
	this.idRequerimiento = idRequerimiento;
    }

    @Column(name = "descripcion", nullable = false, length = 50)
    public String getDescripcion() {
	return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    @Column(name = "alias", nullable = false, length = 10)
    public String getAlias() {
	return this.alias;
    }
    public void setAlias(String alias) {
	this.alias = alias;
    }

    @Column(name = "workflow")
	public Boolean getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Boolean workflow) {
		this.workflow = workflow;
	}
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "requerimiento")
    public Set<RequerimientoPermiso> getRequerimientoPermisos() {
	return this.requerimientoPermisos;
    }

	public void setRequerimientoPermisos(
	    Set<RequerimientoPermiso> requerimientoPermisos) {
	this.requerimientoPermisos = requerimientoPermisos;
    }

}
