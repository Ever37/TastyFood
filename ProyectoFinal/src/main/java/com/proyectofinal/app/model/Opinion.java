package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "opinion", catalog = "proyecto_final")
public class Opinion implements java.io.Serializable {

	/*
	 * Principales
	 */
	private Long idOpinion;
	private String descripcion;
	private Integer valoracion;
	private Date fechaBaja;
	/*
	 * Relaciones
	 */
	private Comercio comercio;
		
	public Opinion() {
	}
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_opinion", unique = true, nullable = false)
	public Long getIdOpinion() {
		return idOpinion;
	}
    //Generado por DB.
    protected void setIdOpinion(Long idOpinion) {
		this.idOpinion = idOpinion;
	}
	
    @Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
    @Column(name = "valoracion", nullable = false)
	public Integer getValoracion() {
		return valoracion;
	}
	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_baja")
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "comercio", nullable = false)
	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	
}
