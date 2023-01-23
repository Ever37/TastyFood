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
@Table(name = "salsa", catalog = "proyecto_final")
public class Salsa implements java.io.Serializable {

	/*
	 * Principales
	 */
	private Long idSalsa;
	private String descripcion;
	private Double precio;
	private Date fechaBaja;
	
	/*
	 * Relaciones
	 */
	private Producto producto;
	private Set<ItemPedido> itemsPedido = new HashSet<ItemPedido>(0);
	
	public Salsa() {
		
	}
	
	public Salsa(Long idSalsa, String descripcion, Double precio, Date fechaBaja) {
		this.idSalsa = idSalsa;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fechaBaja = fechaBaja;
	}
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_salsa", unique = true, nullable = false)
	public Long getIdSalsa() {
		return idSalsa;
	}
	public void setIdSalsa(Long idSalsa) {
		this.idSalsa = idSalsa;
	}
    
    @Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
    @Column(name = "precio", nullable = false)
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
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
    @JoinColumn(name = "id_producto", nullable = false)
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salsaItem", cascade = CascadeType.ALL)
	public Set<ItemPedido> getItemsPedido() {
		return itemsPedido;
	}
	public void setItemsPedido(Set<ItemPedido> itemsPedido) {
		this.itemsPedido = itemsPedido;
	}
	
	
}
