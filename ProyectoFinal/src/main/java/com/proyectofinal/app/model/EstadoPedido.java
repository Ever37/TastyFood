package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Estado
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "estado_pedido", catalog = "proyecto_final")
public class EstadoPedido implements java.io.Serializable {

    public enum TipoEstadoPedido{

	INICIADO(1L, "Iniciado","naranja"),
	RECHAZADO(2L, "Rechazado","rojo"),
	RECEPCIONADO(3L, "Recepcionado","violeta"),
	DESECHADO(4L, "Desechado","negro"),
	//EN_CAMINO(5L,"En Camino","celeste"),
	LISTO(6L,"Listo","azul"),
	ENTREGADO(7L,"Entregado","verde");

	private final Long idEstadoPedido;
	private final String descripcion;
	private final String color;

	TipoEstadoPedido (Long idEstadoPedido, String descripcion, String color) {
	    this.idEstadoPedido = idEstadoPedido;
	    this.descripcion = descripcion;
	    this.color = color;
	}

	public Long getIdEstadoPedido() {
		return idEstadoPedido;
	}

	public String getDescripcion() {
	    return this.descripcion;
	}

	public String getColor() {
	    return this.color;
	}

    }
    
    /*
     * Principales
     */
    private Long idEstadoPedido;
    private String descripcion;
    private String color;
    
    /*
     * Relaciones
     */
   
    private Set<EstadoDePedido> estadosDePedido = new HashSet<EstadoDePedido>(0);

    public EstadoPedido() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_estado_pedido", unique = true, nullable = false)
    public Long getIdEstadoPedido() {
		return idEstadoPedido;
	}

	public void setIdEstadoPedido(Long idEstadoPedido) {
		this.idEstadoPedido = idEstadoPedido;
	}

    @Column(name = "descripcion", nullable = false, length = 100)
    public String getDescripcion() {
	return this.descripcion;
    }

	public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    @Column(name = "color", nullable = false, length = 20)
    public String getColor() {
	return this.color;
    }

    public void setColor(String color) {
	this.color = color;
    }
   
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estadoPedido")
    public Set<EstadoDePedido> getEstadosDePedido() {
		return estadosDePedido;
	}
	public void setEstadosDePedido(Set<EstadoDePedido> estadosDePedido) {
		this.estadosDePedido = estadosDePedido;
	}

	@Override
    public String toString() {
	return this.descripcion;
    }

}
