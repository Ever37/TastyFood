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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "comercio", catalog = "proyecto_final")
public class Comercio implements java.io.Serializable, Comparable<Comercio> {

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
    private Boolean activo; // Puede estar inactivo temporalmente.
	private Date fechaAlta;
	private Date fechaBaja;
	/*
	 * Relaciones
	 */
    private Set<Producto> productos = new HashSet<Producto>(0);
    private Set<Pedido> pedidos = new HashSet<Pedido>(0);
    private Set<TelefonoComercio> telefonos = new HashSet<TelefonoComercio>(0);
    private Set<Opinion> opiniones = new HashSet<Opinion>(0);
    private Set<Horario> horarios = new HashSet<Horario>(0);
    private Set<Sabor> sabores = new HashSet<Sabor>(0);
    private Set<Cuenta> cuentas = new HashSet<Cuenta>(0);
    private Usuario usuario; 
	/*
	 * Auxiliares
	 */
    private Boolean abierto;
    private Boolean tienePromo = false;
    
    
	public Comercio() {

	}
	
	public Comercio(String realizaEnvios, Usuario usuario) {
		this.realizaEnvios = realizaEnvios;
		this.usuario = usuario;
	}
	
	public Comercio(String descripcion, String direccion, String nro, String piso, String depto,
					String cuit, String realizaEnvios, Double compraMinima, String tiempoEnvioMin, 
					String tiempoEnvioMax, Double costoEnvio, Usuario usuario, Double valoracionTotal) {
		
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
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_comercio", unique = true, nullable = false)
	public Long getIdComercio() {
		return idComercio;
	}
    //Generado por DB.
    public void setIdComercio(Long idComercio) {
		this.idComercio = idComercio;
	}
	
    @Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
    @Column(name = "direccion", nullable = false)
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
    @Column(name = "nro", nullable = false)	
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
	
    @Column(name = "cuit")
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
    @Column(name = "realiza_envios", nullable = false)
	public String getRealizaEnvios() {
		return realizaEnvios;
	}
	public void setRealizaEnvios(String realizaEnvios) {
		this.realizaEnvios = realizaEnvios;
	}
	
    @Column(name = "compra_minima")	
	public Double getCompraMinima() {
		return compraMinima;
	}
	public void setCompraMinima(Double compraMinima) {
		this.compraMinima = compraMinima;
	}
     
	@Column(name = "tiempo_envio_min")
    public String getTiempoEnvioMin() {
		return tiempoEnvioMin;
	}
	public void setTiempoEnvioMin(String tiempoEnvioMin) {
		this.tiempoEnvioMin = tiempoEnvioMin;
	}

	@Column(name = "tiempo_envio_max")
	public String getTiempoEnvioMax() {
		return tiempoEnvioMax;
	}
	public void setTiempoEnvioMax(String tiempoEnvioMax) {
		this.tiempoEnvioMax = tiempoEnvioMax;
	}

	@Column(name = "costo_envio")
	public Double getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(Double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	
    @Column(name = "valoracion_total", nullable = false)
	public Double getValoracionTotal() {
		return valoracionTotal;
	}

	public void setValoracionTotal(Double valoracionTotal) {
		this.valoracionTotal = valoracionTotal;
	}
	
	@Column(name = "activo", nullable = false)
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Column(name = "fecha_alta", nullable = false)	
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
		
		/*
		Set<Opinion> opiniones = this.getOpiniones();
		for (Opinion opinion : opiniones) {
		    if (opinion.getFechaBaja() == null) {
		    	opinion.setFechaBaja(fechaBaja);
		    }
		}
		
		Set<TelefonoComercio> telefonos = this.getTelefonos();
		for (TelefonoComercio telefono : telefonos) {
		    if (telefono.getFechaBaja() == null) {
		    	telefono.setFechaBaja(fechaBaja);
		    }
		}
		
		Set<Producto> productos = this.getProductos();
		for (Producto producto : productos) {
		    if (producto.getFechaBaja() == null) {
		    	producto.setFechaBaja(fechaBaja);
		    }
		}

		Set<Horario> horarios = this.getHorarios();
		for (Horario horario : horarios) {
		    if (horario.getFechaBaja() == null) {
		    	horario.setFechaBaja(fechaBaja);
		    }
		}
		*/
		
	}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comercio", cascade = CascadeType.ALL)
	public Set<Producto> getProductos() {
		return productos;
	}
	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comercio")
    public Set<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public boolean addProducto(Producto producto) {
	producto.setComercio(this);
	return this.getProductos().add(producto);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comercio", cascade = CascadeType.ALL)    
	public Set<TelefonoComercio> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(Set<TelefonoComercio> telefonos) {
		this.telefonos = telefonos;
	}
    public boolean addTelefono(TelefonoComercio telefono) {
    	telefono.setComercio(this);
    	return this.getTelefonos().add(telefono);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comercio", cascade = CascadeType.ALL)  
	public Set<Horario> getHorarios() {
		return horarios;
	}
	public void setHorarios(Set<Horario> horarios) {
		this.horarios = horarios;
	}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comercio")  
	public Set<Sabor> getSabores() {
		return sabores;
	}
	public void setSabores(Set<Sabor> sabores) {
		this.sabores = sabores;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comercio") 
	public Set<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comercio")  
	public Set<Opinion> getOpiniones() {
		return opiniones;
	}
	public void setOpiniones(Set<Opinion> opiniones) {
		this.opiniones = opiniones;
	}
	
	@OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int compareTo(Comercio comercio) {
		//Ordenar de forma forma descendente por fecha de alta.
		return 0;
	}

	@Transient
	public Boolean getAbierto() {
		return abierto;
	}
	public void setAbierto(Boolean abierto) {
		this.abierto = abierto;
	}

	@Transient
	public Boolean getTienePromo() {
		return tienePromo;
	}
	public void setTienePromo(Boolean tienePromo) {
		this.tienePromo = tienePromo;
	}

    
}
