package com.proyectofinal.app.dto;

import java.util.ArrayList;
import java.util.List;

public class GrupoDto {

    private Long idGrupo;
    private String descripcion;
    private List<RequerimientoDto> rComunes = new ArrayList<RequerimientoDto>();
    private List<RequerimientoDto> rEspeciales = new ArrayList<RequerimientoDto>();

    public GrupoDto() {

    }

    public GrupoDto(Long idGrupo, String descripcion) {
	this.idGrupo = idGrupo;
	this.descripcion = descripcion;
    }

    public Long getIdGrupo() {
	return this.idGrupo;
    }
    public void setIdGrupo(Long idGrupo) {
	this.idGrupo = idGrupo;
    }

    public String getDescripcion() {
	return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }
    
    public List<RequerimientoDto> getrComunes() {
		return rComunes;
	}
	public void setrComunes(List<RequerimientoDto> rComunes) {
		this.rComunes = rComunes;
	}

	public List<RequerimientoDto> getrEspeciales() {
		return rEspeciales;
	}
	public void setrEspeciales(List<RequerimientoDto> rEspeciales) {
		this.rEspeciales = rEspeciales;
	}

	@Override
    public String toString() {
	return this.descripcion;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
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
	GrupoDto other = (GrupoDto) obj;
	if (this.idGrupo == null) {
	    if (other.idGrupo != null) {
		return false;
	    }
	} else if (!this.idGrupo.equals(other.idGrupo)) {
	    return false;
	}
	return true;
    }

}
