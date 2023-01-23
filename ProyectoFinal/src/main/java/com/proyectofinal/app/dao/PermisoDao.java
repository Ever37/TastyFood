package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Permiso;

public interface PermisoDao {

    public List<Permiso> findAllPermisos() throws DataAccessException, Exception;
    
}
