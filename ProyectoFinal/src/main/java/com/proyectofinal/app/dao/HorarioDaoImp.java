package com.proyectofinal.app.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Horario;

@Repository("horarioDao")
public class HorarioDaoImp extends AbstractDao implements HorarioDao {

	/*
	 * Recupera los horarios de cada comercio
	 */
    @SuppressWarnings("unchecked")
	public List<Horario> findByComercio(Long idComercio) throws DataAccessException, Exception {
    	Query query = this.getSession().createQuery(
    			"" + "select h " + "from " + "Horario h "
    				+ "where h.comercio.idComercio = :idComercio "
    				+ "and h.fechaBaja is NULL")
    				.setParameter("idComercio", idComercio);
    	return query.list();   	
	}
    
    /*
     * Agrega fecha_baja cuando se da de baja una salsa.
     */
    public Boolean delete(Long id) throws DataAccessException, Exception {
	
    	Date date = new Date();
    	Horario horario = (Horario) this.findObjectById(Horario.class, id);
    	if (horario != null) {
    		horario.setFechaBaja(date);
    		this.updateObject(horario);
    		return true;
		} else {
			return false;
		}
    }
}
