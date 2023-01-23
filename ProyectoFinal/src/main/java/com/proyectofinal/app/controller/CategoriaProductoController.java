package com.proyectofinal.app.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.CategoriaProducto;
import com.proyectofinal.app.model.Producto;
import com.proyectofinal.app.service.CategoriaProductoService;
import com.proyectofinal.app.service.ProductoService;
import com.proyectofinal.app.util.ManagerString;

@Controller
public class CategoriaProductoController {

	@Autowired
	CategoriaProductoService serviceCategoria;
	
	@Autowired
	ProductoService serviceProducto;
	
    @Autowired
    Carrito shoppingCart;
	
    /**
     * Muestra los datos de una categoria.
     *
     */
    @RequestMapping(value = {"/categorias-{id}-view"}, method = RequestMethod.GET)
    public ModelAndView viewCategoria(
	    @PathVariable("id") Long id,
	    ModelAndView model, HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	CategoriaProducto categoria = this.serviceCategoria.findById(id);
    	model.addObject("categoria", categoria);
    	
    	List<Producto> productos = this.serviceProducto.findByCategoria(categoria.getIdCategoria());
    	model.addObject("productos", productos);

    	model.setViewName("ver-categoria");
    	return model;
    }
	
    /**
     * Agrega una nueva categoria.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/categorias-new"}, method = RequestMethod.GET)
    public ModelAndView newCategoria(ModelAndView model,
    		HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	CategoriaProducto categoria = new CategoriaProducto();
    	model.addObject("categoria", categoria); 	
    	
    	model.setViewName("re-categoria");
    	return model;
    }
    
    /**
     * Se utiliza en el submit del formulario de alta de categoria.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/categorias-new"}, method = RequestMethod.POST)
    public ModelAndView saveCategoria(CategoriaProducto categoria, BindingResult result, 
    		ModelAndView model, HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	if (!result.hasErrors()) {
    			if(this.serviceCategoria.validaSaveCategoria(categoria)) {
    			    this.serviceCategoria.saveCategoria(categoria);
        			model.addObject("success", "Correcto: La categor&iacutea '" + ManagerString.cleanString(categoria.getDescripcion()) + "' ha sido creada." );
                    model.setViewName("redirect:" + "/categorias-listado");
    			} else {
    				model.addObject("error", "Error: La descripci&oacuten de la categor&iacutea '" + ManagerString.cleanString(categoria.getDescripcion()) + "' ya se encuentra en uso." );
    				model.setViewName("redirect:" + "/categorias-new");
    			}     
    	}
    	return model;	
    }
    
    /**
     * Lista todas las categorias.
     *
     */
    @RequestMapping(value = {"/categorias-listado"}, method = RequestMethod.GET)
    public ModelAndView listCategorias(ModelAndView model, String success, String error,
    		HttpSession session)
	    throws DataAccessException, Exception {
 	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	List<CategoriaProducto> categorias = new ArrayList<CategoriaProducto>();
    	categorias = this.serviceCategoria.findAllWithoutDate();
    	model.addObject("categorias", categorias);
    	model.addObject("success", success);
    	model.addObject("error", error);
    	model.setViewName("lista-de-categorias");
    	return model;
    }
    
    /**
     * Agrega fechaBaja a las categorias que se den de baja.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/categorias-{id}-delete"}, method = RequestMethod.GET)
    public ModelAndView deleteCategoria(@PathVariable("id") Long id,
    		HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	ModelAndView modelMap = new ModelAndView("redirect:" + "/categorias-listado");
    	CategoriaProducto categoria = this.serviceCategoria.findById(id);
    	if(this.serviceCategoria.deleteCategoria(id)) {
        	modelMap.addObject("success", "Correcto: La categor&iacutea '" + ManagerString.cleanString(categoria.getDescripcion()) + "' ha sido eliminada.");   		
    	} else {
        	modelMap.addObject("error", "Error: La categor&iacutea '" + ManagerString.cleanString(categoria.getDescripcion()) + "' posee productos asignados.");   		
    	}	
    	return modelMap;
    }
    
    /**
     * Carga en la vista la categoria a actualizar.
     */
    @RequestMapping(value = {"/categorias-{id}-update"}, method = RequestMethod.GET)
    public ModelAndView editCategoria(@PathVariable Long id, ModelAndView model, String success,
    		HttpSession session)
	    throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	CategoriaProducto categoria = this.serviceCategoria.findById(id);
    	model.addObject("categoria", categoria);   	
    	model.addObject("success", success);  	
    	model.setViewName("editar-categoria");
    	return model;
    }
    
    /**
     * Actualizar categoria mediante el submit del formulario de modificacion de
     * categoria.
     */
    @RequestMapping(value = {"/categorias-{id}-update"}, method = RequestMethod.POST)
    public ModelAndView updateCategoria(@PathVariable Long id, CategoriaProducto categoria,
	    BindingResult result, ModelAndView model, SessionStatus status,
	    HttpSession session) throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	categoria.setIdCategoria(id);   	
    	if(this.serviceCategoria.updateCategoria(categoria)) {
        	model = new ModelAndView("redirect:" + "/categorias-listado");
        	model.addObject("success", "Correcto: La categor&iacutea '" + ManagerString.cleanString(categoria.getDescripcion()) + "' ha sido actualizado.");   		
    	} else {
        	model = new ModelAndView("redirect:" + "/categorias-listado");
        	model.addObject("error", "Error: Ya existe otra categor&iacutea con nombre '" + ManagerString.cleanString(categoria.getDescripcion()) + "'.");
    	}  	
    	return model;
    }
    
}
