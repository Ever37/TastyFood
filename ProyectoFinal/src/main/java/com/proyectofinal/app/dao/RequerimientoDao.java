package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Requerimiento;

public interface RequerimientoDao {

    Requerimiento findById(Long id) throws DataAccessException, Exception;

    List<Requerimiento> findAll() throws DataAccessException, Exception;

    List<Requerimiento> findAllByWorkflow(Boolean estado) throws DataAccessException, Exception;
}
