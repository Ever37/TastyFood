package com.proyectofinal.app.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.proyectofinal.app.dto.PedidoDto;
import com.proyectofinal.app.model.EstadoDePedido;
import com.proyectofinal.app.model.EstadoPedido;

public interface EstadoService {

	EstadoPedido findById(Long idEstado, Boolean eager) throws DataAccessException, Exception;
	
	EstadoDePedido findEstadoDePedidoById(Long idEstado) throws DataAccessException, Exception;

	List<EstadoPedido> findAll() throws DataAccessException, Exception;

	List<PedidoDto> findByEstadoActual(List<PedidoDto> pedidos) throws DataAccessException, Exception;

	PedidoDto findByEstadoActual(PedidoDto pedido) throws DataAccessException, Exception;
	
	Long saveEstado(EstadoDePedido estadoDePedido) throws DataAccessException, Exception;
}
