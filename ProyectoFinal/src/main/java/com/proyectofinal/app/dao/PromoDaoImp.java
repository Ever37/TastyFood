package com.proyectofinal.app.dao;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Promo;

@Repository("promoDao")
public class PromoDaoImp extends AbstractDao implements PromoDao {

    /**
     * Alta de consulta.
     */
    public void save(Promo promo) throws DataAccessException, Exception {
    	this.saveObject(promo);
    }


	@Override
	public Promo findPromoToday() throws DataAccessException, Exception {
		
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.MILLISECOND, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.HOUR, 0);
    	Date today = cal.getTime();
    	cal.add(Calendar.DAY_OF_YEAR, + 1);
    	Date tomorrow = cal.getTime();
    	
    	Promo promo = null;
    	Query query = this.getSession()
				.createQuery("SELECT p FROM Promo p WHERE p.fechaAlta BETWEEN :_today and :tomorrow")
				.setParameter("_today", today)
				.setParameter("tomorrow", tomorrow);

		if(query.uniqueResult() == null) {
			return promo;
		} else {
			promo = new Promo();
			promo = (Promo) query.uniqueResult();
			promo = (Promo) this.findObjectById(Promo.class, promo.getIdPromo());
			return promo;				
		}	  	
	}
}
