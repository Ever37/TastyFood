package com.proyectofinal.app.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.dao.CategoriaProductoDao;
import com.proyectofinal.app.dao.ComercioDao;
import com.proyectofinal.app.dao.EstadoDao;
import com.proyectofinal.app.dao.PedidoDao;
import com.proyectofinal.app.dao.PrecioDao;
import com.proyectofinal.app.dao.SaborDao;
import com.proyectofinal.app.dao.SalsaDao;
import com.proyectofinal.app.dao.UsuarioDao;
import com.proyectofinal.app.dto.PedidoDto;
import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.CategoriaProducto;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.EstadoDePedido;
import com.proyectofinal.app.model.EstadoPedido;
import com.proyectofinal.app.model.ItemPedido;
import com.proyectofinal.app.model.Pedido;
import com.proyectofinal.app.model.Precio;
import com.proyectofinal.app.model.Salsa;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.util.ClassToDto;

@Service("pedidoService")
@Transactional
public class PedidoServiceImp implements PedidoService {
		
	@Autowired
	ProductoService daoProducto;
	
	@Autowired
	CategoriaProductoDao daoCategoriaProducto;
	
	@Autowired
	ComercioDao daoComercio;
	
	@Autowired
	PrecioDao daoPrecio;
	
	@Autowired
	PedidoDao daoPedido;
	
	@Autowired
	SalsaDao daoSalsa;
	
	@Autowired
	EstadoDao daoEstado;
	
	@Autowired
	UsuarioDao daoUsuario;
	
	@Autowired
	SaborDao daoSabor;
	
