package com.proyectofinal.app.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.dto.PedidoDto;
import com.proyectofinal.app.model.EstadoDePedido;
import com.proyectofinal.app.model.EstadoPedido;

@Repository("estadoDao")
public class EstadoDaoImp extends AbstractDao implements EstadoDao {

    /*
     * Busca un estado por id.
     */
	public EstadoPedido findById(Long idEstado) throws DataAccessException, Exception {
		return (EstadoPedido) this.findObjectById(EstadoPedido.class, idEstado);
	}
	
    /*
     * Busca un estado de pedido por id.
     */
	public EstadoDePedido findEstadoDePedidoById(Long idEstadoDePedido) throws DataAccessException, Exception {
		return (EstadoDePedido) this.findObjectById(EstadoDePedido.class, idEstadoDePedido);
	}

	public Long findMaxIdEstadoDePedido() throws DataAccessException, Exception {		
		Query query = this.getSession()
				.createQuery("SELECT MAX(idEstadoDePedido) FROM EstadoDePedido");	
		if(query.uniqueResult() == null) {
			return 0L;
		} else {
			return (Long) query.uniqueResult();				
		}	
	}
	
    /**
     * Alta de estado de pedido.
     */
    public Long save(EstadoDePedido estadoDePedido) throws DataAccessException, Exception {
        	return this.saveObject(estadoDePedido);   		
    }

	/*
	 * Lista todos los estados posibles, menos Iniciado.
	 */
    @SuppressWarnings("unchecked")
	public List<EstadoPedido> findAll() throws DataAccessException, Exception {    	
    	List<EstadoPedido> estados = this.getSession().createCriteria(EstadoPedido.class)
    			.add(Restrictions.not(Restrictions.like("descripcion", "Iniciado")))
    			.list();
    	return estados;
	}

	@Override
	public List<PedidoDto> findByEstadoActual(List<PedidoDto> pedidos) throws DataAccessException, Exception {
    	
		List<PedidoDto> dto = new ArrayList<PedidoDto>();
		for(PedidoDto ped : pedidos) {				
			dto.add(estadosSegunEstadoActualPedido(ped));
		}

    	return dto;
	}

	@Override
	public PedidoDto findByEstadoActual(PedidoDto pedido) throws DataAccessException, Exception {		
		return estadosSegunEstadoActualPedido(pedido);
	}
	
    @SuppressWarnings("unchecked")
	public PedidoDto estadosSegunEstadoActualPedido(PedidoDto pedido) throws DataAccessException, Exception {
		
    	List<EstadoPedido> estados = new ArrayList<EstadoPedido>();
    	
		switch(pedido.getUltimoEstado().getDescripcion()) {
			case "Iniciado": {
				Criterion criterio = Restrictions.or(
						Restrictions.eq("descripcion", "Rechazado"),
						Restrictions.eq("descripcion", "Recepcionado"));
				
				estados = this.getSession().createCriteria(EstadoPedido.class)
		    			.add(criterio)	
		    			.list();
				break;
			}
			case "Recepcionado": {
				Criterion criterio = 
						Restrictions.eq("descripcion", "Listo");
				estados = this.getSession().createCriteria(EstadoPedido.class)
		    			.add(criterio)	
		    			.list();
				break;
			}
			case "Listo": {
				Criterion criterio = Restrictions.or(
						Restrictions.eq("descripcion", "Desechado"),
						Restrictions.eq("descripcion", "Entregado"));
				estados = this.getSession().createCriteria(EstadoPedido.class)
		    			.add(criterio)
		    			.list();
				break;
			}
			case "Rechazado": {
				break;
			}
			case "Desechado": {
				
				break;
			}
			case "Entregado": {
				
				break;
			}
			default: {
				System.out.println("Estado no encontrado");
			}
		}
		
		/*
		 * Para que no me genere error, anulo la lista de estados de pedido.
		 * No la necesito.
		 */	
		for(EstadoPedido ep: estados) {
			ep.setEstadosDePedido(null);
		}
			
		pedido.getEstadosPermitidos().addAll(estados);
		return pedido;
	}

}
