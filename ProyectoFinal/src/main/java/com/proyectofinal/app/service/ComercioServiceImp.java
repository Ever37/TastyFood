package com.proyectofinal.app.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.dao.ComercioDao;
import com.proyectofinal.app.dao.HorarioDao;
import com.proyectofinal.app.dao.ProductoDao;
import com.proyectofinal.app.dao.TelefonoComercioDao;
import com.proyectofinal.app.dao.UsuarioDao;
import com.proyectofinal.app.dto.ComercioDto;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Horario;
import com.proyectofinal.app.model.Producto;
import com.proyectofinal.app.model.TelefonoComercio;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.util.ClassToDto;
import com.proyectofinal.app.util.SingletonConstants;

@Service("comercioService")
@Transactional
public class ComercioServiceImp implements ComercioService {

    @Autowired
    private ComercioDao daoComercio;
    
    @Autowired
    private TelefonoComercioDao daoTelefonoComercio;
    
    @Autowired
    private UsuarioDao daoUsuario;

    @Autowired
    private HorarioDao daoHorario;
    
    @Autowired
    private ProductoDao daoProducto;
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
    			   rollbackFor = DataAccessException.class)
	public List<Comercio> findAll() throws DataAccessException, Exception {
		return this.daoComercio.findAll();
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Comercio> findAllWithoutDate() throws DataAccessException, Exception {
		List<Comercio> comercios = this.daoComercio.findAllWithoutDate();
		Comercio comercio;
		Set<TelefonoComercio> set;
		Iterator <Comercio> icomercios = comercios.iterator ();
		
		
		while(icomercios.hasNext()) {	
			
			comercio = icomercios.next();
			List<TelefonoComercio> lista = this.daoTelefonoComercio.findByComercio(comercio.getIdComercio());
			set = new HashSet<TelefonoComercio>(lista);
			comercio.setTelefonos(set);
		}
		
		return comercios;
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Comercio> findAllWithDelivery() throws DataAccessException,
			Exception {
		return this.daoComercio.findAllWithDelivery();
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public Comercio findById(Long id) throws DataAccessException, Exception {
		Comercio comercio = this.daoComercio.findById(id);
    	List<Producto> productos = this.daoProducto.findByComercio(id);
    	comercio.getProductos().addAll(productos);
		Hibernate.initialize(comercio.getProductos());
    	return comercio;
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public Comercio findByName(String descripcion) throws DataAccessException,
			Exception {
    	return this.daoComercio.findByName(descripcion);
	}

	@Override
	public List<Comercio> findAllOpened() throws DataAccessException, Exception {
		return null;
	}

	@Override
	public List<Comercio> findAllClosed() throws DataAccessException, Exception {
		return null;
	}
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Boolean deleteComercio(Long id) throws DataAccessException, Exception {
    	return this.daoComercio.delete(id);
    }
    
    @Transactional(readOnly = false)
    public boolean validaSaveComercio(ComercioDto comercio)
	    throws DataAccessException, Exception {
	
    	boolean rta = true;
    	Comercio com = this.daoComercio.findByName(comercio.getDescripcion());
    	
    	if(com == null) {
    		rta = true;
    	} else {
    		rta = false;
    	}
    	return rta;
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void saveComercio(ComercioDto comercio) throws DataAccessException, Exception {
    	
    	/*No me trae el Id de Usuario del Hidden, por eso lo busco acá de nuevo*/
    	Usuario usuario = this.daoUsuario.findByUsername(comercio.getUsuario().getNombreUsuario());
    	comercio.setUsuario(usuario);
    	comercio.setValoracionTotal(0.0);
    	   	  	
    	Comercio c = ClassToDto.dtoToComercio(comercio);
    	c.setActivo(true);
    	
    	/*Guardar todos los telefonos del comercio*/
    	for(TelefonoComercio tel : comercio.getTelefonos()) {
    		tel.setComercio(c);
    	}
    	c.getTelefonos().addAll(comercio.getTelefonos());
    	   	
    	/*Guarda los horarios nuevos al comercio*/      		
    	for(Horario hor : comercio.getHorarios()) {
    		
    		hor.setComercio(c);
    		/*
    		 * Switch dependiendo del día para hacer ordenamiento.
    		 */
    		switch(hor.getDia()) {
    			case "Domingo": {hor.setIndexDay(1); break;}
    			case "Lunes": {hor.setIndexDay(2); break;}
    			case "Martes": {hor.setIndexDay(3); break;}
    			case "Miercoles": {hor.setIndexDay(4); break;}
    			case "Jueves": {hor.setIndexDay(5); break;}
    			case "Viernes": {hor.setIndexDay(6); break;}
    			case "Sábado": {hor.setIndexDay(7); break;}
    		}
    	}
    	c.getHorarios().addAll(comercio.getHorarios());
    	 	 	
    	this.daoComercio.save(c);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public Comercio findByUsuario(Long id) throws DataAccessException, Exception {
		return this.daoComercio.findByUsuario(id);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<TelefonoComercio> findTelefonosComercio(Long id) throws DataAccessException, Exception {
		return this.daoTelefonoComercio.findByComercio(id);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Horario> findHorariosComercio(Long id) throws DataAccessException, Exception {
		return this.daoHorario.findByComercio(id);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void deleteTelefono(Long id) throws DataAccessException, Exception {
    	this.daoTelefonoComercio.delete(id);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public Boolean updateComercio(ComercioDto comercio) throws DataAccessException, Exception {

    	Comercio com = this.findByName(comercio.getDescripcion());
    	
    	/* Valida que no haya otro comercio con el mismo nombre, y
    	 * que no se haya encontrado el mismo comercio que se va a modificar.
    	 */
    	if(com == null || com.getIdComercio().equals(comercio.getIdComercio())) {
    		Comercio comercioUpdate = this.daoComercio.findById(comercio.getIdComercio());
    		comercioUpdate.setDescripcion(comercio.getDescripcion());
    		comercioUpdate.setDireccion(comercio.getDireccion());
    		comercioUpdate.setNro(comercio.getNro());
    		comercioUpdate.setPiso(comercio.getPiso());
    		comercioUpdate.setDepto(comercio.getDepto());
    		comercioUpdate.setCuit(comercio.getCuit());
    		comercioUpdate.setRealizaEnvios(comercio.getRealizaEnvios());
    		comercioUpdate.setCompraMinima(comercio.getCompraMinima());
    		comercioUpdate.setTiempoEnvioMin(comercio.getTiempoEnvioMin());
    		comercioUpdate.setTiempoEnvioMax(comercio.getTiempoEnvioMax());
    		comercioUpdate.setCostoEnvio(comercio.getCostoEnvio());
    		
        	/*Guardar los telefonos nuevos al comercio*/
        	for(TelefonoComercio tel : comercio.getTelefonos()) {
        		tel.setComercio(comercioUpdate);
        	}
        	comercioUpdate.getTelefonos().addAll(comercio.getTelefonos());
        	
        	/*Guarda los horarios nuevos al comercio*/      		
        	for(Horario hor : comercio.getHorarios()) {
        		
        		hor.setComercio(comercioUpdate);
        		/*
        		 * Switch dependiendo del día para hacer ordenamiento.
        		 */
        		switch(hor.getDia()) {
        			case "Domingo": {hor.setIndexDay(1); break;}
        			case "Lunes": {hor.setIndexDay(2); break;}
        			case "Martes": {hor.setIndexDay(3); break;}
        			case "Miercoles": {hor.setIndexDay(4); break;}
        			case "Jueves": {hor.setIndexDay(5); break;}
        			case "Viernes": {hor.setIndexDay(6); break;}
        			case "Sábado": {hor.setIndexDay(7); break;}
        		}
        	}
        	comercioUpdate.getHorarios().addAll(comercio.getHorarios());
    		
    		this.daoComercio.update(comercioUpdate);   		
    		return true;
    	} else {
    		return false;
    	}
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public Boolean validaFranjaHorariaComercio(Long id) throws DataAccessException, Exception {
		List<Horario> horarios = this.daoHorario.findByComercio(id);
		Calendar fecha = Calendar.getInstance();
		Date hoy=new Date();
		fecha.setTime(hoy);
		String today = SingletonConstants.dayOfWeek[fecha.get(Calendar.DAY_OF_WEEK) - 1 ];
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		int horaActual = fecha.get(Calendar.HOUR_OF_DAY);
		int minutosActual = fecha.get(Calendar.MINUTE);
		String horaCompletaString = horaActual + ":" + minutosActual + ":00";
		Date horaCompleta = dateFormat.parse(horaCompletaString);

		Boolean abierto = false;
		for(Horario h: horarios) {
			if(h.getDia().equals(today)) {
				if( (horaCompleta.after(h.getHoraDesde())|| horaCompleta.equals(h.getHoraDesde()))
						&&  horaCompleta.before(h.getHoraHasta())) {
					abierto = true;
					break;
				}
			}
		}
		
		return abierto;
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Comercio> findAllByDescriptionAndCategoriaProducto(String descripcion, String idCategoria) 
			throws DataAccessException, Exception {
		return this.daoComercio.findAllByDescriptionAndCategoriaProducto(descripcion, idCategoria);
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void deleteHorario(Long id) throws DataAccessException, Exception {
    	this.daoHorario.delete(id);
    }
    
}
