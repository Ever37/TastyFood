package com.proyectofinal.app.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.proyectofinal.app.core.security.CustomUserDetails;
import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Sabor;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.service.ComercioService;
import com.proyectofinal.app.service.SaborService;
import com.proyectofinal.app.service.UsuarioService;
import com.proyectofinal.app.util.ManagerString;

@Controller
public class SaborController {

	@Autowired
	ComercioService serviceComercio;
	
	@Autowired
	SaborService serviceSabor;
	
	@Autowired
	UsuarioService serviceUsuario;
	
    @Autowired
    Carrito shoppingCart;
	
    /**
     * Muestra los datos de un sabor.
     *
     */
    @RequestMapping(value = {"/sabores-{id}-view"}, method = RequestMethod.GET)
    public ModelAndView viewSabor(
	    @PathVariable("id") Long id,
	    ModelAndView model, HttpSession session) throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	Sabor sabor = this.serviceSabor.findById(id);
    	model.addObject("sabor", sabor);
    	
    	model.setViewName("ver-sabor");
    	return model;
    }
	
    /**
     * Agrega un nuevo sabor.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/sabores-new"}, method = RequestMethod.GET)
    public ModelAndView newSabor(ModelAndView model, String error, HttpSession session) throws DataAccessException, Exception {
    	   
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	/*
    	 * Recuperar desde el usuario logueado, su correspondiente comercio y setearlo al sabor.
    	 */
       	CustomUserDetails auth = 
       			(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();      	
       	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario()); 
    
       	Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
       	
    	Sabor sabor = new Sabor(comercio);
    	sabor.setActivo(true);
    	model.addObject("sabor", sabor);
    	model.addObject("error", error);
    	model.setViewName("re-sabor");

    	return model;
    }
    
    /**
     * Se utiliza en el submit del formulario de alta de sabor.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/sabores-new"}, method = RequestMethod.POST)
    public ModelAndView saveSabor(Sabor sabor, BindingResult result, 
    		ModelAndView model, HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	if (!result.hasErrors()) {
    			if(this.serviceSabor.validaSaveSabor(sabor)) {   			    
        			model.addObject("success", "Correcto: El sabor '" + ManagerString.cleanString(sabor.getDescripcion()) + "' ha sido creado." );
                    model.setViewName("redirect:" + "/sabores-listado");
    			} else {
    				model.addObject("error", "Error: El nombre del sabor '" + ManagerString.cleanString(sabor.getDescripcion()) + "' ya se encuentra en uso." );
    				model.setViewName("redirect:" + "/sabores-new");
    			}     
    	}
    	return model;	
    }
    
    /**
     * Lista todos los sabores.
     *
     */
    @RequestMapping(value = {"/sabores-listado"}, method = RequestMethod.GET)
    public ModelAndView listSabores(ModelAndView model, String success, String error, HttpSession session)
	    throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
       	CustomUserDetails auth = 
       			(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();      	
       	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario()); 
    
       	Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
       	
       	List<Sabor> sabores = new ArrayList<Sabor>();
       	if(comercio != null) {
       		sabores = this.serviceSabor.findAllByComercio(comercio.getIdComercio());
       	}    	 
    	model.addObject("sabores", sabores); 	
    	
    	model.addObject("success", success);
    	model.addObject("error", error);
    	model.setViewName("lista-de-sabores");
    	return model;
    }  
    
    /**
     * Agrega fechaBaja a los sabores que se den de baja.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/sabores-{id}-delete"}, method = RequestMethod.GET)
    public ModelAndView deleteSabores(@PathVariable("id") Long id, HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	ModelAndView modelMap = new ModelAndView("redirect:" + "/sabores-listado");
    	Sabor sabor = this.serviceSabor.findById(id);
    	if(this.serviceSabor.deleteSabor(sabor.getIdSabor())) {
        	modelMap.addObject("success", "Correcto: El sabor '" + ManagerString.cleanString(sabor.getDescripcion()) + "' ha sido eliminado.");
    	} else {       	
        	modelMap.addObject("error", "Error de sistema. Contactese con TastyFood");
    	}
    	return modelMap;  	
    }
    
    /**
     * Carga en la vista el sabor a actualizar.
     */
    @RequestMapping(value = {"/sabores-{id}-update"}, method = RequestMethod.GET)
    public ModelAndView editSabor(@PathVariable Long id, ModelAndView model, String success, HttpSession session)
	    throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	Sabor sabor = this.serviceSabor.findById(id);
    	model.addObject("sabor", sabor); 
    	   	
    	model.addObject("success", success);  	
    	model.setViewName("editar-sabor");
    	return model;
    }
    
    /**
     * Actualizar sabor mediante el submit del formulario de modificacion de
     * sabor.
     */
    @RequestMapping(value = {"/sabores-{id}-update"}, method = RequestMethod.POST)
    public ModelAndView updateSabor(@PathVariable("id") Long id, Sabor sabor, BindingResult result,
    	   ModelAndView model, SessionStatus status, HttpSession session) 
    	   throws DataAccessException, Exception {
  	   	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	if(this.serviceSabor.updateSabor(sabor)) {
        	model = new ModelAndView("redirect:" + "/sabores-listado");
        	model.addObject("success", "Correcto: El sabor '" + ManagerString.cleanString(sabor.getDescripcion()) + "' ha sido actualizado.");   		
    	} else {
        	model = new ModelAndView("redirect:" + "/sabores-listado");
        	model.addObject("error", "Error: Ya existe otro sabor con nombre '" + ManagerString.cleanString(sabor.getDescripcion()) + "'.");
    	}  	
    	return model;
    }
}
