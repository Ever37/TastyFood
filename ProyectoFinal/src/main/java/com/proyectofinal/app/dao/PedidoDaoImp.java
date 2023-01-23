package com.proyectofinal.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Pedido;
import com.proyectofinal.app.util.SingletonConstants;

@Repository("pedidoDao")
public class PedidoDaoImp extends AbstractDao implements PedidoDao {

    public Pedido findById(Long id) throws DataAccessException, Exception {
		Pedido pedido = (Pedido) this.getSession().createCriteria(Pedido.class)
						.add(Restrictions.eq("idPedido", id)).uniqueResult();
		return pedido;
	}
	
    /*
     * Busca los pedidos de un usuario.
     */
    @SuppressWarnings("unchecked")
	public List<Pedido> findPedidosByUsuario(Long idUsuario) throws DataAccessException,
			Exception {
    	List<Pedido> pedidos = this.getSession().createCriteria(Pedido.class)
    			.add(Restrictions.eq("usuario.idUsuario", idUsuario)).list();
    	if (pedidos.size() > 0) {
    	    return pedidos;
    	} else {
    	    return new ArrayList<Pedido>();
    	}
	}
    
    /*
     * Busca las ventas (pedidos) de un comercios.
     */
    @SuppressWarnings("unchecked")
	public List<Pedido> findPedidosByComercio(Long idComercio) throws DataAccessException,
			Exception {
    	List<Pedido> pedidos = this.getSession().createCriteria(Pedido.class)
    			.add(Restrictions.eq("comercio.idComercio", idComercio)).list();   	
    	if (pedidos.size() > 0) {
    	    return pedidos;
    	} else {
    	    return null;
    	}
	}
    
    /*
     * Busca las ventas (pedidos) de un comercios entre dos fechas.
     */
    @SuppressWarnings("unchecked")
	public List<Pedido> findPedidosByComercioAndDates(Long idComercio, Date fechaDesde, Date fechaHasta) 
			throws DataAccessException, Exception {

    	
    	List<Pedido> pedidos = this.getSession().createCriteria(Pedido.class)
    			.add(Restrictions.eq("comercio.idComercio", idComercio))
    			.add(Restrictions.ge("fechaHora", fechaDesde))
    			.add(Restrictions.le("fechaHora", fechaHasta))
    			.list();   	
    	if (pedidos.size() > 0) {
    	    return pedidos;
    	} else {
    	    return new ArrayList<Pedido>();
    	}
	}
    
    /*
     * Busca las ventas (pedidos) de un comercios entre dos fechas.
     */
    @SuppressWarnings("unchecked")
	public List<Pedido> findPedidosDevengables(Long idComercio, Date fechaDesde, Date fechaHasta) 
			throws DataAccessException, Exception {
    	
    	List<Pedido> pedidos = this.getSession().createCriteria(Pedido.class, "pedido")
    			.createAlias("estados", "estados")
    			.createAlias("estados.estadoPedido", "estadoPedido")
    			.add(Restrictions.eq("comercio.idComercio", idComercio))
    			.add(Restrictions.ge("fechaHora", fechaDesde))
    			.add(Restrictions.le("fechaHora", fechaHasta))
    			.add(
    			Restrictions.and(
    			Restrictions.not(Restrictions.eq("estadoPedido.descripcion",SingletonConstants.ESTADO_PEDIDO_RECHAZADO)),
    		    Restrictions.not(Restrictions.eq("estadoPedido.descripcion",SingletonConstants.ESTADO_PEDIDO_INICIADO)))
    			)
    			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
    			.list();   	
    	if (pedidos.size() > 0) {
    	    return pedidos;
    	} else {
    	    return new ArrayList<Pedido>();
    	}
	}
    
    /**
     * Alta de pedido.
     */
    public void save(Pedido pedido) throws DataAccessException, Exception {
       	this.saveObject(pedido);   		
    }   
    
    /*
     * Actualiza pedido
     */
    public void update(Pedido pedido) throws DataAccessException, Exception {	
    	this.updateObject(pedido);
    }
}
