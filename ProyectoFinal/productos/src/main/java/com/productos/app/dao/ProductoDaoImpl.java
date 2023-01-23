package com.productos.app.dao;



import java.util.Date;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.productos.app.model.Producto;

@Repository("productoDao")
public class ProductoDaoImpl extends AbstractDao implements ProductoDao {

	/*
	 * Lista todos los productos
	 */
	@SuppressWarnings("unchecked")
	public List<Producto> findAll()  {
		return this.findAllObject(Producto.class);
	}

	/*
	 * Lista todos los productos sin fecha de baja
	 */
	@SuppressWarnings("unchecked")
	public List<Producto> findAllWithoutDate() {
		return this.findAllObjectWithoutDate(Producto.class);
	}

	/*
	 * Busca un producto por id
	 */
	public Producto findById(Long id)  {
		return (Producto) this.findObjectById(Producto.class, id);
	}

	/*
	 * Busca un producto por descripcion
	 */
	@SuppressWarnings("unchecked")
	public Producto findByName(String descripcion) {
		List<Producto> productos = this.getSession().createCriteria(Producto.class)
				.add(Restrictions.like("descripcion", descripcion)).list();
		if (!productos.isEmpty()) {
			return productos.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Alta de producto.
	 */
	public Long save(Producto producto)  {
		return this.saveObject(producto);
	}

	/*
	 * Agrega fecha_baja cuando se da de baja un comercio.
	 */
	public Boolean delete(Long id)  {

		Date date = new Date();
		Producto producto = (Producto) this.findObjectById(Producto.class, id);
		if (producto != null) {
			producto.setFechaBaja(date);
			this.updateObject(producto);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Actualiza producto
	 */
	public void update(Producto producto)  {
		this.updateObject(producto);
	}

}
