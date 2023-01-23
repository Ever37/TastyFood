package com.proyectofinal.app.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Cuenta;

public interface CuentaDao {

	Long save(Cuenta cuenta) throws DataAccessException, Exception;

	List<Cuenta> findByComercio(Long idComercio) throws DataAccessException, Exception;
}
