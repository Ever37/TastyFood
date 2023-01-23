package com.proyectofinal.app.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.CategoriaProducto;

@Repository("categoriaProductoDao")
public class CategoriaProductoDaoImp extends AbstractDao implements CategoriaProductoDao{

	/*
	 * Lista todos los comercios
	 */
    @SuppressWarnings("unchecked")
	public List<CategoriaProducto> findAll() throws DataAccessException, Exception {
		return this.findAllObject(CategoriaProducto.class);
	}
    
    /*
     * Lista todas las categorias sin fecha de baja
     */
    @SuppressWarnings("unchecked")
	public List<CategoriaProducto> findAllWithoutDate() throws DataAccessException,
			Exception {
    	return this.findAllObjectWithoutDate(CategoriaProducto.class);
	}

    /*
     * Busca un comercio por id
     */
	public CategoriaProducto findById(Long id) throws DataAccessException, Exception {
    	return (CategoriaProducto) this.findObjectById(CategoriaProducto.class, id);
	}
	
    /*
     * Busca un categoria por descripción
     */
    @SuppressWarnings("unchecked")
	public CategoriaProducto findByName(String descripcion) throws DataAccessException,
			Exception {
    	List<CategoriaProducto> categorias = this.getSession().createCriteria(CategoriaProducto.class)
    			.add(Restrictions.like("descripcion", descripcion)).list();
    	if (categorias.size() > 0) {
    	    return categorias.get(0);
    	} else {
    	    return null;
    	}
	}
    
    
    /**
     * Alta de categoria.
     */
    public Long save(CategoriaProducto categoria) throws DataAccessException, Exception {
    	return this.saveObject(categoria);
    }
    
    /*
     * Agrega fecha_baja cuando se da de baja una categoria.
     */
    public Boolean delete(Long id) throws DataAccessException, Exception {
	
    	Date date = new Date();
    	CategoriaProducto categoria = (CategoriaProducto) this.findObjectById(CategoriaProducto.class, id);
    	if (categoria != null && categoria.getProductos().size() == 0) {
    		categoria.setFechaBaja(date);
    		this.updateObject(categoria);
    		return true;
		} else {
			return false;
		}
    }
    
    /**
     * Actualiza los datos de un usuario.
     */
    public void update(CategoriaProducto categoria) throws DataAccessException, Exception {    	   	
    	this.updateObject(categoria);
    }

}
