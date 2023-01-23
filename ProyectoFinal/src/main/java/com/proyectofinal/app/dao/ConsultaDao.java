package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Consulta;

public interface ConsultaDao {

	Long save(Consulta consulta) throws DataAccessException, Exception;
	
	Consulta findById(Long id) throws DataAccessException, Exception;
	
	List<Consulta> findAllWithoutDate() throws DataAccessException, Exception;	
	
    Boolean delete(Long id) throws DataAccessException, Exception;
    
    void update(Consulta consulta) throws DataAccessException, Exception;

}
