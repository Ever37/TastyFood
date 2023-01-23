package com.proyectofinal.app.dao;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Pedido;

public interface PedidoDao {
	
	Pedido findById(Long idPedido) throws DataAccessException, Exception;

	List<Pedido> findPedidosByUsuario(Long idUsuario) throws DataAccessException, Exception;
	
	List<Pedido> findPedidosByComercio(Long idComercio) throws DataAccessException, Exception;
	
	List<Pedido> findPedidosByComercioAndDates(Long idComercio, Date fechaHasta, Date fechaDesde) 
				throws DataAccessException, Exception;	
	
    void save(Pedido pedido) throws DataAccessException, Exception;

	List<Pedido> findPedidosDevengables(Long idComercio, Date fromDate, Date toDate)
						throws DataAccessException, Exception;
	
	void update(Pedido pedido) throws DataAccessException, Exception;
    
}
