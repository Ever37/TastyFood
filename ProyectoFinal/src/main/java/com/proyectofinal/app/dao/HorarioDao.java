package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Horario;

public interface HorarioDao {

	List<Horario> findByComercio(Long id) throws DataAccessException, Exception;
	
	Boolean delete(Long id) throws DataAccessException, Exception;
}
