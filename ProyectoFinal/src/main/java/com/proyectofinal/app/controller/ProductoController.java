package com.proyectofinal.app.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.proyectofinal.app.core.security.CustomUserDetails;
import com.proyectofinal.app.dto.ProductoDto;
import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.CategoriaProducto;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Precio;
import com.proyectofinal.app.model.Producto;
import com.proyectofinal.app.model.Salsa;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.service.CategoriaProductoService;
import com.proyectofinal.app.service.ComercioService;
import com.proyectofinal.app.service.ProductoService;
import com.proyectofinal.app.service.UsuarioService;
import com.proyectofinal.app.util.ClassToDto;
import com.proyectofinal.app.util.ManagerString;

@Controller
public class ProductoController {
	
	@Autowired
	ProductoService serviceProducto;
	
	@Autowired
	CategoriaProductoService serviceCategoria;
	
	@Autowired
	ComercioService serviceComercio;
	
	@Autowired
	UsuarioService serviceUsuario;
	
    @Autowired
    Carrito shoppingCart;

    /**
     * Muestra los datos de un producto.
     *
     */
    @RequestMapping(value = {"/productos-{id}-view"}, method = RequestMethod.GET)
    public ModelAndView viewProducto(
	    @PathVariable("id") Long id,
	    ModelAndView model, HttpSession session) throws DataAccessException, Exception {
    	
        session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	ProductoDto producto = this.serviceProducto.findDtoById(id);
    	model.addObject("producto", producto);
    	
 	   /*
 	    * A MODIFICAR POR HISTORIAL DE PRECIO.
 	    */
    	List<Precio> precios = this.serviceProducto.findPreciosByProducto(producto.getIdProducto());
    	Precio ultimoPrecio = precios.get(precios.size() - 1);
    	model.addObject("ultimoPrecio", ultimoPrecio.getValor());
    	
    	if(producto.getCategoria().getDescripcion().equals("Pastas")) {
    		List<Salsa> salsas = this.serviceProducto.findByProducto(producto.getIdProducto());
    		model.addObject("salsas", salsas);   				
    	}
    	
    	model.setViewName("ver-producto");
    	return model;
    }
	
