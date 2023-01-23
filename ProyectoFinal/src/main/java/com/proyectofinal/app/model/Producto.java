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
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "producto", catalog = "proyecto_final")
public class Producto implements java.io.Serializable {

	/*
	 * Principales
	 */
	private Long idProducto;
	private String descripcion;
	private String observaciones;
	private Date fechaBaja;
	private Integer cantidadGustos;
	private Boolean activo;
	
	/*
	 * Relaciones
	 */
	private Comercio comercio;
	private CategoriaProducto categoria;
    private Set<ItemPedido> items = new HashSet<ItemPedido>(0);
    private Set<Precio> precios = new HashSet<Precio>(0);
    private Set<Salsa> salsas = new HashSet<Salsa>(0);
    
    /*
     * Auxiliares
     */
    private Double ultimoPrecio;

	public Producto() {
	}
	
	public Producto(Long idProducto, String descripcion, String observaciones, Integer cantidadGustos,
					CategoriaProducto categoria, Comercio comercio) {
		this.idProducto = idProducto;
		this.descripcion = descripcion;
		this.observaciones = observaciones;
		this.cantidadGustos = cantidadGustos;
		this.categoria = categoria;
		this.comercio = comercio;
	}
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_producto", unique = true, nullable = false)
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
    
    @Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
    @Column(name = "observaciones")
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
    @Column(name = "cantidad_gustos")
	public Integer getCantidadGustos() {
		return cantidadGustos;
	}
	public void setCantidadGustos(Integer cantidadGustos) {
		this.cantidadGustos = cantidadGustos;
	}

    @Column(name = "activo")
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
		
		/*Baja de salsas*/
		Set<Salsa> salsas = this.getSalsas();
		if(salsas.size() > 0) {
			for (Salsa s : salsas) {
			    if (s.getFechaBaja() == null) {
			    	s.setFechaBaja(fechaBaja);
			    }
			}			
		}
		
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comercio", nullable = false)	
    public Comercio getComercio() {
		return comercio;
	}

	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", nullable = false)
	public CategoriaProducto getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaProducto categoria) {
		this.categoria = categoria;
	}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	public Set<ItemPedido> getItems() {
		return items;
	}
	public void setItems(Set<ItemPedido> items) {
		this.items = items;
	}
    public boolean addItem(ItemPedido item) {
    	item.setProducto(this);
    	return this.getItems().add(item);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto", cascade = CascadeType.ALL)
	public Set<Precio> getPrecios() {
		return precios;
	}
	public void setPrecios(Set<Precio> precios) {
		this.precios = precios;
	}    
    public boolean addPrecio(Precio precio) {
    	precio.setProducto(this);
    	return this.getPrecios().add(precio);
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto", cascade = CascadeType.ALL)
	public Set<Salsa> getSalsas() {
		return salsas;
	}
	public void setSalsas(Set<Salsa> salsas) {
		this.salsas = salsas;
	}    
    public boolean addSalsa(Salsa salsa) {
    	salsa.setProducto(this);
    	return this.getSalsas().add(salsa);
    }

    @Transient
	public Double getUltimoPrecio() {
		return ultimoPrecio;
	}

	public void setUltimoPrecio(Double ultimoPrecio) {
		this.ultimoPrecio = ultimoPrecio;
	} 

}
