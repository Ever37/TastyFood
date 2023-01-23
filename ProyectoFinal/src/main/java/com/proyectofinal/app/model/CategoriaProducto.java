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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "categoria_de_producto", catalog = "proyecto_final")
public class CategoriaProducto implements java.io.Serializable {

	/*
	 * Principales
	 */
	private Long idCategoria;
	private String descripcion;
	private Date fechaBaja;
	/*
	 * Relaciones
	 */
    private Set<Producto> productos = new HashSet<Producto>(0);
	
	public CategoriaProducto() {
	}
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_categoria", unique = true, nullable = false)
	public Long getIdCategoria() {
		return idCategoria;
	}
    //Generado por DB.
    public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
    @Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_baja")
	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
	public Set<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}
    public boolean addProducto(Producto producto) {
	producto.setCategoria(this);
	return this.getProductos().add(producto);
    }
	
}
