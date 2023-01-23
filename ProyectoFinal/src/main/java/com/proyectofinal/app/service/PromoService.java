package com.proyectofinal.app.service;

import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Promo;

public interface PromoService {

	void savePromo(Promo promo) throws DataAccessException, Exception;
	
	Promo findById(Long id) throws DataAccessException, Exception;
	
	Promo findPromoToday() throws DataAccessException, Exception;
		
}
