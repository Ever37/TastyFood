package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.CategoriaProducto;

public interface CategoriaProductoDao {

	List<CategoriaProducto> findAll() throws DataAccessException, Exception;
	
	List<CategoriaProducto> findAllWithoutDate() throws DataAccessException, Exception;	
	
	CategoriaProducto findByName(String descripcion) throws DataAccessException, Exception;
	
	CategoriaProducto findById(Long id) throws DataAccessException, Exception;
	
    Long save(CategoriaProducto categoria) throws DataAccessException, Exception;
    
    Boolean delete(Long id) throws DataAccessException, Exception;
    
    void update(CategoriaProducto categoria) throws DataAccessException, Exception;
}
