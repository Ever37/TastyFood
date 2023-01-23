package com.proyectofinal.app.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.EstadoDePedido;
import com.proyectofinal.app.model.EstadoPedido;
import com.proyectofinal.app.model.ItemPedido;
import com.proyectofinal.app.model.Usuario;

public class PedidoDto implements Comparable<PedidoDto> {

	/*
	 * Principales
	 */
	private Long idPedido;
	private Date fechaHora;
	private String direccion;
	private String nro;
	private String piso;
	private String depto;
	private Double total;
	private String observaciones;
	private String conEnvio;
	private Double costoEnvio;
	
	/*
	 * Relaciones
	 */
	private Usuario usuario;
    private Comercio comercio;
    private List<ItemPedido> items = new ArrayList<ItemPedido>(0);
    private List<EstadoDePedido> estados = new ArrayList<EstadoDePedido>(0);
    /*
     * Estados a los que puedo cambiar, según estado actual de pedido.
     */
    private List<EstadoPedido> estadosPermitidos = new ArrayList<EstadoPedido>(0);
    private EstadoDePedido ultimoEstadoDePedido;
    private EstadoPedido ultimoEstado;
    private Integer nroPedido;

    public PedidoDto() {
    	
	}
    
	public PedidoDto(String conEnvio, Comercio comercio, Double total) {
    	this.conEnvio = conEnvio;
    	this.comercio = comercio;
    	this.total = total;
	}
	   
    public PedidoDto(String conEnvio, Comercio comercio, Set<ItemPedido> items, Double total) {
    	this.conEnvio = conEnvio;
    	this.comercio = comercio;
    	this.total = total;
    	for(ItemPedido ip : items) {
    		this.items.add(ip);
    	}
	}

	public Long getIdPedido() {
		return idPedido;
	}	
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	
	public String getDepto() {
		return depto;
	}
	public void setDepto(String depto) {
		this.depto = depto;
	}
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getConEnvio() {
		return conEnvio;
	}
	public void setConEnvio(String conEnvio) {
		this.conEnvio = conEnvio;
	}
	
	public Double getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(Double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	
	public List<ItemPedido> getItems() {
		return items;
	}
	public void setItems(List<ItemPedido> items) {
		this.items = items;
	}
	
	public List<EstadoDePedido> getEstados() {
		return estados;
	}
	public void setEstados(List<EstadoDePedido> estados) {
		this.estados = estados;
	}

	public EstadoDePedido getUltimoEstadoDePedido() {
		return ultimoEstadoDePedido;
	}
	public void setUltimoEstadoDePedido(EstadoDePedido ultimoEstadoDePedido) {
		this.ultimoEstadoDePedido = ultimoEstadoDePedido;
	}

	public EstadoPedido getUltimoEstado() {
		return ultimoEstado;
	}
	public void setUltimoEstado(EstadoPedido ultimoEstado) {
		this.ultimoEstado = ultimoEstado;
	}

	public Integer getNroPedido() {
		return nroPedido;
	}

	public void setNroPedido(Integer nroPedido) {
		this.nroPedido = nroPedido;
	}

	public List<EstadoPedido> getEstadosPermitidos() {
		return estadosPermitidos;
	}

	public void setEstadosPermitidos(List<EstadoPedido> estadosPermitidos) {
		this.estadosPermitidos = estadosPermitidos;
	}

	/*
	 * Ordeno los pedidos por fecha de alta de forma descendiente.
	 */
	@Override
	public int compareTo(PedidoDto p) {
		int rta = 0;
		
		if(this.getFechaHora().before(p.getFechaHora())) {
			rta = -1;
		} else if(this.getFechaHora().after(p.getFechaHora())) {
			rta = 1;
		} 
		
		return rta;
	}

}
