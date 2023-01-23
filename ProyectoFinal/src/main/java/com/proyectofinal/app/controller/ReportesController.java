package com.proyectofinal.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.proyectofinal.app.core.security.CustomUserDetails;
import com.proyectofinal.app.dto.BusquedaDto;
import com.proyectofinal.app.dto.CuentaDto;
import com.proyectofinal.app.dto.PedidoDto;
import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Pedido;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.service.ComercioService;
import com.proyectofinal.app.service.CuentaService;
import com.proyectofinal.app.service.EstadoService;
import com.proyectofinal.app.service.PedidoService;
import com.proyectofinal.app.service.UsuarioService;

@Controller
public class ReportesController {

    @Autowired
    UsuarioService serviceUsuario;
    
    @Autowired
    ComercioService serviceComercio;
    
    @Autowired
    PedidoService servicePedido;
    
    @Autowired
    EstadoService serviceEstado;
    
    @Autowired
    CuentaService serviceCuentas;
    
    @Autowired
    Carrito shoppingCart;
    
    /*
     * Pedidos realizados por un usuario
     */
    @RequestMapping(value = {"/reporte-mis-pedidos"}, method = RequestMethod.GET)
    public ModelAndView reporteMisPedidos(ModelAndView model, String success,
    		HttpSession session) 
    		throws DataAccessException, Exception {
	    
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	/*
    	 * Validar si está logueado.
    	 */
    	CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();    	
    	
    	List<Pedido> misPedidos = this.servicePedido.findPedidosByUsuario(auth.getIdUsuario());	
    	
    	List<PedidoDto> sorterPedidos = this.servicePedido.sorterListPedidos(misPedidos);
    	model.addObject("misPedidos", sorterPedidos);
    	model.addObject("success", success);
    	model.setViewName("reporte-mis-pedidos");
    	return model;
    }
    
