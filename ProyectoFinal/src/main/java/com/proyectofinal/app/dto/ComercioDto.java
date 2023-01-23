package com.proyectofinal.app.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.proyectofinal.app.model.Horario;
import com.proyectofinal.app.model.Opinion;
import com.proyectofinal.app.model.Producto;
import com.proyectofinal.app.model.TelefonoComercio;
import com.proyectofinal.app.model.Usuario;

public class ComercioDto {

	/*
	 * Principales
	 */
	private Long idComercio;
	private String descripcion;
	private String direccion;
	private String nro;
	private String piso;
	private String depto;
	private String cuit;
	private String realizaEnvios; // S - Si | N - No
	private Double compraMinima;
	private String tiempoEnvioMin;
	private String tiempoEnvioMax;
	private Double costoEnvio;
    private Double valoracionTotal;
    private String estado; // A - Abierto | C - Cerrado
	private Date fechaAlta;
	private Date fechaBaja;
	/*
	 * Relaciones
	 */
    private List<Producto> productos = new ArrayList<Producto>();
    private List<TelefonoComercio> telefonos = new ArrayList<TelefonoComercio>();
    private List<Opinion> opiniones = new ArrayList<Opinion>();
    private List<Horario> horarios = new ArrayList<Horario>();
    private Usuario usuario; 

	
	public ComercioDto() {

	}
	
	public ComercioDto(Long idComercio, String descripcion, String direccion, String nro, String piso, String depto,
			String cuit, String realizaEnvios, Double compraMinima, String tiempoEnvioMin, 
			String tiempoEnvioMax, Double costoEnvio, Usuario usuario, Double valoracionTotal) {

		this.idComercio = idComercio;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.nro = nro;
		this.piso = piso;
		this.depto = depto;
		this.cuit = cuit;
		this.realizaEnvios = realizaEnvios;
		this.compraMinima = compraMinima;
		this.tiempoEnvioMin = tiempoEnvioMin;
		this.tiempoEnvioMax = tiempoEnvioMax;
		this.costoEnvio = costoEnvio;
		this.usuario = usuario;
		this.valoracionTotal = valoracionTotal;
	}
	
	public ComercioDto(String realizaEnvios, Usuario usuario) {
		this.realizaEnvios = realizaEnvios;
		this.usuario = usuario;
	}

	public Long getIdComercio() {
		return idComercio;
	}
    public void setIdComercio(Long idComercio) {
		this.idComercio = idComercio;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRealizaEnvios() {
		return realizaEnvios;
	}
	public void setRealizaEnvios(String realizaEnvios) {
		this.realizaEnvios = realizaEnvios;
	}

	public Double getCompraMinima() {
		return compraMinima;
	}
	public void setCompraMinima(Double compraMinima) {
		this.compraMinima = compraMinima;
	}

	public String getTiempoEnvioMin() {
		return tiempoEnvioMin;
	}
	public void setTiempoEnvioMin(String tiempoEnvioMin) {
		this.tiempoEnvioMin = tiempoEnvioMin;
	}

	public String getTiempoEnvioMax() {
		return tiempoEnvioMax;
	}
	public void setTiempoEnvioMax(String tiempoEnvioMax) {
		this.tiempoEnvioMax = tiempoEnvioMax;
	}

	public Double getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(Double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public Double getValoracionTotal() {
		return valoracionTotal;
	}
	public void setValoracionTotal(Double valoracionTotal) {
		this.valoracionTotal = valoracionTotal;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<TelefonoComercio> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(List<TelefonoComercio> telefonos) {
		this.telefonos = telefonos;
	}

	public List<Opinion> getOpiniones() {
		return opiniones;
	}
	public void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
