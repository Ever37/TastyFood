package com.proyectofinal.app.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Sabor;

@Repository("saborDao")
public class SaborDaoImp extends AbstractDao implements SaborDao {

    /*
     * Busca un sabor por id
     */
	public Sabor findById(Long id) throws DataAccessException, Exception {
    	return (Sabor) this.findObjectById(Sabor.class, id);
	}
	
    /*
     * Busca un sabor por descripción y comercio
     */
    @SuppressWarnings("unchecked")
	public Sabor findByNameAndComercio(String descripcion, Long idComercio) throws DataAccessException,
			Exception {
    	List<Sabor> sabores = this.getSession().createCriteria(Sabor.class)
    			.add(Restrictions.like("descripcion", descripcion))
    			.add(Restrictions.eq("comercio.idComercio", idComercio))
    			.add(Restrictions.isNull("fechaBaja"))
    			.list();
    	if (sabores.size() > 0) {
    	    return sabores.get(0);
    	} else {
    	    return null;
    	}
	}
    
    /*
     * Busca los sabores de un comercio
     */
    @SuppressWarnings("unchecked")
	public List<Sabor> findAllByComercio(Long idComercio) throws DataAccessException,
			Exception {
    	List<Sabor> sabores = this.getSession().createCriteria(Sabor.class)
    			.add(Restrictions.eq("comercio.idComercio", idComercio))
    			.add(Restrictions.eq("activo", true))
    			.add(Restrictions.isNull("fechaBaja"))
    			.list();
    	if (sabores.size() > 0) {
    	    return sabores;
    	} else {
    	    return null;
    	}
	}
    
    /**
     * Alta de sabor.
     */
    public Long save(Sabor sabor) throws DataAccessException, Exception {
    	return this.saveObject(sabor);
    }
    
    /*
     * Actualiza sabor
     */
    public void update(Sabor sabor) throws DataAccessException, Exception {	
    	this.updateObject(sabor);
    }
    
    /*
     * Lista todos los sabores sin fecha de baja
     */
    @SuppressWarnings("unchecked")
	public List<Sabor> findAllWithoutDate() throws DataAccessException,
			Exception {
    	return this.findAllObjectWithoutDate(Sabor.class);
	}
    
    /*
     * Agrega fecha_baja cuando se da de baja un sabor.
     */
    public Boolean delete(Long id) throws DataAccessException, Exception {	
    	Date date = new Date();
    	Sabor sabor = (Sabor) this.findObjectById(Sabor.class, id);
    	if (sabor != null) {
    		sabor.setFechaBaja(date);
    		this.updateObject(sabor);
    		return true;
		} else {
			return false;
		}
    }
	
}
