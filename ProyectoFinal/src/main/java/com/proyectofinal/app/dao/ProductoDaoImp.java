package com.proyectofinal.app.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Producto;

@Repository("productoDao")
public class ProductoDaoImp extends AbstractDao implements ProductoDao{

	/*
	 * Lista todos los productos
	 */
    @SuppressWarnings("unchecked")
	public List<Producto> findAll() throws DataAccessException, Exception {
		return this.findAllObject(Producto.class);
	}
    
    /*
     * Lista todos los productos sin fecha de baja
     */
    @SuppressWarnings("unchecked")
	public List<Producto> findAllWithoutDate() throws DataAccessException,
			Exception {
    	return this.findAllObjectWithoutDate(Producto.class);
	}
    
    /*
     * Busca un producto por id
     */
	public Producto findById(Long id) throws DataAccessException, Exception {
    	return (Producto) this.findObjectById(Producto.class, id);
	} 
	
    /*
     * Busca un producto por descripción
     */
    @SuppressWarnings("unchecked")
	public Producto findByName(String descripcion) throws DataAccessException,
			Exception {
    	List<Producto> productos = this.getSession().createCriteria(Producto.class)
    			.add(Restrictions.like("descripcion", descripcion)).list();
    	if (productos.size() > 0) {
    	    return productos.get(0);
    	} else {
    	    return null;
    	}
	}
    
    /*
     * Busca un producto por descripción y comercio
     */
    @SuppressWarnings("unchecked")
	public Producto findByNameAndComercio(String descripcion, Long idComercio) throws DataAccessException,
			Exception {
    	List<Producto> productos = this.getSession().createCriteria(Producto.class)
    			.add(Restrictions.like("descripcion", descripcion))
    			.add(Restrictions.eq("comercio.idComercio", idComercio))
    			.add(Restrictions.isNull("fechaBaja"))
    			.list();
    	if (productos.size() > 0) {
    	    return productos.get(0);
    	} else {
    	    return null;
    	}
	}
	
    /*
     * Busca productos por id de comercio
     */
    @SuppressWarnings("unchecked")
	public List<Producto> findByComercio(Long idComercio) throws DataAccessException, Exception {
    	Query query = this.getSession().createQuery(
    			"" + "select p " + "from " + "Producto p "
    				+ "where p.comercio.idComercio = :idComercio "
    				+ "and p.fechaBaja is NULL")
    				.setParameter("idComercio", idComercio);
    	return query.list();   	
	}
    
    /*
     * Busca productos por id de categoria
     */
    @SuppressWarnings("unchecked")
	public List<Producto> findByCategoria(Long idCategoria) throws DataAccessException, Exception {
    	Query query = this.getSession().createQuery(
    			"" + "select p " + "from " + "Producto p "
    				+ "where p.categoria.idCategoria = :idCategoria "
    				+ "and p.fechaBaja is NULL")
    				.setParameter("idCategoria", idCategoria);
    	return query.list();   	
	}
    
    /**
     * Alta de producto.
     */
    public Long save(Producto producto) throws DataAccessException, Exception {
    	return this.saveObject(producto);
    }
    
    /*
     * Agrega fecha_baja cuando se da de baja un comercio.
     */
    public Boolean delete(Long id) throws DataAccessException, Exception {
	
    	Date date = new Date();
    	Producto producto = (Producto) this.findObjectById(Producto.class, id);
    	if (producto != null) {
    		producto.setFechaBaja(date);
    		this.updateObject(producto);
    		return true;
		} else {
			return false;
		}
    }
    
    /*
     * Actualiza producto
     */
    public void update(Producto producto) throws DataAccessException, Exception {
    	this.updateObject(producto);
    }

    /*
     * Busca productos por descripcion de categoria
     */
    @SuppressWarnings("unchecked")
	public List<Producto> findByNameCategoria(String descripcion) throws DataAccessException, Exception {
    	Query query = this.getSession().createQuery(
    			"" + "select p " + "from " + "Producto p "
    				+ "where p.categoria.descripcion = :descripcion "
    				+ "and p.fechaBaja is NULL")
    				.setParameter("descripcion", descripcion);
    	return query.list();   	
	}
    
    /*
     * Busca productos por descripcion de categoria y comercio
     */
    @SuppressWarnings("unchecked")
	public List<Producto> findByNameCategoriaAndComercio(String descripcion, Long idComercio) 
			throws DataAccessException, Exception {
    	Query query = this.getSession().createQuery(
    			 "" + "select p " + "from " + "Producto p "
    				+ "where p.categoria.descripcion = :descripcion "
    				+ "and p.comercio.idComercio = :idComercio "
    				+ "and p.fechaBaja is NULL")
    				.setParameter("descripcion", descripcion)
    				.setParameter("idComercio", idComercio);
    	return query.list();   	
	}
}
