package com.proyectofinal.app.dao;

import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.RequerimientoPermisoGrupo;

public interface RequerimientoPermisoGrupoDao {

    RequerimientoPermisoGrupo findById(Long id) throws DataAccessException,
    Exception;
}
