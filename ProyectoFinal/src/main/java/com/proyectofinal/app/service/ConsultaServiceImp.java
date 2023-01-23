package com.proyectofinal.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.dao.ConsultaDao;
import com.proyectofinal.app.model.Consulta;

@Service("consultaService")
@Transactional
public class ConsultaServiceImp implements ConsultaService {

	@Autowired
	private ConsultaDao daoConsulta;
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void saveConsulta(Consulta consulta) throws DataAccessException, Exception {
    	this.daoConsulta.save(consulta);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Consulta> findAllWithoutDate() throws DataAccessException,
			Exception {
    	return this.daoConsulta.findAllWithoutDate();
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public Consulta findById(Long id) throws DataAccessException, Exception {
		return this.daoConsulta.findById(id);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Boolean deleteConsulta(Long id) throws DataAccessException, Exception {
    	Consulta consulta = this.daoConsulta.findById(id);
    	if(consulta.getEstado().equals("P")) {
    		return false;
    	} else {
    		return this.daoConsulta.delete(id);
    	}   	
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void updateConsulta(Consulta consulta) throws DataAccessException, Exception {
    	
    		Consulta con = this.daoConsulta.findById(consulta.getIdConsulta());
    		con.setRespuesta(consulta.getRespuesta());
    		con.setEstado("C");   		
    		this.daoConsulta.update(con);
    }
}
