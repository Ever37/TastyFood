package com.proyectofinal.app.dao;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.proyectofinal.app.model.Consulta;


@Repository("consultaDao")
public class ConsultaDaoImp extends AbstractDao implements ConsultaDao {

    /**
     * Alta de consulta.
     */
    public Long save(Consulta consulta) throws DataAccessException, Exception {
    	return this.saveObject(consulta);
    }
    
    /*
     * Busca una consulta por id
     */
	public Consulta findById(Long id) throws DataAccessException, Exception {
    	return (Consulta) this.findObjectById(Consulta.class, id);
	}
	
    /*
     * Lista todos las consultas sin fecha de baja
     */
    @SuppressWarnings("unchecked")
	public List<Consulta> findAllWithoutDate() throws DataAccessException,
			Exception {
    	return this.findAllObjectWithoutDate(Consulta.class);
	}
    
    /*
     * Agrega fecha_baja cuando se da de baja una consulta.
     */
    public Boolean delete(Long id) throws DataAccessException, Exception {
	
    	Date date = new Date();
    	Consulta consulta = (Consulta) this.findObjectById(Consulta.class, id);
    	if (consulta != null) {
    		consulta.setFechaBaja(date);
    		this.updateObject(consulta);
    		return true;
		} else {
			return false;
		}
    }
    
    /**
     * Actualiza los datos de una consulta.
     */
    public void update(Consulta consulta) throws DataAccessException, Exception {    	   	
    	this.updateObject(consulta);
    }
    
}