package com.proyectofinal.app.controller;

import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.service.UsuarioService;
import com.proyectofinal.app.util.ManagerString;

@Controller
@SessionAttributes("usuario")
public class LoginController {

    @Autowired
    UsuarioService service;
    
    @Autowired
    Carrito shoppingCart;

    private static final Logger logger = Logger
	    .getLogger(LoginController.class);

    /**
     * Esto es lo que ve Spring Security
     *
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(
	    @RequestParam(value = "error", required = false) String error,
	    ModelAndView model, HttpSession session, Principal principal) {

		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
        Usuario usuario = new Usuario("S");
        model.addObject("usuario", usuario);
        
    	if (error != null) {
    		
    		if(error.equals("")) {
        		model.addObject("error", ManagerString.cleanString("El par usuario/contraseña no coinciden"));
    		} else {
        		model.addObject("error", error);
    		}    		
    		logger.warn("Intento de Autenticacion Fallido");
    	}	
    	model.setViewName("login");
		return model;
    }

    @RequestMapping(value = {"/home/out"}, method = RequestMethod.GET)
    public ModelAndView logout(

	    @RequestParam(value = "logout", required = false) String logout,
	    ModelAndView model, HttpSession session, Principal principal) {

		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	model.addObject("logout", "Ha cerrado sesion exitosamente.");
    	SecurityContextHolder.clearContext();
    	session.invalidate();
    	principal = null;

    	model.setViewName("redirect:/home/login");
    	return model;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied(Principal user, HttpSession session) {

		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	ModelAndView model = new ModelAndView();
    	model.addObject("error", "Acceso Denegado");
    	model.setViewName("messages/acceso-denegado");
    	return model;

    }


}