    /**
     * Agrega un nuevo producto.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/productos-new"}, method = RequestMethod.GET)
    public ModelAndView newProducto(ModelAndView model, String error,
    		HttpSession session) throws DataAccessException, Exception {
    	  
        session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
       	CustomUserDetails auth = 
       			(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();      	
       	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario()); 
       	
    	Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
    	
    	if(comercio == null) {
    		throw new AccessDeniedException(null);
    	} else {
	
    	ProductoDto producto = new ProductoDto(comercio);
    	producto.setActivo(true);
    	model.addObject("producto", producto);
    	
    	List<CategoriaProducto> categorias = this.serviceCategoria.findAllWithoutDate();
    	model.addObject("categorias", categorias);
    	
    	model.addObject("error", error);
    	model.setViewName("re-producto");
    	return model;
    	}

    	
    }
    
    /**
     * Se utiliza en el submit del formulario de alta de producto.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/productos-new"}, method = RequestMethod.POST)
    public ModelAndView saveProducto(ProductoDto producto, BindingResult result, 
    		ModelAndView model, HttpSession session) throws DataAccessException, Exception {
    	
        session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	if (!result.hasErrors()) {
    			if(this.serviceProducto.validaSaveProducto(producto)) {
    		    	Comercio comercio = this.serviceComercio
    		    			.findById(producto.getComercio().getIdComercio());
    		    	producto.setComercio(comercio);
    			    this.serviceProducto.saveProducto(producto);
        			model.addObject("success", "Correcto: El producto '" + ManagerString.cleanString(producto.getDescripcion()) + "' ha sido creado." );
                    model.setViewName("redirect:" + "/productos-listado");
    			} else {
    				model.addObject("error", "Error: La descripci&oacuten del producto '" + ManagerString.cleanString(producto.getDescripcion()) + "' ya se encuentra en uso." );
    				model.setViewName("redirect:" + "/productos-new");
    			}     
    	}
    	return model;	
    }
	
    /**
     * Lista todos los productos.
     *
     */
    @RequestMapping(value = {"/productos-listado"}, method = RequestMethod.GET)
    public ModelAndView listProductos(ModelAndView model, String success, String error,
    		HttpSession session)
	    throws DataAccessException, Exception {

        session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	/*
    	 * Recuperar al usuario, y mostrar sus productos.
    	 * El administrador va a poder ver todos los productos diferenciados por comercio y usuario.
    	 */
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
    	
    	List<Producto> helados = this.serviceProducto
    			.findProductoByNameCategoriaAndComercio("Helados", comercio.getIdComercio());
    	
    	model.addObject("hayHelado", helados.size());    	
    	
    	model.addObject("success", success);
    	model.addObject("error", error);
    	model.setViewName("lista-de-productos");
    	return model;
    	}
    	
    }  
    
    /**
     * Agrega fechaBaja a los productos que se den de baja.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/productos-{id}-delete"}, method = RequestMethod.GET)
    public ModelAndView deleteProducto(@PathVariable("id") Long id,
    		HttpSession session) throws DataAccessException, Exception {
    	
        session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	ModelAndView modelMap = new ModelAndView("redirect:" + "/productos-listado");
    	Producto producto = this.serviceProducto.findById(id);
    	if(this.serviceProducto.deleteProducto(producto.getIdProducto())) {
        	modelMap.addObject("success", "Correcto: El producto '" + ManagerString.cleanString(producto.getDescripcion()) + "' ha sido eliminado.");
    	} else {       	
        	modelMap.addObject("error", "Error de sistema. Contactese con TastyFood");
    	}
    	return modelMap;  	
    }
    
    /**
     * Carga en la vista el producto a actualizar.
     */
    @RequestMapping(value = {"/productos-{id}-update"}, method = RequestMethod.GET)
    public ModelAndView editProducto(@PathVariable Long id, ModelAndView model, String success,
    		HttpSession session)
	    throws DataAccessException, Exception {
    	
        session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	Producto producto = this.serviceProducto.findById(id);
    	model.addObject("producto", ClassToDto.productoToDto(producto)); 
    	
    	CategoriaProducto categoria = this.serviceCategoria.findById(producto.getCategoria().getIdCategoria());
    	model.addObject("categoria", categoria);
    	
 	   /*
 	    * A MODIFICAR POR HISTORIAL DE PRECIO.
 	    */
    	List<Precio> precios = this.serviceProducto.findPreciosByProducto(producto.getIdProducto());
    	Precio ultimoPrecio = precios.get(precios.size() - 1);
    	model.addObject("ultimoPrecio", ultimoPrecio.getValor());
    	
    	if(producto.getCategoria().getDescripcion().equals("Pastas")) {
    		List<Salsa> salsas = this.serviceProducto.findByProducto(producto.getIdProducto());
    		model.addObject("salsas", salsas);   				
    	}  	
    	
       	CustomUserDetails auth = 
       			(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();      	
       	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario()); 
       	
       	Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
       	
    	Boolean horario = this.serviceComercio.validaFranjaHorariaComercio(comercio.getIdComercio());
    	model.addObject("abierto", horario);
    	model.addObject("success", success);  	
    	model.setViewName("editar-producto");
    	return model;
    }
    
    /**
     * Actualizar producto mediante el submit del formulario de modificacion de
     * producto.
     */
    @RequestMapping(value = {"/productos-{id}-update"}, method = RequestMethod.POST)
    public ModelAndView updateProducto(@PathVariable Long id, ProductoDto producto,
	    BindingResult result, ModelAndView model, SessionStatus status,
	    HttpSession session) throws DataAccessException, Exception {
  	   	
        session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	if(this.serviceProducto.updateProducto(producto)) {
        	model = new ModelAndView("redirect:" + "/productos-listado");
        	model.addObject("success", "Correcto: El producto '" + ManagerString.cleanString(producto.getDescripcion()) + "' ha sido actualizado.");   		
    	} else {
        	model = new ModelAndView("redirect:" + "/productos-listado");
        	model.addObject("error", "Error: Ya existe otro producto con nombre '" + ManagerString.cleanString(producto.getDescripcion()) + "'.");
    	}  	
    	return model;
    }
}
