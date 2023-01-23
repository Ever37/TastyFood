package com.productos.app.model;



import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "producto", catalog = "productos")
public class Producto implements java.io.Serializable {

	/*
	 * Principales
	 */
	private Long idProducto;
	private String descripcion;
	private String observaciones;
	private Date fechaBaja;
	private Boolean activo;

	public Producto() {
	}

	public Producto(Long idProducto, String descripcion, String observaciones) {
		this.idProducto = idProducto;
		this.descripcion = descripcion;
		this.observaciones = observaciones;
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
	}

}
