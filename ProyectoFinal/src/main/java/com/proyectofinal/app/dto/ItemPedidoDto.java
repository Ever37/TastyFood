package com.proyectofinal.app.dto;

import java.util.ArrayList;
import java.util.List;
import com.proyectofinal.app.model.Pedido;
import com.proyectofinal.app.model.Producto;
import com.proyectofinal.app.model.Sabor;
import com.proyectofinal.app.model.Salsa;

public class ItemPedidoDto {

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
	private Salsa salsa;
	private List<Sabor> sabores = new ArrayList<Sabor>();
		
	public ItemPedidoDto() {

	}
	
	public Long getIdItem() {
		return idItem;
	}
	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}
	
	public Integer getNroItem() {
		return nroItem;
	}
	public void setNroItem(Integer nroItem) {
		this.nroItem = nroItem;
	}
	
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getAclaraciones() {
		return aclaraciones;
	}
	public void setAclaraciones(String aclaraciones) {
		this.aclaraciones = aclaraciones;
	}
	
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Salsa getSalsa() {
		return salsa;
	}
	public void setSalsa(Salsa salsa) {
		this.salsa = salsa;
	}
	public List<Sabor> getSabores() {
		return sabores;
	}
	public void setSabores(List<Sabor> sabores) {
		this.sabores = sabores;
	}
	
}
