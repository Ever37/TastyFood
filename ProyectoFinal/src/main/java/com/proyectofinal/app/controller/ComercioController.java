package com.proyectofinal.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.proyectofinal.app.core.security.CustomUserDetails;
import com.proyectofinal.app.dto.ComercioDto;
import com.proyectofinal.app.exceptions.AccessDeniedException;
import com.proyectofinal.app.exceptions.FueraDeHorarioComercioException;
import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.CategoriaProducto;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Horario;
import com.proyectofinal.app.model.Promo;
import com.proyectofinal.app.model.TelefonoComercio;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.service.CategoriaProductoService;
import com.proyectofinal.app.service.ComercioService;
import com.proyectofinal.app.service.PermisoService;
import com.proyectofinal.app.service.ProductoService;
import com.proyectofinal.app.service.PromoService;
import com.proyectofinal.app.service.UsuarioService;
import com.proyectofinal.app.util.ClassToDto;
import com.proyectofinal.app.util.ManagerString;
import com.proyectofinal.app.util.SingletonConstants;

@Controller
public class ComercioController {

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
    PromoService servicePromo;
    
    @Autowired
    Carrito shoppingCart;
    
    /**
     * Muestra los datos de un comercio.
     *
     */
    @SuppressWarnings("finally")
	@RequestMapping(value = {"/comercio-profile"}, method = RequestMethod.GET)
    public ModelAndView profileComercio(String success, String error,
	    ModelAndView model, HttpSession session) throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
       	CustomUserDetails auth = 
       			(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();      	
       	try {
           	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());
           	model.addObject("usuario", usuario);
           	
           	Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
           	model.addObject("comercio", comercio);
           	       	       	
        	List<TelefonoComercio> telefonos = this.serviceComercio.findTelefonosComercio(comercio.getIdComercio());
        	model.addObject("telefonos", telefonos);
        	
        	List<Horario> horarios = this.serviceComercio.findHorariosComercio(comercio.getIdComercio());
        	model.addObject("horarios", horarios);

        	model.addObject("success", success);
        	model.addObject("error", error);
        	model.addObject("profile", "true");
        	
        	model.setViewName("ver-comercio");
       	} catch(AccessDeniedException e) {
       		throw new AccessDeniedException();
       	} finally {
        	return model;       		
       	}
    }
    
    /**
     * Carga en la vista el comercio a actualizar.
     */
    @RequestMapping(value = {"/comercio-profile-update"}, method = RequestMethod.GET)
    public ModelAndView editProfileComercio(ModelAndView model, String success, String error,
    		HttpSession session)
	    throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
       	CustomUserDetails auth = 
       			(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();      	
       	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());  
    	model.addObject("usuario", usuario);

    	Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
    	model.addObject("comercio", ClassToDto.comercioToDto(comercio));
    	
    	List<TelefonoComercio> telefonos = this.serviceComercio.findTelefonosComercio(comercio.getIdComercio());
    	model.addObject("telefonos", telefonos);
    	
    	List<Horario> horarios = this.serviceComercio.findHorariosComercio(comercio.getIdComercio());
    	model.addObject("horarios", horarios);
    	
    	model.addObject("id", usuario.getIdUsuario());
    	model.addObject("profile", "true");
    	model.addObject("success", success);
    	model.addObject("error", error);
    	model.addObject("grupo", usuario.getGrupo().getDescripcion());
    	model.addObject("vendedor", SingletonConstants.GRUPO_DEFAULT_VENDEDOR);
    	model.setViewName("editar-comercio");
    	return model;
    }
    
    /**
     * Actualizar comercio mediante el submit del formulario de modificacion de
     * comercio.
     */
    @RequestMapping(value = {"/comercio-profile-update"}, method = RequestMethod.POST)
    public ModelAndView updateComercio(ComercioDto comercio,
    	    BindingResult result, ModelAndView model, SessionStatus status,
    	    HttpSession session) throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	if(this.serviceComercio.updateComercio(comercio)) {
        	model = new ModelAndView("redirect:" + "/comercio-profile");
        	model.addObject("success", "Correcto: El comercio '" + ManagerString.cleanString(comercio.getDescripcion()) + "' ha sido actualizado.");   		
    	} else {
        	model = new ModelAndView("redirect:" + "/comercios-" + comercio.getIdComercio() + "-update");
        	model.addObject("error", "Error: Ya existe otro comercio con nombre '" + comercio.getDescripcion() + "'.");
    	}
    	
    	model.addObject("profile", "true");
    	return model;
    }
    
    /**
     * Agrega un nuevo comercio.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/comercios-{id}-new"}, method = RequestMethod.GET)
    public ModelAndView newComercio(ModelAndView model, @PathVariable Long id,
    		HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	Usuario usuario = this.serviceUsuario.findUsuarioById(id);
    	ComercioDto comercio = new ComercioDto("S", usuario);
    	model.addObject("comercio", comercio);  	
    	
    	model.setViewName("re-comercio");
    	return model;
    }
    
    /**
     * Se utiliza en el submit del formulario de alta de comercio.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/comercios-new"}, method = RequestMethod.POST)
    public ModelAndView saveComercio(ComercioDto comercio, BindingResult result, 
    		ModelAndView model, HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
       	CustomUserDetails auth = 
       			(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();      	
       	Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());
    	
    	if (!result.hasErrors()) {
    			if(this.serviceComercio.validaSaveComercio(comercio)) {
    			    this.serviceComercio.saveComercio(comercio);
        			model.addObject("success", "Correcto: El comercio '" + ManagerString.cleanString(comercio.getDescripcion()) + "' ha sido creado." );
                    if(usuario.getGrupo().getDescripcion().equals(SingletonConstants.GRUPO_DEFAULT_ADMIN)) {
                    	model.setViewName("redirect:" + "/comercios-listado");
                    } else {
                    	model.setViewName("redirect:" + "/usuario-profile");
                    }       			
    			} else {
    				model.addObject("error", "Error: La descripci&oacuten del comercio '" + ManagerString.cleanString(comercio.getDescripcion()) + "' ya se encuentra en uso." );
    				model.setViewName("redirect:" + "/comercios-" + comercio.getUsuario().getIdUsuario() + "-new");
    			}     
    	}
    	return model;	
    }
    
    /**
     * Lista todos los comercios.
     *
     */
    @RequestMapping(value = {"/comercios-listado"}, method = RequestMethod.GET)
    public ModelAndView listComercios(ModelAndView model, String success, String error,
    		HttpSession session)
	    throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	List<Comercio> comercios = new ArrayList<Comercio>();
    	comercios = this.serviceComercio.findAllWithoutDate();
    	model.addObject("comercios", comercios);
    	model.addObject("success", success);
    	model.addObject("error", error);
    	model.setViewName("lista-de-comercios");
    	return model;
    }  
    
    /**
     * Muestra los datos de un comercio.
     *
     */
    @RequestMapping(value = {"/comercios-{id}-view"}, method = RequestMethod.GET)
    public ModelAndView viewComercio(
	    @PathVariable("id") Long id,
	    ModelAndView model, HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	Comercio comercio = this.serviceComercio.findById(id);
    	model.addObject("comercio", comercio);
    	
    	List<TelefonoComercio> telefonos = this.serviceComercio.findTelefonosComercio(id);
    	model.addObject("telefonos", telefonos);
    	
    	List<Horario> horarios = this.serviceComercio.findHorariosComercio(id);
    	model.addObject("horarios", horarios);

    	model.setViewName("ver-comercio");
    	return model;
    }
    
    /**
     * Agrega fechaBaja a los comercios que se den de baja.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/comercios-{id}-delete"}, method = RequestMethod.GET)
    public ModelAndView deleteComercio(@PathVariable("id") Long id,
    		HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	ModelAndView modelMap = new ModelAndView("redirect:" + "/comercios-listado");
    	Comercio comercio = this.serviceComercio.findById(id);
    	if(this.serviceComercio.deleteComercio(id)) {
        	modelMap.addObject("success", "Correcto: El comercio '" + ManagerString.cleanString(comercio.getDescripcion()) + "' ha sido eliminado.");
    	} else {       	
        	modelMap.addObject("error", "Error de sistema");
    	}
    	return modelMap;  	
    }
    
    /**
     * Carga en la vista el comercio a actualizar.
     */
    @RequestMapping(value = {"/comercios-{id}-update"}, method = RequestMethod.GET)
    public ModelAndView editComercio(@PathVariable Long id, ModelAndView model, String success, String error,
    		HttpSession session)
	    throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	Comercio comercio = this.serviceComercio.findById(id);
    	model.addObject("comercio", ClassToDto.comercioToDto(comercio));
    	
    	List<TelefonoComercio> telefonos = this.serviceComercio.findTelefonosComercio(id);
    	model.addObject("telefonos", telefonos);
    	
    	List<Horario> horarios = this.serviceComercio.findHorariosComercio(id);
    	model.addObject("horarios", horarios);
    	
    	model.addObject("success", success);
    	model.addObject("error", error);
    	model.setViewName("editar-comercio");
    	return model;
    }
    
    /**
     * Actualizar comercio mediante el submit del formulario de modificacion de
     * comercio.
     */
    @RequestMapping(value = {"/comercios-{id}-update"}, method = RequestMethod.POST)
    public ModelAndView updateComercio(@PathVariable Long id, ComercioDto comercio,
	    BindingResult result, ModelAndView model, SessionStatus status,
	    HttpSession session) throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	if(this.serviceComercio.updateComercio(comercio)) {
        	model = new ModelAndView("redirect:" + "/comercios-listado");
        	model.addObject("success", "Correcto: El comercio '" + ManagerString.cleanString(comercio.getDescripcion()) + "' ha sido actualizado.");   		
    	} else {
        	model = new ModelAndView("redirect:" + "/comercios-" + id + "-update");
        	model.addObject("error", "Error: Ya existe otro comercio con nombre '" + ManagerString.cleanString(comercio.getDescripcion()) + "'.");
    	}
    	
    	return model;
    }
    
    /*
     * Muestra el men� del comercio.
     */
    @SuppressWarnings("finally")
	@RequestMapping(value = {"/comercio-{id}-menu"}, method = RequestMethod.GET)
    public ModelAndView menuComercio(@PathVariable Long id, 
    	   ModelAndView model, String success, String error, HttpSession session)
	    throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
		try {

			if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
					&& this.shoppingCart.getUsuario() == null) {
				if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
					CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
							.getPrincipal();
					this.shoppingCart.setUsuario(auth);
				}
			} else {
				Usuario usuario = new Usuario("S");
				model.addObject("usuario", usuario);
			}

			Comercio comercio = this.serviceComercio.findById(id);
			model.addObject("comercio", comercio);

			List<CategoriaProducto> categorias = this.serviceCategoria.findProductoGroupByCategoria(id);
			model.addObject("categorias", categorias);

			List<Horario> horarios = this.serviceComercio.findHorariosComercio(id);
			Collections.sort(horarios);
			model.addObject("horarios", horarios);
				    	
	    	List<TelefonoComercio> telefonos = this.serviceComercio.findTelefonosComercio(id);
	    	model.addObject("telefonos", telefonos);
	    	
		    Promo promo = this.servicePromo.findPromoToday();
		    if(promo != null && comercio.getIdComercio().equals(promo.getProducto().getComercio().getIdComercio())){
		    	comercio.setTienePromo(true);
		    } else {
		    	comercio.setTienePromo(false);
		    }
		    model.addObject("promo", promo);

			if (!this.serviceComercio.validaFranjaHorariaComercio(id)) {
				throw new FueraDeHorarioComercioException(SingletonConstants.MESSAGE_COMERCIO_CERRADO);
			}				    
		   
			model.addObject("success", success);
			model.addObject("error", error);
			model.addObject("alert", "");
			model.addObject("EN_HORARIO", true);

		} catch (FueraDeHorarioComercioException e) {
			model.addObject("alert", SingletonConstants.MESSAGE_COMERCIO_CERRADO);
			model.addObject("EN_HORARIO", false);
		} finally {
			model.setViewName("ver-menu-comercio");
			return model;
		}
    }  
    
	public boolean isAuthenticated(){
	      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	     return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}
}
