package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "estados_de_pedido", catalog = "proyecto_final")
public class EstadoDePedido implements java.io.Serializable {

	/*
	 * Principales
	 */
    private Long idEstadoDePedido;
    private Date fechaEstado;
    private String aclaraciones;

    /*
     * Relaciones
     */
    private Pedido pedido;  
    private EstadoPedido estadoPedido;
    private Usuario usuario;
    
    
    public EstadoDePedido() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_estado_de_pedido", unique = true, nullable = false)
	public Long getIdEstadoDePedido() {
		return idEstadoDePedido;
	}

	public void setIdEstadoDePedido(Long idEstadoDePedido) {
		this.idEstadoDePedido = idEstadoDePedido;
	}
       
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", nullable = false)
	public EstadoPedido getEstadoPedido() {
		return estadoPedido;
	}
	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pedido", nullable = false)
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}  

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_estado", length = 19)
    public Date getFechaEstado() {
	return this.fechaEstado;
    }

	public void setFechaEstado(Date fechaEstado) {
	this.fechaEstado = fechaEstado;
    }

    @Column(name = "aclaraciones", length = 100)
    public String getAclaraciones() {
		return aclaraciones;
	}

	public void setAclaraciones(String aclaraciones) {
		this.aclaraciones = aclaraciones;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    public Usuario getUsuario() {
	return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }
}
