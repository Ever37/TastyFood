package com.proyectofinal.app.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Requerimiento;

@Repository("requerimientoDao")
public class RequerimientoDaoImp extends AbstractDao implements RequerimientoDao {

    public Requerimiento findById(Long id) throws DataAccessException,Exception {
    	return (Requerimiento) this.findObjectById(Requerimiento.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public List<Requerimiento> findAll() throws DataAccessException, Exception {
    	return this.findAllObject(Requerimiento.class);
    }
    
    @SuppressWarnings("unchecked")
    public List<Requerimiento> findAllByWorkflow(Boolean estado)
	    throws DataAccessException, Exception {
	Query query = this.getSession().createQuery(
		"" + "select r " + "from  Requerimiento r "
		   + "where r.workflow = :estado").setParameter("estado", estado);
	return query.list();
    }
    
}