    /*
     * Ventas realizadas por un comercio
     */
    @RequestMapping(value = {"/reporte-ventas"}, method = RequestMethod.GET)
    public ModelAndView busquedaVentasComercio(ModelAndView model, HttpSession session) 
    		throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	/*
    	 * Esta búsqueda sola la puede realizar un comercio.
    	 */
    	CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 	
    	
    	
    	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());    	
    	Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
    	
    	if(comercio == null) {
    		throw new AccessDeniedException(null);
    	} else {

        	BusquedaDto busqueda = new BusquedaDto();
        	model.addObject("busqueda", busqueda);
        	
        	Date today = new Date();
        	List<Pedido> misVentas = this.servicePedido
        								.findPedidosByComercioAndDates(comercio.getIdComercio(), 
        															   today,
        															   today);       	       	
        	
        	List<PedidoDto> sorterVentas = this.servicePedido.sorterListPedidos(misVentas);
        	
        	sorterVentas = this.serviceEstado.findByEstadoActual(sorterVentas);
        	
        	model.addObject("misVentas", sorterVentas);
        	model.addObject("fechaDesde", today);
        	model.addObject("fechaHasta", today);
        	
    		Double totalVentas = 0.0;
    		for(Pedido p: misVentas) {
    			totalVentas += p.getTotal();
    		}
    		model.addObject("totalVentas", totalVentas);
    		
        	model.setViewName("reporte-ventas-comercio");
        	return model;
    	}
    	

    }

    @RequestMapping(value = {"/reporte-ventas"}, method = RequestMethod.POST)
    public ModelAndView reporteVentasComercio(ModelAndView model, BusquedaDto busqueda,
    		HttpSession session) 
    		throws DataAccessException, Exception {
	    
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder
    							 .getContext().getAuthentication().getPrincipal(); 	
    	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());    	
    	Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
    	
    	List<Pedido> misVentas = this.servicePedido
    							.findPedidosByComercioAndDates(comercio.getIdComercio(),
    														   busqueda.getFechaDesde(),
    														   busqueda.getFechaHasta()); 
    	
		Double totalVentas = 0.0;
		for(Pedido p: misVentas) {
			totalVentas += p.getTotal();
		}
		model.addObject("totalVentas", totalVentas);
    	   	
    	List<PedidoDto> sorterVentas = this.servicePedido.sorterListPedidos(misVentas);   	
    	sorterVentas = this.serviceEstado.findByEstadoActual(sorterVentas);
    	
    	model.addObject("misVentas", sorterVentas);   
    	model.addObject("fechaDesde", busqueda.getFechaDesde());
    	model.addObject("fechaHasta", busqueda.getFechaHasta());
    	model.addObject("busqueda", busqueda);
    	model.setViewName("reporte-ventas-comercio");
    	return model;
    }
    
    /*
     * Reporte de cuentas - devengamientos de comercios.
     */
    @RequestMapping(value = {"/resumen-de-cuentas"}, method = RequestMethod.GET)
    public ModelAndView busquedaCuentas(ModelAndView model,
    		HttpSession session) throws DataAccessException, Exception {
	    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 	
    	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());    	
    	
    	if(usuario == null) {
    		throw new AccessDeniedException(null);
    	} else {
    		BusquedaDto busqueda = new BusquedaDto();
    		model.addObject("busqueda", busqueda);

    		List<CuentaDto> cuentas = new ArrayList<CuentaDto>();
    		
    		model.addObject("cuentas", cuentas);
    		model.setViewName("reporte-devengamiento");
    		return model;
    	}
    }

    @RequestMapping(value = {"/resumen-de-cuentas"}, method = RequestMethod.POST)
    public ModelAndView reporteCuentas(ModelAndView model, BusquedaDto busqueda,
    		HttpSession session) 
    		throws DataAccessException, Exception {
	    
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 	
    	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());    	
    	
    	if(usuario == null) {
    		throw new AccessDeniedException(null);
    	} else {

    		model.addObject("busqueda", busqueda);
    		List<CuentaDto> cuentas = this.serviceCuentas
    								  .generateCuentas(busqueda.getComision(),
    										  		   busqueda.getFechaDesde(), 
    										  		   busqueda.getFechaHasta());
    		
    		Double totalCuentas = 0.0;
    		for(CuentaDto c: cuentas) {
    			totalCuentas += c.getComision();
    		}
    		
    		model.addObject("comision", busqueda.getComision());
    		model.addObject("totalCuentas", totalCuentas);
    		
    		
    		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy hh:mm aaa", Locale.US);  		
    		String stringDesde = format.format(busqueda.getFechaDesde());
    		String stringHasta = format.format(busqueda.getFechaHasta());
    		
    		/*
    		Date dateDesde = format.parse(stringDesde);
    		Date dateHasta = format.parse(stringHasta);
    		*/

    		
        	model.addObject("fDesde", stringDesde);
        	model.addObject("fHasta", stringHasta);
    		
        	model.addObject("fechaDesde", busqueda.getFechaDesde());
        	model.addObject("fechaHasta", busqueda.getFechaHasta());
        	
    	    model.addObject("cuentas", cuentas);
    	    
    		BusquedaDto dto = new BusquedaDto();
    		model.addObject("busquedaPedidos", dto);
    		model.addObject("busquedaGuardar", dto);
    		model.setViewName("reporte-devengamiento");
    		return model;
    	}
    }
    
    @RequestMapping(value = {"/cuentas-new"}, method = RequestMethod.POST)
    public ModelAndView saveCuentas(ModelAndView model, BusquedaDto busqueda,
    		HttpSession session) 
    		throws DataAccessException, Exception {
	    
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	/**/
    	try {

    	CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 	
    	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());    	
    	
    	if(usuario == null) {
    		throw new AccessDeniedException(null);
    	} else {
    		
    		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy hh:mm aaa", Locale.US); 
    		busqueda.setFechaDesde(format.parse(busqueda.getDesde()));
    		busqueda.setFechaHasta(format.parse(busqueda.getHasta()));	
    		
    		model.addObject("busqueda", busqueda);
    		List<CuentaDto> cuentas = this.serviceCuentas
    								  .generateCuentas(busqueda.getComision(),
    										  		   busqueda.getFechaDesde(), 
    										  		   busqueda.getFechaHasta());
    		 
    		try {
    			this.serviceCuentas.saveAllCuentas(cuentas);
			} catch (Exception e) {}
    		
        	model.addObject("fechaDesde", busqueda.getFechaDesde());
        	model.addObject("fechaHasta", busqueda.getFechaHasta());
        	model.setViewName("redirect:" + "/cuentas-listado");
        	model.addObject("success", "Correcto: Las cuentas han sido creadas.");
    		return model;
    	}
    	
    	} catch (AccessDeniedException a) {
    		throw new AccessDeniedException(null);
    	}   	
    }
    
    /**
     * Lista todos los comercios.
     *
     */
    @RequestMapping(value = {"/cuentas-listado"}, method = RequestMethod.GET)
    public ModelAndView busquedaCuentas(ModelAndView model, String success, String error,
    		HttpSession session)
	    throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
		BusquedaDto busqueda = new BusquedaDto();
		model.addObject("busqueda", busqueda);
    	
    	List<Comercio> comercios = this.serviceComercio.findAllWithoutDate();    	
    	model.addObject("comercios", comercios);
    	model.addObject("success", success);
    	model.addObject("error", error);
    	model.setViewName("lista-de-cuentas");
    	return model;
    }
    
    @RequestMapping(value = {"/cuentas-{id}-listado"}, method = RequestMethod.POST)
    public ModelAndView listCuentas(@PathVariable("id") Long idComercio, 
    		ModelAndView model, String success, String error,
    		HttpSession session)
	    throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	List<Comercio> comercios = this.serviceComercio.findAllWithoutDate();
    	model.addObject("comercios", comercios);
    	
		BusquedaDto busqueda = new BusquedaDto();
		model.addObject("busqueda", busqueda);
    	
    	List<CuentaDto> cuentas = new ArrayList<CuentaDto>();
    	cuentas = this.serviceCuentas.findDtoByComercio(idComercio);
    	model.addObject("cuentas", cuentas);		
    	
    	model.addObject("success", success);
    	model.addObject("error", error);
    	
		BusquedaDto dto = new BusquedaDto();
		model.addObject("busquedaPedidos", dto);
		
		Comercio comercio = this.serviceComercio.findById(idComercio);
		model.addObject("comercio", comercio);
		
    	model.setViewName("lista-de-cuentas");
    	return model;
    }
}
