package com.proyectofinal.app.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Sabor;

public interface SaborDao {

	Sabor findById(Long id) throws DataAccessException, Exception;
	
	Sabor findByNameAndComercio(String descripcion, Long idComercio) throws DataAccessException, Exception;
	
    Long save(Sabor sabor) throws DataAccessException, Exception;

	List<Sabor> findAllWithoutDate() throws DataAccessException, Exception;
	
	List<Sabor> findAllByComercio(Long id) throws DataAccessException, Exception;

	Boolean delete(Long id) throws DataAccessException, Exception;

	void update(Sabor saborUpdate) throws DataAccessException, Exception;
}
