package com.proyectofinal.app.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.proyectofinal.app.core.security.CustomUserDetails;
import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.CategoriaProducto;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Promo;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.service.CategoriaProductoService;
import com.proyectofinal.app.service.ComercioService;
import com.proyectofinal.app.service.PromoService;
import com.proyectofinal.app.util.SingletonConstants;

@Controller
@RequestMapping(value = "/")
public class MainController {

	
	@Autowired
	ComercioService serviceComercio;
	
	@Autowired
	CategoriaProductoService serviceCategoria;
	
	@Autowired
	PromoService servicePromo;
	
    @Autowired
    Carrito shoppingCart;
	
    /**
     * Muestra la página de inicio
     *
     */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request,
	    ModelAndView model, HttpSession session, String success) throws DataAccessException, Exception {

	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());   	
    Usuario usuario = new Usuario("S");
    List<CategoriaProducto> categorias = this.serviceCategoria.findAllWithoutDate();
    Promo promo = this.servicePromo.findPromoToday();
    
    List<Comercio> comercios = this.serviceComercio.findAllWithoutDate();
    for(Comercio c: comercios) {
    	c.setAbierto(this.serviceComercio.validaFranjaHorariaComercio(c.getIdComercio()));
    	if(promo != null) {
    		if(promo.getProducto().getComercio().getIdComercio().equals(c.getIdComercio())) {
    			c.setTienePromo(true);
    		} else {
    			c.setTienePromo(false);
    		}  
    	}
    }
    
    model.addObject("comercios", comercios);
    model.addObject("promo", promo);
    model.addObject("categorias", categorias);
    model.addObject("usuario", usuario);
    model.addObject("success", success);
	model.setViewName("index");
	return model;
    }
    
    /**
     * Muestra la página de preguntas frecuentes
     *
     */
    @RequestMapping(value = {"/faq"}, method = RequestMethod.GET)
    public ModelAndView preguntasFrecuentes(HttpServletRequest request,
	    ModelAndView model, HttpSession session) throws DataAccessException, Exception {
    	
		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
        Usuario usuario = new Usuario("S");
        model.addObject("usuario", usuario);

        model.addObject("entityName", SingletonConstants.ENTITY_NAME);
    	
    	model.setViewName("faq");
    	return model;
    }
    
    /**
     * Muestra la página de Tabla de precios
     *
     */
    @RequestMapping(value = {"/tabla-precios"}, method = RequestMethod.GET)
    public ModelAndView tablePrices(HttpServletRequest request,
	    ModelAndView model, HttpSession session) throws DataAccessException, Exception {
    	
		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	model.setViewName("tabla-precios");
    	return model;
    }
    
    /**
     * Muestra la página de ¿Quienes somos?
     *
     */
    @RequestMapping(value = {"/nosotros"}, method = RequestMethod.GET)
    public ModelAndView nosotros(HttpServletRequest request,
	    ModelAndView model, HttpSession session) throws DataAccessException, Exception {

		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
        Usuario usuario = new Usuario("S");
        model.addObject("usuario", usuario);
        
        CustomUserDetails usu = this.shoppingCart.getUsuario();
        model.addObject("usu", usu);
        
    	model.setViewName("quienes-somos");
    	return model;
    }
    
    /**
     * Muestra la página en construcción
     *
     */
    @RequestMapping(value = {"/en-construccion"}, method = RequestMethod.GET)
    public ModelAndView underConstruction(HttpServletRequest request,
	    ModelAndView model, HttpSession session) throws DataAccessException, Exception {
    	
		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	model.setViewName("messages/en-construccion");
    	return model;
    }
    
    @RequestMapping(value = {"/ayuda"}, method = RequestMethod.GET)
    public ModelAndView ayudaOnline(HttpServletRequest request,
	    ModelAndView model, HttpSession session) throws DataAccessException, Exception {
    	   	
    	model.setViewName("ayuda-online");
    	return model;
    }
    
    
}
