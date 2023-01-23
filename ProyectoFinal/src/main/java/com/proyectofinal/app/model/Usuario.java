package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
@Table(name = "usuario", catalog = "proyecto_final")
public class Usuario implements java.io.Serializable{
	
	/*
	 * Principales
	 */
    private Long idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String telefono; //Teléfono y celular de contacto del vendedor.
    private String celular;
    private String email;
    private Double valoracion;
	private String observaciones;
    private String notificaciones; //Recibe emails promocionales
    private Date fechaAlta;
    private Date fechaBaja;
  
    /*
     * Relaciones
     */
    //private Comercio comercio;
    private Set<Pedido> pedidos = new HashSet<Pedido>(0);
    private Grupo grupo;
    
	public Usuario() {
	}
	
	public Usuario(String notificaciones) {
		this.notificaciones = notificaciones;
	}
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_usuario", unique = true, nullable = false)
	public Long getIdUsuario() {
		return idUsuario;
	}
    public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
    @Column(name = "nombre_usuario", nullable = false)
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
    @Column(name = "contrasena", nullable = false)
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
    @Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
    @Column(name = "apellido", nullable = false)
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
    @Column(name = "telefono")
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
    @Column(name = "celular")
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
    @Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
    @Column(name = "valoracion")
	public Double getValoracion() {
		return valoracion;
	}
	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}
	
    @Column(name = "observaciones")
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
    @Column(name = "notificaciones")
    public String getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(String notificaciones) {
		this.notificaciones = notificaciones;
	}

	@Temporal(TemporalType.TIMESTAMP)
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
	
	/*
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	*/

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grupo", nullable = false)
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}
