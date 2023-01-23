package com.proyectofinal.app.dto;

public class RequerimientoDto {

    private Long idRequerimiento;
    private String descripcion;
    private String alias;
    private Boolean workflow;
    private Integer permiso;
    private Long idRequerimientoPermisoGrupo;

    public RequerimientoDto() {

    }

    public RequerimientoDto(Long idRequerimiento,
	    String descripcion, String alias, Boolean workflow,
	    Integer permiso, Long idRequerimientoPermisoGrupo) {
	this.idRequerimiento = idRequerimiento;
	this.descripcion = descripcion;
	this.alias = alias;
	this.workflow = workflow;
	this.permiso = permiso;
	this.idRequerimientoPermisoGrupo = idRequerimientoPermisoGrupo;
    }

    public Long getIdRequerimiento() {
	return this.idRequerimiento;
    }

    public void setIdRequerimiento(Long idRequerimiento) {
	this.idRequerimiento = idRequerimiento;
    }

    public String getDescripcion() {
	return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getAlias() {
	return this.alias;
    }

    public void setAlias(String alias) {
	this.alias = alias;
    }

    public Boolean getWorkflow() {
	return this.workflow;
    }

    public void setWorkflow(Boolean workflow) {
	this.workflow = workflow;
    }

    public Integer getPermiso() {
	return this.permiso;
    }

    public void setPermiso(Integer permiso) {
	this.permiso = permiso;
    }

    public Long getIdRequerimientoPermisoGrupo() {
	return this.idRequerimientoPermisoGrupo;
    }

    public void setIdRequerimientoPermisoGrupo(Long idRequerimientoPermisoGrupo) {
	this.idRequerimientoPermisoGrupo = idRequerimientoPermisoGrupo;
    }

    @Override
    public String toString() {
	return this.descripcion;
    }

    public static class BuilderRequerimientoDto {

	private Long idRequerimiento;
	private String descripcion;
	private String alias;
	private Boolean workflow;
	private Integer permiso;
	private Long idRequerimientoPermisoGrupo;

	public BuilderRequerimientoDto hasIdRequerimiento(Long idRequerimiento) {
	    this.idRequerimiento = idRequerimiento;
	    return this;
	}

	public BuilderRequerimientoDto hasDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	    return this;
	}

	public BuilderRequerimientoDto hasAlias(String alias) {
	    this.alias = alias;
	    return this;
	}

	public BuilderRequerimientoDto hasWorkflow(Boolean workflow) {
	    this.workflow = workflow;
	    return this;
	}

	public BuilderRequerimientoDto hasPermiso(Integer permiso) {
	    this.permiso = permiso;
	    return this;
	}

	public BuilderRequerimientoDto hasIdRequerimientoPermisoGrupo(
		Long idRequerimientoPermisoGrupo) {
	    this.idRequerimientoPermisoGrupo = idRequerimientoPermisoGrupo;
	    return this;
	}

	public RequerimientoDto build() {
	    RequerimientoDto dto = new RequerimientoDto(this.idRequerimiento,
		    this.descripcion, this.alias, this.workflow,
		    this.permiso, this.idRequerimientoPermisoGrupo);
	    return dto;
	}

    }

}
