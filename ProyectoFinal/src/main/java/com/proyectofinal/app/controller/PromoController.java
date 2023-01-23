package com.proyectofinal.app.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Producto;
import com.proyectofinal.app.model.Promo;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.service.CategoriaProductoService;
import com.proyectofinal.app.service.ComercioService;
import com.proyectofinal.app.service.ProductoService;
import com.proyectofinal.app.service.PromoService;
import com.proyectofinal.app.service.UsuarioService;

@Controller
public class PromoController {

	@Autowired
	ProductoService serviceProducto;
	
	@Autowired
	CategoriaProductoService serviceCategoria;
	
	@Autowired
	ComercioService serviceComercio;
	
	@Autowired
	UsuarioService serviceUsuario;
	
	@Autowired
	PromoService servicePromo;
	
    @Autowired
    Carrito shoppingCart;
    
    /**
     * Lista todos los productos de un comercio para que promocione alguno.
     *
     */
    @RequestMapping(value = {"/promocionar-producto"}, method = RequestMethod.GET)
    public ModelAndView listProductosPromocionables(ModelAndView model, String success, String error,
    		HttpSession session)
	    throws DataAccessException, Exception {

        session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
       	CustomUserDetails auth = 
       			(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();      	
       	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario()); 
    
       	Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
       	Promo promo = new Promo();
    	if(comercio == null) {
    		throw new AccessDeniedException(null);
    	} else {
       	
    		if(this.servicePromo.findPromoToday() != null) {
    			error = "Ya existe un producto en promoción. Intente nuevamente mañana.";
    		} else {

    		}	
	    List<Producto> productos = new ArrayList<Producto>();
	    if(comercio != null) {
	       	productos = this.serviceProducto.findByComercio(comercio.getIdComercio());      		
	    }
	    model.addObject("productos", productos);
    	/*
    	List<Producto> helados = this.serviceProducto
    			.findProductoByNameCategoriaAndComercio("Helados", comercio.getIdComercio());    	
    	model.addObject("hayHelado", helados.size());    	
    	*/
    	
    	model.addObject("promo", promo);    	
    	model.addObject("success", success);
    	model.addObject("error", error);
    	model.setViewName("promo");
    	return model;
    	}
    	
    }  
    
    @RequestMapping(value = {"/promocionar-{id}-producto"}, method = RequestMethod.POST)
    public ModelAndView promocionarProducto(@PathVariable("id") Long idProducto,
    		ModelAndView model, String success, String error,
    		HttpSession session)
	    throws DataAccessException, Exception {

        session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());

       	CustomUserDetails auth = 
       			(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();      	
       	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario()); 
    
       	Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
       	
    	if(comercio == null) {
    		throw new AccessDeniedException(null);
    	} else {
       	
       	List<Producto> productos = new ArrayList<Producto>();
       	if(comercio != null) {
        	productos = this.serviceProducto.findByComercio(comercio.getIdComercio());      		
       	}
    	model.addObject("productos", productos);
    	   	
    	Promo promo = new Promo();
    	Producto producto = this.serviceProducto.findById(idProducto);
    	promo.setProducto(producto);
    	Promo otraPromo = this.servicePromo.findPromoToday();
    	if(otraPromo == null){
    		this.servicePromo.savePromo(promo);
    		success = "Correcto: El producto " + producto.getDescripcion() + " se promocionará hasta finalizar el día.";
    	} else {
    		error = "Ya existe un producto en promoción. Intente nuevamente mañana.";
    	}
    	    	
    	model.addObject("promo", promo);	  	
    	model.addObject("success", success);
    	model.addObject("error", error);
    	model.setViewName("promo");
    	return model;
    	}
    	
    }  
    
}
