package com.proyectofinal.app.service;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.dto.PedidoDto;
import com.proyectofinal.app.model.CategoriaProducto;
import com.proyectofinal.app.model.ItemPedido;
import com.proyectofinal.app.model.Pedido;

public interface PedidoService {
	
	Pedido findById(Long idPedido) throws DataAccessException, Exception;

	PedidoDto findDtoById(Long idPedido) throws DataAccessException, Exception;
	
	List<Pedido> findPedidosByUsuario(Long idUsuario) throws DataAccessException, Exception;
	
	List<Pedido> findPedidosByComercio(Long idComercio) throws DataAccessException, Exception;
	
	List<Pedido> findPedidosByComercioAndDates(Long idComercio, Date fechaDesde, Date fechaHasta) 
			throws DataAccessException, Exception;
	
	int generateNroItem() throws DataAccessException, Exception;
	
	Double calcularSubtotalItem(ItemPedido itemPedido, CategoriaProducto categoria) throws DataAccessException, Exception;

	PedidoDto createPedido() throws DataAccessException, Exception;
	
    void savePedido(Pedido pedido) throws DataAccessException, Exception;
    
    List<PedidoDto> sorterListPedidos(List<Pedido> pedidos) throws DataAccessException, Exception;

	List<Pedido> findPedidosDevengables(Long idComercio, Date fechaDesde, Date fechaHasta)
			throws DataAccessException, Exception;
	
	Boolean validaCarritoVacio() throws DataAccessException, Exception;
	
    void updatePedido(Pedido pedido) throws DataAccessException, Exception;
    
}
