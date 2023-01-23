package com.proyectofinal.app.service;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.dto.CuentaDto;
import com.proyectofinal.app.model.Cuenta;

public interface CuentaService {

	List<CuentaDto> generateCuentas(Double comision, Date fechaDesde, Date fechaHasta) 
			throws DataAccessException, Exception;

	void saveAllCuentas(List<CuentaDto> cuentas) throws DataAccessException, Exception;

	List<Cuenta> findByComercio(Long idComercio) throws DataAccessException, Exception;
	
	List<CuentaDto> findDtoByComercio(Long idComercio) throws DataAccessException, Exception;
}
