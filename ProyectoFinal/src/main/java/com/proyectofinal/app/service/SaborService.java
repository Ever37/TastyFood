package com.proyectofinal.app.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Sabor;

public interface SaborService {

	Sabor findById(Long id) throws DataAccessException, Exception;
	
	boolean validaSaveSabor(Sabor sabor) throws DataAccessException, Exception;
	
    void saveSabor(Sabor sabor) throws DataAccessException, Exception;

	List<Sabor> findAllWithoutDate() throws DataAccessException, Exception;
	
	List<Sabor> findAllByComercio(Long id) throws DataAccessException, Exception;

	boolean deleteSabor(Long idSabor) throws DataAccessException, Exception;

	boolean updateSabor(Sabor sabor) throws DataAccessException, Exception;
}