    @Autowired
    Carrito shoppingCart;
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public PedidoDto findDtoById(Long id) throws DataAccessException, Exception {
    	
    	Pedido pedido = this.daoPedido.findById(id);
    	/*
    	 * A partir del objeto pedido, construyo un objeto dto.
    	 */
    	PedidoDto dto = ClassToDto.pedidoToDto(pedido);
    	dto.setUltimoEstado((this.daoEstado.findEstadoDePedidoById(pedido.getIdUltimoEstado())).getEstadoPedido());
    	
    	Iterator<ItemPedido> it = pedido.getItems().iterator();
    	ItemPedido ip = new ItemPedido();
    	List<Precio> precios = new ArrayList<Precio>();
        while(it.hasNext()) {
        	ip = it.next();

        	if(ip.getProducto().getCategoria().getDescripcion().equals("Helados")) {
        		Hibernate.initialize(ip.getSaboresItem());           		
        	}
        	
        	/*
        	 * A MODIFICAR POR HISTORIAL DE PRECIO
        	 */
        	precios = this.daoPrecio.findPreciosByProducto(ip.getProducto().getIdProducto());
        	ip.setPrecio(precios.get(0).getValor());
            dto.getItems().add(ip);
    
        } 
        
        dto.setUsuario(pedido.getUsuario());
		
		return dto;
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
    public Pedido findById(Long idPedido) throws DataAccessException, Exception {   	   	
    	Pedido pedido = this.daoPedido.findById(idPedido);
    	Hibernate.initialize(pedido.getEstados());
    	return pedido;
    }
      
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
    public List<Pedido> findPedidosByUsuario(Long idUsuario) throws DataAccessException, Exception {   	   	
    	List<Pedido> pedidos = this.daoPedido.findPedidosByUsuario(idUsuario);
    	return pedidos;
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
    public List<Pedido> findPedidosByComercio(Long idComercio) throws DataAccessException, Exception {
    	return this.daoPedido.findPedidosByComercio(idComercio);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
    public List<Pedido> findPedidosByComercioAndDates(Long idComercio, Date fechaDesde, Date fechaHasta) 
    		throws DataAccessException, Exception {
    	/*
    	 * Se busca entre dos fechas desde la hora 0.
    	 */
    	Date fromTimestamp = fechaDesde;
    	Date toTimestamp = fechaHasta;
    	Date fromDate = this.getDateWithoutTime(fromTimestamp);
    	Date toDate = this.getDateWithoutTime(this.getTomorrowDate(toTimestamp));
    	return this.daoPedido.findPedidosByComercioAndDates(idComercio, fromDate, toDate);
    }
    
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
 public List<Pedido> findPedidosDevengables(Long idComercio, Date fechaDesde, Date fechaHasta) 
 		throws DataAccessException, Exception {
 	/*
 	 * Se busca entre dos fechas desde la hora 0.
 	 */
 	Date fromTimestamp = fechaDesde;
 	Date toTimestamp = fechaHasta;
 	Date fromDate = this.getDateWithoutTime(fromTimestamp);
 	Date toDate = this.getDateWithoutTime(this.getTomorrowDate(toTimestamp));
 	return this.daoPedido.findPedidosDevengables(idComercio, fromDate, toDate);
    }
    
    /*
     * Métodos para setear la hora en 0 del día.
     */
    public Date getDateWithoutTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public Date getTomorrowDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }
	
	public int generateNroItem() throws DataAccessException, Exception {
		
		int max = 1;
		List<ItemPedido> items = this.shoppingCart.getItemsDePedido();
		for(ItemPedido ip : items) {
				if(ip.getNroItem() >= max) {
					max = ip.getNroItem() + 1;
				}
		}			
		return max;
	}

	public Double calcularSubtotalItem(ItemPedido itemPedido, CategoriaProducto categoria) throws DataAccessException, Exception {
		
	    List<Precio> precios = this.daoPrecio
	    					   .findPreciosByProducto(itemPedido.getProducto().getIdProducto());
	    
	    /*
	     * Cambiar cuando haga historial de precios.
	     */
		double subtotal = itemPedido.getCantidad() * precios.get(0).getValor();
		
		switch (categoria.getDescripcion()) {
			case "Helados" : {
				break;
			}		
			case "Pizza" : {
				break;
			}
			case "Pastas" : {
				/*
				 * Sumar precio de la salsa.
				 */
				break;
			}
		}

		return subtotal;
	}
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
    public PedidoDto createPedido() throws DataAccessException, Exception {

 	   PedidoDto pedido = new PedidoDto();
 	   	   
       CategoriaProducto categoriaProducto;
 	   List<Precio> precios = new ArrayList<Precio>();
 	   Double total = 0.0;
 	   List<ItemPedido> carrito = this.shoppingCart.getItemsDePedido();
 	   for(ItemPedido i: carrito) {
 		   categoriaProducto = this.daoCategoriaProducto.findById(i.getProducto().getCategoria().getIdCategoria());
 		   i.getProducto().setCategoria(categoriaProducto);
 		   /*
 		    * No tendría que usar un service, sino un dao.
 		    */
 		   precios = this.daoProducto.findPreciosByProducto(i.getProducto().getIdProducto());
 		   i.setPrecio(precios.get(0).getValor());
 		   total += i.getSubtotal();
 		   if(i.getProducto().getCategoria().getDescripcion().equals("Pastas")) {
 			   Salsa salsa = this.daoSalsa.findSalsaById(Long.parseLong(i.getSalsa()));
 			   i.setSalsaItem(salsa);
 			   total += salsa.getPrecio() * i.getCantidad();
 		   }
 	   }	   
 	   
 	   /*
 	    * validar que los productos cargados sean todos del mismo comercio.
 	    */
 	   if(this.shoppingCart.getComercio() != null) {
 		   Long idComercio = this.shoppingCart.getComercio().getIdComercio();
 		   Comercio comercio = this.daoComercio.findById(idComercio);
 		   Hibernate.initialize(comercio.getProductos());
 		   
 		   /*
 		   if(comercio.getRealizaEnvios().equals("S")) {
 	 		   total += comercio.getCostoEnvio();		
 	 		   pedido.setConEnvio("S");
 		   }
 		   */
 		   pedido.setComercio(comercio);
 	   }
 	   pedido.getItems().addAll(carrito);
 	   pedido.setTotal(total);
 	   
 	   /*
 	    * El usuario se lo podemos setear en el preview, por si se tiene que loguear.
 	    */
   	
       return pedido;
    }
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void savePedido(Pedido pedido) throws DataAccessException, Exception {

 	   EstadoPedido estado = this.daoEstado
  				.findById(EstadoPedido.TipoEstadoPedido.INICIADO.getIdEstadoPedido());

 	   EstadoDePedido estadoDePedido = new EstadoDePedido();	   
 	   estadoDePedido.setUsuario(pedido.getUsuario());
 	   estadoDePedido.setPedido(pedido);
 	   estadoDePedido.setEstadoPedido(estado);
 	   estadoDePedido.setAclaraciones("");
 	   
 	   pedido.getEstados().add(estadoDePedido);
 	   Long idUltimoEstadoDePedido = this.daoEstado.findMaxIdEstadoDePedido();
 	   pedido.setIdUltimoEstado(idUltimoEstadoDePedido + 1);
 	   estado.getEstadosDePedido().add(estadoDePedido);
 	   
 	   for(ItemPedido ip: pedido.getItems()) {
 		   ip.setPedido(pedido);
 		   if(!ip.getProducto().getCategoria().getDescripcion().equals("Pastas")) {
 			  ip.setSalsaItem(null);
 		   }
 	   }
 	   this.daoPedido.save(pedido);		   
    }
  
    /*
     * Convierto una lista de pedidos en dto, recupero los estados y ordeno los pedidos.
     *
     */
	public List<PedidoDto> sorterListPedidos(List<Pedido> pedidos) throws DataAccessException, Exception {
		
    	List<PedidoDto> misPedidosDto = new ArrayList<PedidoDto>();
    	EstadoPedido estado;
    	EstadoDePedido ultimoEstado;
    	Usuario usuario;
    	PedidoDto dto;
    	for(Pedido p: pedidos) {
    		ultimoEstado = this.daoEstado.findEstadoDePedidoById(p.getIdUltimoEstado());
    		estado = this.daoEstado.findById(ultimoEstado.getEstadoPedido().getIdEstadoPedido());
    		dto = ClassToDto.pedidoToDto(p);
    		dto.setUltimoEstado(estado);
    		dto.setUltimoEstadoDePedido(ultimoEstado);
    		usuario = this.daoUsuario.findById(p.getUsuario().getIdUsuario());
    		dto.setUsuario(usuario);
    		misPedidosDto.add(dto);
    	}
    	
		Collections.sort(misPedidosDto);
		
		/*
		 * Seteo el nro de pedido de arriba a abajo de la lista, desde el mayor al menor nro.
		 */
		Integer nroPedido = misPedidosDto.size();
		for(PedidoDto p: misPedidosDto) {
			p.setNroPedido(nroPedido);
			nroPedido--;
		}
		
		return misPedidosDto;
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
	public Boolean validaCarritoVacio() throws DataAccessException, Exception { 	
    	if(this.shoppingCart.getItemsDePedido().size() > 0 ) {
    		return true;
    	} else {
    		return false;
    	}
	}
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = DataAccessException.class)
    public void updatePedido(Pedido pedido) throws DataAccessException, Exception {
    	this.daoPedido.update(pedido);
    }
}
