package com.proyectofinal.app.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.proyectofinal.app.model.CategoriaProducto;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.ItemPedido;
import com.proyectofinal.app.model.Precio;
import com.proyectofinal.app.model.Sabor;
import com.proyectofinal.app.model.Salsa;

public class ProductoDto {
	
	/*
	 * Principales
	 */
	private Long idProducto;
	private String descripcion;
	private String observaciones;
	private Integer cantidadGustos;
	private Date fechaBaja;
	private Boolean activo;
	/*
	 * Relaciones
	 */
	private Comercio comercio;
	private CategoriaProducto categoria;
    private List<ItemPedido> items = new ArrayList<ItemPedido>();
    private List<Precio> precios = new ArrayList<Precio>();
    private List<Salsa> salsas = new ArrayList<Salsa>();
    private List<Sabor> sabores = new ArrayList<Sabor>();
	
	public ProductoDto() {
	}
	
	public ProductoDto(Comercio comercio) {
		this.comercio = comercio;
	}
	
	public ProductoDto(Long idProducto, String descripcion, String observaciones, Integer cantidadGustos,
				CategoriaProducto categoria, Comercio comercio) {
		this.idProducto = idProducto;
		this.descripcion = descripcion;
		this.observaciones = observaciones;
		this.cantidadGustos = cantidadGustos;
		this.categoria = categoria;
		this.comercio = comercio;
	}
	
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getCantidadGustos() {
		return cantidadGustos;
	}
	public void setCantidadGustos(Integer cantidadGustos) {
		this.cantidadGustos = cantidadGustos;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}

	public CategoriaProducto getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaProducto categoria) {
		this.categoria = categoria;
	}

	public List<ItemPedido> getItems() {
		return items;
	}
	public void setItems(List<ItemPedido> items) {
		this.items = items;
	}

	public List<Precio> getPrecios() {
		return precios;
	}
	public void setPrecios(List<Precio> precios) {
		this.precios = precios;
	}

	public List<Salsa> getSalsas() {
		return salsas;
	}
	public void setSalsas(List<Salsa> salsas) {
		this.salsas = salsas;
	}

	public List<Sabor> getSabores() {
		return sabores;
	}
	public void setSabores(List<Sabor> sabores) {
		this.sabores = sabores;
	}
	
}
