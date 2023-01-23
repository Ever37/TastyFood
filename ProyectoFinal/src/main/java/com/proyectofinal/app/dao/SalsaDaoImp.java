package com.proyectofinal.app.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Salsa;

@Repository("salsaDao")
public class SalsaDaoImp extends AbstractDao implements SalsaDao {

	/*
	 * Recupera las salsas de un producto
	 */
    @SuppressWarnings("unchecked")
	public List<Salsa> findByProducto(Long idProducto) throws DataAccessException, Exception {
    	Query query = this.getSession().createQuery(
    			"" + "select s " + "from " + "Salsa s "
    				+ "where s.producto.idProducto = :idProducto "
    				+ "and s.fechaBaja is NULL")
    				.setParameter("idProducto", idProducto);
    	return query.list();   	
	}

    /*
     * Busca un producto por id
     */
	public Salsa findSalsaById(Long id) throws DataAccessException, Exception {
    	return (Salsa) this.findObjectById(Salsa.class, id);
	} 
	
    /*
     * Agrega fecha_baja cuando se da de baja una salsa.
     */
    public Boolean delete(Long id) throws DataAccessException, Exception {
	
    	Date date = new Date();
    	Salsa salsa = (Salsa) this.findObjectById(Salsa.class, id);
    	if (salsa != null) {
    		salsa.setFechaBaja(date);
    		this.updateObject(salsa);
    		return true;
		} else {
			return false;
		}
    }
}
