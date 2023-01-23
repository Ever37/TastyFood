package com.proyectofinal.app.dao;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.RequerimientoPermiso;

@Repository("requerimientoPermisoDao")
public class RequerimientoPermisoDaoImp extends AbstractDao implements RequerimientoPermisoDao {

    public RequerimientoPermiso findByPermisoAndRequerimiento(Integer idPermiso, Long idRequerimiento)
		    throws DataAccessException, Exception {
	
     Query query = this.getSession()
		.createQuery(
			""
				+ "select rp "
				+ "from  RequerimientoPermiso rp "
				+ "where rp.requerimiento.idRequerimiento = :idRequerimiento "
				+ "and rp.permiso.idPermiso = :idPermiso")
		.setParameter("idRequerimiento", idRequerimiento)
		.setParameter("idPermiso", idPermiso);
	return (RequerimientoPermiso) query.uniqueResult();
    }

}