package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Comercio;

public interface ComercioDao {

	List<Comercio> findAll() throws DataAccessException, Exception;
	
	List<Comercio> findAllWithoutDate() throws DataAccessException, Exception;	
	
	List<Comercio> findAllWithDelivery() throws DataAccessException, Exception;
	
	Comercio findById(Long id) throws DataAccessException, Exception;
	
	Comercio findByName(String descripcion) throws DataAccessException, Exception;
	
    Boolean delete(Long id) throws DataAccessException, Exception;
	
    Long save(Comercio comercio) throws DataAccessException, Exception;
    
    Comercio findByUsuario(Long id) throws DataAccessException, Exception;
    
    void update(Comercio comercio) throws DataAccessException, Exception;

	List<Comercio> findAllByDescriptionAndCategoriaProducto(String descripcion, String idCategoria)
			throws DataAccessException, Exception;
    
}
