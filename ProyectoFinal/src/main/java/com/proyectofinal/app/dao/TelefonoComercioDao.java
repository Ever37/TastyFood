package com.proyectofinal.app.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.proyectofinal.app.model.TelefonoComercio;

public interface TelefonoComercioDao {

	List<TelefonoComercio> findByComercio(Long idComercio) throws DataAccessException, Exception;
	
    void delete(Long id) throws DataAccessException, Exception;
	
}
