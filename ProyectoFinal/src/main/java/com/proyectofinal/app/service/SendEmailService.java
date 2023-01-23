package com.proyectofinal.app.service;

import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Usuario;

public interface SendEmailService {

	void emailChargeStatus(String email, Usuario usuario, String aclaraciones)
			throws DataAccessException, Exception;
}
