package com.proyectofinal.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Comercio;

@Repository("comercioDao")
public class ComercioDaoImp extends AbstractDao implements ComercioDao {

	/*
	 * Lista todos los comercios
	 */
    @SuppressWarnings("unchecked")
	public List<Comercio> findAll() throws DataAccessException, Exception {
		return this.findAllObject(Comercio.class);
	}

    /*
     * Lista todos los comercios sin fecha de baja
     */
    @SuppressWarnings("unchecked")
	public List<Comercio> findAllWithoutDate() throws DataAccessException,
			Exception {
    	return this.findAllObjectWithoutDate(Comercio.class);
	}

    /*
     * Lista todos los comercios que realizan envios
     */
    @SuppressWarnings("unchecked")
	public List<Comercio> findAllWithDelivery() throws DataAccessException,
			Exception {
    	return this.findAllObjectWithoutDate(Comercio.class);
	}

    /*
     * Busca un comercio por id
     */
	public Comercio findById(Long id) throws DataAccessException, Exception {
    	return (Comercio) this.findObjectById(Comercio.class, id);
	}

    /*
     * Busca un comercio por descripción
     */
    @SuppressWarnings("unchecked")
	public Comercio findByName(String descripcion) throws DataAccessException,
			Exception {
    	List<Comercio> comercios = this.getSession().createCriteria(Comercio.class)
    			.add(Restrictions.like("descripcion", descripcion)).list();
    	if (comercios.size() > 0) {
    	    return comercios.get(0);
    	} else {
    	    return null;
    	}
	}
    
    /*
     * Agrega fecha_baja cuando se da de baja un comercio.
     */
    public Boolean delete(Long id) throws DataAccessException, Exception {
	
    	Date date = new Date();
    	Comercio comercio = (Comercio) this.findObjectById(Comercio.class, id);
    	if (comercio != null) {
    		comercio.setFechaBaja(date);
    		this.updateObject(comercio);
    		return true;
		} else {
			return false;
		}
    }
    
    /**
     * Alta de comercio.
     */
    public Long save(Comercio comercio) throws DataAccessException, Exception {
    	return this.saveObject(comercio);
    }
    
    public Comercio findByUsuario(Long id) throws DataAccessException,
    Exception {
	Query query = this.getSession().createQuery(
		"" 	+ "select c " + "from Comercio c "
			+ "where c.usuario.idUsuario = :idUsuario ")
		.setParameter("idUsuario", id);

	return (Comercio) query.uniqueResult();
    }
    
    /**
     * Actualiza los datos de un comercio.
     */
    public void update(Comercio comercio) throws DataAccessException, Exception {    	   	
    	this.updateObject(comercio);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Comercio> findAllByDescriptionAndCategoriaProducto(String descripcion, String idCategoria)
			throws DataAccessException, Exception {
		
		List<Comercio> comercios = new ArrayList<Comercio>();
		if(idCategoria != null && !idCategoria.equals("")) {
			comercios = this.getSession().createCriteria(Comercio.class, "comercio")
	    			.createAlias("comercio.productos", "p")
	    			.add(Restrictions.isNull("p.fechaBaja"))
	    			.add(Restrictions.like("comercio.descripcion", "%" + descripcion + "%"))
	    			.add(Restrictions.isNull("comercio.fechaBaja"))
	    			.add(Restrictions.eq("p.categoria.idCategoria", Long.valueOf(idCategoria)))
	    			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
	    			.list();
		} else {
			comercios = this.getSession().createCriteria(Comercio.class, "comercio")
	    			.createAlias("comercio.productos", "p")
	    			.add(Restrictions.isNull("p.fechaBaja"))
	    			.add(Restrictions.like("comercio.descripcion", "%" + descripcion + "%"))
	    			.add(Restrictions.isNull("comercio.fechaBaja"))
	    			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
	    			.list();		
		}
		
    	if (comercios.size() > 0) {
    	    return comercios;
    	} else {
    	    return new ArrayList<Comercio>();
    	}		
	}
}
