package com.productos.app.dao;



import java.util.List;

import com.productos.app.model.Producto;


public interface ProductoDao {

	List<Producto> findAll();

	List<Producto> findAllWithoutDate();

	Producto findById(Long id);

	Producto findByName(String descripcion);

	Long save(Producto producto);

	Boolean delete(Long id);

	void update(Producto producto);

}
