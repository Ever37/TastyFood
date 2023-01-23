package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "item_de_pedido", catalog = "proyecto_final")
public class ItemPedido implements java.io.Serializable {
	
	/*
	 * Principales
	 */
	private Long idItem;
	private Integer nroItem;
	private Double cantidad;
	private String aclaraciones;
	private Double subtotal;
	/*
	 * Relaciones
	 */
	private Pedido pedido;
	private Producto producto;
	private Set<SaboresDeItem> saboresItem = new HashSet<SaboresDeItem>(0);
	private Salsa salsaItem;
	/*
	 * Auxiliares
	 */
	private List<String> sabores = new ArrayList<String>();
	private String salsa;
	private Double precio;
		
	public ItemPedido() {
	}
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_item_de_pedido", unique = true, nullable = false)
	public Long getIdItem() {
		return idItem;
	}
	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	@Column(name = "nro_item")
    public Integer getNroItem() {
		return nroItem;
	}
	public void setNroItem(Integer nroItem) {
		this.nroItem = nroItem;
	}
    
	@Column(name = "cantidad")	
    public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "aclaraciones", length = 100)
    public String getAclaraciones() {
		return aclaraciones;
	}
	public void setAclaraciones(String aclaraciones) {
		this.aclaraciones = aclaraciones;
	}
     
	@Column(name = "subtotal")
	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pedido")
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	} 

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto")
    public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_salsa")
	public Salsa getSalsaItem() {
		return salsaItem;
	}
	public void setSalsaItem(Salsa salsaItem) {
		this.salsaItem = salsaItem;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itemPedido", cascade = CascadeType.ALL)
	public Set<SaboresDeItem> getSaboresItem() {
		return saboresItem;
	}
	public void setSaboresItem(Set<SaboresDeItem> saboresItem) {
		this.saboresItem = saboresItem;
	}
	
	@Transient
	public List<String> getSabores() {
		return sabores;
	}
	public void setSabores(List<String> sabores) {
		this.sabores = sabores;
	}

	@Transient
	public String getSalsa() {
		return salsa;
	}
	public void setSalsa(String salsa) {
		this.salsa = salsa;
	}

	@Transient
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}	
	
}
