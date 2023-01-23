package com.proyectofinal.app.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.dao.EstadoDao;
import com.proyectofinal.app.dto.PedidoDto;
import com.proyectofinal.app.model.EstadoDePedido;
import com.proyectofinal.app.model.EstadoPedido;

@Service("estadoService")
@Transactional
public class EstadoServiceImp implements EstadoService {

	@Autowired
	EstadoDao daoEstado;
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public EstadoPedido findById(Long idEstado, Boolean eager) throws DataAccessException, Exception {
		EstadoPedido estadoPedido = this.daoEstado.findById(idEstado);
		if(eager) {
			Hibernate.initialize(estadoPedido.getEstadosDePedido());			
		}
		return estadoPedido;
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public EstadoDePedido findEstadoDePedidoById(Long idEstado) throws DataAccessException, Exception {
		return this.daoEstado.findEstadoDePedidoById(idEstado);
	}

	@Override
	public List<EstadoPedido> findAll() throws DataAccessException, Exception {
		return this.daoEstado.findAll();
	}

	@Override
	public List<PedidoDto> findByEstadoActual(List<PedidoDto> pedidos) throws DataAccessException, Exception {
		return this.daoEstado.findByEstadoActual(pedidos);
	}

	@Override
	public PedidoDto findByEstadoActual(PedidoDto pedido) throws DataAccessException, Exception {
		return this.daoEstado.findByEstadoActual(pedido);
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
	public Long saveEstado(EstadoDePedido estadoDePedido) throws DataAccessException, Exception {
    	return this.daoEstado.save(estadoDePedido);
	}

}
