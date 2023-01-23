package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Permiso;

@Repository("permisoDao")
public class PermisoDaoImp extends AbstractDao implements PermisoDao {

    /**
     * Trae todos los permisos
     */
    @SuppressWarnings("unchecked")
    public List<Permiso> findAllPermisos() throws DataAccessException, Exception {
    	return this.findAllObject(Permiso.class);
    }
    
}
