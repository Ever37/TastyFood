package com.productos.app.service;


import java.util.List;

import com.productos.app.model.Producto;

public interface ProductoService {

	List<Producto> findAll();

	List<Producto> findAllWithoutDate();

	Producto findByName(String descripcion);

	Producto findById(Long id);

	void saveProducto(Producto producto);

	Boolean deleteProducto(Long id);

	Boolean updateProducto(Producto producto);
}
