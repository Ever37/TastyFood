package com.proyectofinal.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Precio;

@Repository("precioDao")
public class PrecioDaoImp extends AbstractDao implements PrecioDao {
	
    /*
     * Busca un producto por id
     */
	public Precio findById(Long id) throws DataAccessException, Exception {
    	return (Precio) this.findObjectById(Precio.class, id);
	} 
	
    /*
     * Busca precios por id de producto
     */
    @SuppressWarnings("unchecked")
	public List<Precio> findPreciosByProducto(Long idProducto) throws DataAccessException, Exception {
    	Query query = this.getSession().createQuery(
    			"" + "select p " + "from " + "Precio p "
    				+ "where p.producto.idProducto = :idProducto ")
    				.setParameter("idProducto", idProducto);
    	return (List<Precio>) query.list();   	
	}
}
