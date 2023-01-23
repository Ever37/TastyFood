package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "telefono_comercio", catalog = "proyecto_final")
public class TelefonoComercio implements java.io.Serializable {

	/*
	 * Principales
	 */
	private Long idTelefono;
	private String numero;
	private Date fechaBaja;
	/*
	 * Relaciones
	 */
	private Comercio comercio;
		
	public TelefonoComercio() {
	}
	
    public TelefonoComercio(Long idTelefono, String numero, Date fechaBaja) {
		this.idTelefono = idTelefono;
		this.numero = numero;
		this.fechaBaja = fechaBaja;
	}

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_telefono_comercio", unique = true, nullable = false)
	public Long getIdTelefono() {
		return idTelefono;
	}
    //Generado por DB.
    public void setIdTelefono(Long idTelefono) {
		this.idTelefono = idTelefono;
	}
	
    @Column(name = "numero", nullable = false)
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_baja")
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "comercio", nullable = false)
	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	
}
