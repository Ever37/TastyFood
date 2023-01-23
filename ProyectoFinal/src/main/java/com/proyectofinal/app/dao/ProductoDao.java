package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Producto;

public interface ProductoDao {

	List<Producto> findAll() throws DataAccessException, Exception;
	
	List<Producto> findAllWithoutDate() throws DataAccessException, Exception;	
	
	Producto findById(Long id) throws DataAccessException, Exception;
	
	Producto findByName(String descripcion) throws DataAccessException, Exception;

	List<Producto> findByNameCategoria(String descripcion) throws DataAccessException, Exception;
	
	List<Producto> findByNameCategoriaAndComercio(String descripcion, Long idComercio)
			throws DataAccessException, Exception;
	
	Producto findByNameAndComercio(String descripcion, Long idComercio) throws DataAccessException, Exception;
	
	List<Producto> findByComercio(Long idComercio) throws DataAccessException, Exception;
	
	List<Producto> findByCategoria(Long idCategoria) throws DataAccessException, Exception;
	
    Long save(Producto producto) throws DataAccessException, Exception;
    
    Boolean delete(Long id) throws DataAccessException, Exception;
    
    void update(Producto producto) throws DataAccessException, Exception;
}
