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

@Entity
@Table(name = "consulta", catalog = "proyecto_final")
public class Consulta {

	/*
	 * Principales
	 */
	private Long idConsulta;
	private String nombreRemitente;
	private String emailRemitente;
	private String asunto;
	private String mensaje;
	private Date fechaAlta;
	private Date fechaBaja;
	private String respuesta;
	private String estado = "P"; //P - Pendiente | C - Cerrada
	/*
	 * Relaciones
	 */
	private Usuario usuario;
	
	public Consulta() {
		
	}

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_consulta", unique = true, nullable = false)
	public Long getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(Long idConsulta) {
		this.idConsulta = idConsulta;
	}
	
    @Column(name = "nombre", nullable = false)
	public String getNombreRemitente() {
		return nombreRemitente;
	}
	public void setNombreRemitente(String nombreRemitente) {
		this.nombreRemitente = nombreRemitente;
	}
	
    @Column(name = "email", nullable = false)
	public String getEmailRemitente() {
		return emailRemitente;
	}
	public void setEmailRemitente(String emailRemitente) {
		this.emailRemitente = emailRemitente;
	}
	
	@Column(name = "asunto", nullable = false)
    public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	@Column(name = "mensaje", nullable = false)	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	@Column(name = "fecha_alta")
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_baja")
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@Column(name = "estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "respuesta")	
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

}
