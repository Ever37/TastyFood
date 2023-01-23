package com.proyectofinal.app.service;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.CategoriaProducto;

public interface CategoriaProductoService {

	List<CategoriaProducto> findAll() throws DataAccessException, Exception;
	
	List<CategoriaProducto> findAllWithoutDate() throws DataAccessException, Exception;
	
	List<CategoriaProducto> findProductoGroupByCategoria(Long id) throws DataAccessException, Exception;
	
	CategoriaProducto findByName(String descripcion) throws DataAccessException, Exception;
	
	CategoriaProducto findById(Long id) throws DataAccessException, Exception;
	
    boolean validaSaveCategoria(CategoriaProducto categoria) throws DataAccessException, Exception;
    
    void saveCategoria(CategoriaProducto categoria) throws DataAccessException, Exception;
    
    Boolean deleteCategoria(Long id) throws DataAccessException, Exception;
    
    Boolean updateCategoria(CategoriaProducto categoria) throws DataAccessException, Exception;
}
