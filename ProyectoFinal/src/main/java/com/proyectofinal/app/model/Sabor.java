package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "sabor", catalog = "proyecto_final")
public class Sabor implements java.io.Serializable {

	/*
	 * Principales
	 */
	private Long idSabor;
	private String descripcion;
	private Boolean activo;
	private Date fechaBaja;
	
	/*
	 * Relaciones
	 */
	private Comercio comercio;
	private Set<SaboresDeItem> saboresItem = new HashSet<SaboresDeItem>(0);
	
	public Sabor() {
	}

    public Sabor(Comercio comercio) {
		this.comercio = comercio;
	}
    
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_sabor", unique = true, nullable = false)
	public Long getIdSabor() {
		return idSabor;
	}
	public void setIdSabor(Long idSabor) {
		this.idSabor = idSabor;
	}

    @Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
    @Column(name = "activo", nullable = false)	
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_baja")
    public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)	
    @JoinColumn(name = "id_comercio", nullable = false)
	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sabor", cascade = CascadeType.ALL)
	public Set<SaboresDeItem> getSaboresItem() {
		return saboresItem;
	}

	public void setSaboresItem(Set<SaboresDeItem> saboresItem) {
		this.saboresItem = saboresItem;
	}	
	
}
