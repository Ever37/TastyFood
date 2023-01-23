package com.proyectofinal.app.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.RequerimientoPermisoGrupo;

@Repository("requerimientoPermisoGrupoDao")
public class RequerimientoPermisoGrupoDaoImp extends AbstractDao implements RequerimientoPermisoGrupoDao {
    
	public RequerimientoPermisoGrupo findById(Long id) throws DataAccessException, Exception {
    	return (RequerimientoPermisoGrupo) this.findObjectById(RequerimientoPermisoGrupo.class, id);
    }
	
}
