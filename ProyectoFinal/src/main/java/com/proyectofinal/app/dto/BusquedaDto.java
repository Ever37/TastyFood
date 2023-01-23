package com.proyectofinal.app.dto;

import java.util.Date;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Usuario;

public class BusquedaDto {

	/*
	 * Principales
	 */
	private Date fechaDesde;
	private Date fechaHasta;
	//Uso estos 2 string para enviarlos por hidden POST.
	private String desde;
	private String hasta;
	private Double comision;
	/*
	 * Relaciones
	 */
	private Usuario usuario;	
	private Comercio comercio;
	
	public BusquedaDto() {
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Double getComision() {
		return comision;
	}

	public void setComision(Double comision) {
		this.comision = comision;
	}

	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	
}
