package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
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
@Table(name = "precio_producto", catalog = "proyecto_final")
public class Precio implements java.io.Serializable {
	
	/*
	 * Principales
	 */
	private Long idPrecio;
	private Double valor;
	private Date fechaDesde;
	private Date fechaHasta;
	private Integer tipoPrecio;
	/*
	 * Relaciones
	 */	
	private Producto producto;
	
	public Precio() {
	}
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_precio_producto", unique = true, nullable = false)
	public Long getIdPrecio() {
		return idPrecio;
	}
    //Generado por DB.
    protected void setIdPrecio(Long idPrecio) {
		this.idPrecio = idPrecio;
	}
	
    @Column(name = "valor", nullable = false)
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_desde", nullable = false)
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hasta")
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

    @Column(name = "tipo_precio", nullable = false)
	public Integer getTipoPrecio() {
		return tipoPrecio;
	}
	public void setTipoPrecio(Integer tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	//Acordarse siempre del cascade cuando el alta de un objeto lleva a cabo el alta de otro.
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto", nullable = false)
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
