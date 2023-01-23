package com.proyectofinal.app.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.service.CategoriaProductoService;
import com.proyectofinal.app.service.ComercioService;
import com.proyectofinal.app.service.PermisoService;
import com.proyectofinal.app.service.ProductoService;
import com.proyectofinal.app.service.UsuarioService;

@Controller
public class ComentariosController {

    @Autowired
    UsuarioService serviceUsuario;
    
    @Autowired
    PermisoService servicPermiso;
    
    @Autowired
    ComercioService serviceComercio;
    
    @Autowired
    ProductoService serviceProducto;
    
    @Autowired
    CategoriaProductoService serviceCategoria;
    
    @Autowired
    Carrito shoppingCart;
    
    /**
     * Lista todos los comentarios de un comercio.
     *
     */
    @RequestMapping(value = {"/comentarios-{id}-listado"}, method = RequestMethod.GET)
    public ModelAndView listComercios(@PathVariable("id") Long idComercio,
    		ModelAndView model, String success, String error,
    		HttpSession session)
	    throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	List<Comercio> comercios = this.serviceComercio.findAllWithoutDate();
    	model.addObject("comercios", comercios);
    	
		Comercio comercio = this.serviceComercio.findById(idComercio);
		model.addObject("comercio", comercio);
    	
    	model.addObject("success", success);
    	model.addObject("error", error);
    	return model;
    }  
}
