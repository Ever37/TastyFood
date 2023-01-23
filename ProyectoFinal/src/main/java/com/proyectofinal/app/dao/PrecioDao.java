package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Precio;

public interface PrecioDao {
	
	List<Precio> findPreciosByProducto(Long id) throws DataAccessException, Exception;
	
	Precio findById(Long id) throws DataAccessException, Exception;
	
}
