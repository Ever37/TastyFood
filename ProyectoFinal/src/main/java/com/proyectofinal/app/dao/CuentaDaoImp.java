package com.proyectofinal.app.dao;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Cuenta;

@Repository("cuentaDao")
public class CuentaDaoImp extends AbstractDao implements CuentaDao {

    /**
     * Alta de cuenta.
     */
    public Long save(Cuenta cuenta) throws DataAccessException, Exception {
    	return this.saveObject(cuenta);
    }

    @SuppressWarnings("unchecked")
	public List<Cuenta> findByComercio(Long idComercio) throws DataAccessException, Exception {
    	
		List<Cuenta> cuentas = this.getSession().createCriteria(Cuenta.class)
    			.add(Restrictions.like("comercio.idComercio", idComercio)).list();
		
    	if (cuentas.size() > 0) {
    	    return cuentas;
    	} else {
    	    return null;
    	}
	}
}
