package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import java.util.HashSet;
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

@Entity
@Table(name = "cuenta", catalog = "proyecto_final")
public class Cuenta {

	/*
	 * Principales
	 */
	private Long idCuenta;
	private Date fechaDesde;
	private Date fechaHasta;
	private Double comision;
	private Double comisionPorcentual;
	private Double montoTotal;
	private Date fechaAlta;
	
	/*
	 * Relaciones
	 */
	private Comercio comercio;
    private Set<PedidoCuenta> pedidosCuentas = new HashSet<PedidoCuenta>(0);

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_cuenta", unique = true, nullable = false)
	public Long getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_desde")
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_hasta")
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	@Column(name = "comision", nullable = false)	
	public Double getComision() {
		return comision;
	}
	public void setComision(Double comision) {
		this.comision = comision;
	}

	@Column(name = "comision_porcentual", nullable = false)	
	public Double getComisionPorcentual() {
		return comisionPorcentual;
	}
	public void setComisionPorcentual(Double comisionPorcentual) {
		this.comisionPorcentual = comisionPorcentual;
	}
	
	@Column(name = "monto_total", nullable = false)	
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comercio")
	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta", cascade = CascadeType.ALL)
	public Set<PedidoCuenta> getPedidosCuentas() {
		return pedidosCuentas;
	}
	public void setPedidosCuentas(Set<PedidoCuenta> pedidosCuentas) {
		this.pedidosCuentas = pedidosCuentas;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_alta")
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

}
