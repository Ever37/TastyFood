package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "pedido", catalog = "proyecto_final")
public class Pedido implements java.io.Serializable, Comparable<Pedido> {

	/*
	 * Principales
	 */
	private Long idPedido;
	private Date fechaHora; //Por DB.
	private String direccion;
	private String nro;
	private String piso;
	private String depto;
	private Double total;
	private String observaciones;
	private String conEnvio;
	private Double costoEnvio;
	private Long idUltimoEstado;
	
	/*
	 * Relaciones
	 */
	private Usuario usuario;
    private Comercio comercio;
    private Set<ItemPedido> items = new HashSet<ItemPedido>(0);
    private Set<EstadoDePedido> estados = new HashSet<EstadoDePedido>(0);
    private Set<PedidoCuenta> pedidosCuentas = new HashSet<PedidoCuenta>(0);
	
	public Pedido() {
	}
	
    public Pedido(String conEnvio, Comercio comercio, List<ItemPedido> items, Double total) {
    	this.conEnvio = conEnvio;
    	this.comercio = comercio;
    	this.total = total;
    	for(ItemPedido ip : items) {
    		this.items.add(ip);
    	}
	}
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_pedido", unique = true, nullable = false)
	public Long getIdPedido() {
		return idPedido;
	}
    //Generado por DB.
    public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora")
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}   
	
    @Column(name = "direccion")
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
    @Column(name = "nro")
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	
    @Column(name = "piso")
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	
    @Column(name = "depto")
	public String getDepto() {
		return depto;
	}
	public void setDepto(String depto) {
		this.depto = depto;
	}
	
    @Column(name = "total")
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
    @Column(name = "observaciones")
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

    @Column(name = "con_envio", nullable = false)
	public String getConEnvio() {
		return conEnvio;
	}
	public void setConEnvio(String conEnvio) {
		this.conEnvio = conEnvio;
	}
    
    @Column(name = "costo_envio", nullable = false)
	public Double getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(Double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	/*
	 * Revisar relación.
	 */
    @Column(name = "ultimo_estado", nullable = false)	
	public Long getIdUltimoEstado() {
		return idUltimoEstado;
	}
	public void setIdUltimoEstado(Long idUltimoEstado) {
		this.idUltimoEstado = idUltimoEstado;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comercio", nullable = false)
	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL)
	public Set<EstadoDePedido> getEstados() {
		return estados;
	}
	public void setEstados(Set<EstadoDePedido> estados) {
		this.estados = estados;
	}	
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL)
	public Set<PedidoCuenta> getPedidosCuentas() {
		return pedidosCuentas;
	}
	public void setPedidosCuentas(Set<PedidoCuenta> pedidosCuentas) {
		this.pedidosCuentas = pedidosCuentas;
	}
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL)
	public Set<ItemPedido> getItems() {
		return items;
	}
	public void setItems(Set<ItemPedido> items) {
		this.items = items;
	}

	/*
	 * Ordeno los pedidos por fecha de alta de forma descendiente.
	 */
	@Override
	public int compareTo(Pedido p) {
		int rta = 0;
		
		if(this.getFechaHora().before(p.getFechaHora())) {
			rta = -1;
		} else if(this.getFechaHora().after(p.getFechaHora())) {
			rta = 1;
		} 
		
		return rta;
	}

}
