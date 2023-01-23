package com.proyectofinal.app.dao;

import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.RequerimientoPermiso;

public interface RequerimientoPermisoDao {

    RequerimientoPermiso findByPermisoAndRequerimiento(Integer idPermiso,
    	    Long idRequerimiento) throws DataAccessException, Exception;
}

