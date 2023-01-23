package com.proyectofinal.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.dao.ComercioDao;
import com.proyectofinal.app.dao.SaborDao;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Sabor;

@Service("saborService")
@Transactional
public class SaborServiceImp implements SaborService {
	
	@Autowired
	private SaborDao daoSabor;
	
	@Autowired
	private ComercioDao daoComercio;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public Sabor findById(Long id) throws DataAccessException, Exception {
		Sabor sabor = this.daoSabor.findById(id);
		sabor.getSaboresItem();
    	return sabor;
	}
    
    @Transactional(readOnly = false)
    public boolean validaSaveSabor(Sabor sabor)
	    throws DataAccessException, Exception {
	
		Comercio comercio = this.daoComercio.findById(sabor.getComercio().getIdComercio());
		sabor.setComercio(comercio);
    	
    	boolean rta = true;
    	Sabor sab = this.daoSabor.findByNameAndComercio(sabor.getDescripcion(), sabor.getComercio().getIdComercio());
    	if(sab == null) {
    		rta = true;
    		this.saveSabor(sabor);
    	} else {
    		rta = false;
    	}
    	return rta;
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void saveSabor(Sabor sabor) throws DataAccessException, Exception {   	  	
    	sabor.getComercio().getSabores().add(sabor);
    	this.daoSabor.save(sabor);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Sabor> findAllWithoutDate() throws DataAccessException,
			Exception {
    	return this.daoSabor.findAllWithoutDate();
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Sabor> findAllByComercio(Long id) throws DataAccessException,
			Exception {
    	return this.daoSabor.findAllByComercio(id);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public boolean deleteSabor(Long id) throws DataAccessException, Exception {
    	return this.daoSabor.delete(id);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public boolean updateSabor(Sabor sabor) throws DataAccessException, Exception {

    	Sabor sab = this.daoSabor.findByNameAndComercio(sabor.getDescripcion(), sabor.getComercio().getIdComercio());
    	
    	/* Valida que no haya otro sabor con el mismo nombre, y
    	 * que no se haya encontrado el mismo producto que se va a modificar.
    	 */
    	if(sab == null || sab.getIdSabor().equals(sabor.getIdSabor())) {
    		   		
    		Sabor saborUpdate = this.daoSabor.findById(sabor.getIdSabor());
    		saborUpdate.setComercio(sabor.getComercio());
    		saborUpdate.setDescripcion(sabor.getDescripcion());
    		saborUpdate.setActivo(sabor.getActivo());
    		   		
    		this.daoSabor.update(saborUpdate);   		
    		return true;
    	} else {
    		return false;
    	}
    }
    

}
