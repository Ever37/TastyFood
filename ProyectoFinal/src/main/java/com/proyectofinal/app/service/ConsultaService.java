package com.proyectofinal.app.service;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Consulta;

public interface ConsultaService {

	void saveConsulta(Consulta consulta) throws DataAccessException, Exception;
	
	List<Consulta> findAllWithoutDate() throws DataAccessException, Exception;
	
	Consulta findById(Long id) throws DataAccessException, Exception;
	
	Boolean deleteConsulta(Long id) throws DataAccessException, Exception;
	
	void updateConsulta(Consulta consulta) throws DataAccessException, Exception;
}
