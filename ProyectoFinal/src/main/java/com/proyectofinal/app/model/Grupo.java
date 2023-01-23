package com.proyectofinal.app.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@SuppressWarnings("serial")
@Entity
@Table(name = "grupo", catalog = "proyecto_final", uniqueConstraints = @UniqueConstraint(columnNames = "descripcion"))
public class Grupo implements java.io.Serializable {

    private Long idGrupo;
    private String descripcion;
    private Set<Usuario> usuarios = new HashSet<Usuario>(0);
    private Set<RequerimientoPermisoGrupo> requerimientoPermisoGrupos = new HashSet<RequerimientoPermisoGrupo>(
	    0);

    public Grupo() {
    }

    public Grupo(Long idGrupo, String descripcion,
	    Set<RequerimientoPermisoGrupo> requerimientoPermisoGrupos) {
	this.idGrupo = idGrupo;
	this.descripcion = descripcion;
	this.requerimientoPermisoGrupos = requerimientoPermisoGrupos;
    }

    @Id
    @Column(name = "id_grupo", unique = true, nullable = false)
    public Long getIdGrupo() {
	return this.idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
	this.idGrupo = idGrupo;
    }

    @Column(name = "descripcion", unique = true, nullable = false, length = 50)
    public String getDescripcion() {
	return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
    public Set<Usuario> getUsuarios() {
	return this.usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
	this.usuarios = usuarios;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    public Set<RequerimientoPermisoGrupo> getRequerimientoPermisoGrupos() {
    	return this.requerimientoPermisoGrupos;
    }

    public void setRequerimientoPermisoGrupos(
	    Set<RequerimientoPermisoGrupo> requerimientoPermisoGrupos) {
	this.requerimientoPermisoGrupos = requerimientoPermisoGrupos;
    }

    public boolean addGrupoToReqPerm(
	    RequerimientoPermisoGrupo requerimientoPermisoGrupo) {
	requerimientoPermisoGrupo.setGrupo(this);
	return this.getRequerimientoPermisoGrupos()
		.add(requerimientoPermisoGrupo);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((this.descripcion == null) ? 0 : this.descripcion.hashCode());
	result = prime * result + ((this.idGrupo == null) ? 0 : this.idGrupo.hashCode());
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
	Grupo other = (Grupo) obj;
	if (this.descripcion == null) {
	    if (other.descripcion != null) {
		return false;
	    }
	} else if (!this.descripcion.equals(other.descripcion)) {
	    return false;
	}
	if (this.idGrupo == null) {
	    if (other.idGrupo != null) {
		return false;
	    }
	} else if (!this.idGrupo.equals(other.idGrupo)) {
	    return false;
	}
	return true;
    }

    public static class BuilderGrupo {

	private Long idGrupo;
	private String descripcion;
	private Set<RequerimientoPermisoGrupo> requerimientoPermisoGrupos = new HashSet<RequerimientoPermisoGrupo>(
		0);

	public BuilderGrupo hasIdGrupo(Long idGrupo) {
	    this.idGrupo = idGrupo;
	    return this;
	}

	public BuilderGrupo hasDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	    return this;
	}

	public BuilderGrupo hasRequerimientoPermisoGrupos(
		Set<RequerimientoPermisoGrupo> requerimientoPermisoGrupos) {
	    this.requerimientoPermisoGrupos = requerimientoPermisoGrupos;
	    return this;
	}

	public Grupo build() {
	    Grupo grupo = new Grupo(this.idGrupo, this.descripcion,
		    this.requerimientoPermisoGrupos);
	    for (RequerimientoPermisoGrupo requerimientoPermisoGrupo : this.requerimientoPermisoGrupos) {
		grupo.addGrupoToReqPerm(requerimientoPermisoGrupo);
	    }
	    return grupo;
	}
    }

}
