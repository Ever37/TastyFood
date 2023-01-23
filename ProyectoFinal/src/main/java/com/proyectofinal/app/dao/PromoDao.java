package com.proyectofinal.app.dao;

import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.model.Promo;

public interface PromoDao {

    void save(Promo promo) throws DataAccessException, Exception;
    
    Promo findPromoToday() throws DataAccessException, Exception;
    
}
