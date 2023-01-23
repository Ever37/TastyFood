package com.proyectofinal.app.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.TelefonoComercio;

@Repository("telefonoComercioDao")
public class TelefonoComercioDaoImp extends AbstractDao implements TelefonoComercioDao {

	/*
	 * Recupera los teléfonos de cada comercio
	 */
    @SuppressWarnings("unchecked")
	public List<TelefonoComercio> findByComercio(Long idComercio) throws DataAccessException, Exception {
    	Query query = this.getSession().createQuery(
    			"" + "select t " + "from " + "TelefonoComercio t "
    				+ "where t.comercio.idComercio = :idComercio "
    				+ "and t.fechaBaja is NULL")
    				.setParameter("idComercio", idComercio);
    	return query.list();   	
	}
    
    /*
     * Agrega fecha_baja cuando se da de baja un telefono.
     */
    public void delete(Long id) throws DataAccessException, Exception {
	
    	Date date = new Date();
    	TelefonoComercio telefono = (TelefonoComercio) this.findObjectById(TelefonoComercio.class, id);
    	if (telefono != null) {
    		telefono.setFechaBaja(date);
    		this.updateObject(telefono);
		} 
    }
}
