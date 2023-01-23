package com.proyectofinal.app.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Pedido;

public class CuentaDto {

	private Long idCuenta;
	private Date fechaDesde;
	private Date fechaHasta;
	private Double comision;
	private Double comisionPorcentual;
	private Double montoTotal;
	private Date fechaAlta;
	
	private Comercio comercio;
    private List<Pedido> pedidos = new ArrayList<Pedido>(0);
	//Uso estos 2 string para enviarlos por hidden POST.
	private String desde;
	private String hasta;
	
    public Long getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}
	
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	public Double getComision() {
		return comision;
	}
	public void setComision(Double comision) {
		this.comision = comision;
	}

	public Double getComisionPorcentual() {
		return comisionPorcentual;
	}
	public void setComisionPorcentual(Double comisionPorcentual) {
		this.comisionPorcentual = comisionPorcentual;
	}
	
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public String getDesde() {
		return desde;
	}
	public void setDesde(String desde) {
		this.desde = desde;
	}
	
	public String getHasta() {
		return hasta;
	}
	public void setHasta(String hasta) {
		this.hasta = hasta;
	}
    
	
	
}
