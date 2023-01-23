package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "promo", catalog = "proyecto_final")
public class Promo implements java.io.Serializable {

	/*
	 * Principales
	 */
	private Long idPromo;
	private Producto producto;
	private Date fechaAlta;
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_promo", unique = true, nullable = false)
	public Long getIdPromo() {
		return idPromo;
	}
	public void setIdPromo(Long idPromo) {
		this.idPromo = idPromo;
	}
		
	@Column(name = "fecha_alta", nullable = false)
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	@OneToOne
    @JoinColumn(name = "id_producto", nullable = false)
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
