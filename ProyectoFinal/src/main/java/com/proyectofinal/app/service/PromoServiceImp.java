package com.proyectofinal.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.dao.PromoDao;
import com.proyectofinal.app.model.Promo;

@Service("promoService")
@Transactional
public class PromoServiceImp implements PromoService {

	@Autowired
	private PromoDao daoPromo;
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void savePromo(Promo promo) throws DataAccessException, Exception {
    	this.daoPromo.save(promo);
    }

	@Override
	public Promo findById(Long id) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Promo findPromoToday() throws DataAccessException, Exception {
		return this.daoPromo.findPromoToday();
	}	
}
