package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Salsa;

public interface SalsaDao {

	List<Salsa> findByProducto(Long id) throws DataAccessException, Exception;
	
	Salsa findSalsaById(Long id) throws DataAccessException, Exception;
	
	Boolean delete(Long id) throws DataAccessException, Exception;
	
}
