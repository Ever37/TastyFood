package com.proyectofinal.app.service;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.dto.ComercioDto;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Horario;
import com.proyectofinal.app.model.TelefonoComercio;

public interface ComercioService {

	List<Comercio> findAll() throws DataAccessException, Exception;
	
	List<Comercio> findAllByDescriptionAndCategoriaProducto(String descripcion, String idCategoria) 
			throws DataAccessException, Exception;
	
	List<Comercio> findAllWithoutDate() throws DataAccessException, Exception;	
	
	List<Comercio> findAllWithDelivery() throws DataAccessException, Exception;
	
	List<Comercio> findAllOpened() throws DataAccessException, Exception;
	
	List<Comercio> findAllClosed() throws DataAccessException, Exception;	
	
	Comercio findById(Long id) throws DataAccessException, Exception;
	
	Comercio findByName(String descripcion) throws DataAccessException, Exception;
	
    Boolean deleteComercio(Long id) throws DataAccessException, Exception;
    
    void deleteTelefono(Long id) throws DataAccessException, Exception;
    
    void deleteHorario(Long idHorario) throws DataAccessException, Exception;
    
    boolean validaSaveComercio(ComercioDto comercio) throws DataAccessException, Exception;
    
    void saveComercio(ComercioDto comercio) throws DataAccessException, Exception;
    
    Comercio findByUsuario(Long id) throws DataAccessException, Exception;
    
    List<TelefonoComercio> findTelefonosComercio(Long id) throws DataAccessException, Exception;
    
    List<Horario> findHorariosComercio(Long id) throws DataAccessException, Exception;

    Boolean validaFranjaHorariaComercio(Long id) throws DataAccessException, Exception;
    
    Boolean updateComercio(ComercioDto comercio) throws DataAccessException, Exception;
}
