package com.proyectofinal.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.proyectofinal.app.dto.GrupoDto;
import com.proyectofinal.app.dto.PermisoDto;
import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.Grupo;
import com.proyectofinal.app.service.PermisoService;
import com.proyectofinal.app.util.ManagerString;
import com.proyectofinal.app.util.SingletonConstants;

@Controller
public class PermisosController {

	@Autowired
	PermisoService servicePermisos;
	
    @Autowired
    Carrito shoppingCart;
	
    @RequestMapping(value = { "/grupos-new" }, method = RequestMethod.GET)
    public ModelAndView newGrupo(ModelAndView model, HttpSession session)
    		throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	GrupoDto grupo = this.servicePermisos.createGrupo();
    	model.addObject("grupo", grupo);

    	List<PermisoDto> permisos = this.servicePermisos.findAllPermisosDto();
    	model.addObject("permisos", permisos);
    	
    	model.addObject("administrador", SingletonConstants.GRUPO_DEFAULT_ADMIN);
    	model.setViewName("re-grupo");
    	return model;
    }

    @RequestMapping(value = { "/grupos-new" }, method = RequestMethod.POST)
    public ModelAndView saveGrupo(ModelAndView model, GrupoDto grupo,
    		HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	if(!this.servicePermisos.saveGrupo(grupo)) {
    		model.addObject("error", "Error: Ya existe otro grupo con nombre '" + ManagerString.cleanString(grupo.getDescripcion()) + "'.");
    	} else {
    		model.addObject("success", "Correcto: El grupo '" + ManagerString.cleanString(grupo.getDescripcion()) + "' ha sido creado.");   		
    	}
        model.setViewName("redirect:" + "/grupos-listado");
    	return model;
    }
  
    @RequestMapping(value = { "/grupos-listado" }, method = RequestMethod.GET)
    public ModelAndView listGrupos(ModelAndView model, String success, String error,
    		HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	model.addObject("success", success);
    	model.addObject("error", error);
    	model.addObject("grupos", this.servicePermisos.findAllGruposDto());
    	model.setViewName("lista-de-grupos");
    	return model;
    } 
    
    @RequestMapping(value = { "/grupos-{id}-view" }, method = RequestMethod.GET)
    public ModelAndView viewGrupo(@PathVariable Long id, ModelAndView model,
    		HttpSession session) throws DataAccessException, Exception {
    	
      session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());	
    	
      GrupoDto dto = this.servicePermisos.viewGrupo(id);
      model.addObject("grupo", dto);
      List<PermisoDto> permisos = this.servicePermisos.findAllPermisosDto();
      model.addObject("permisos", permisos);

      model.setViewName("ver-grupo");
      return model;
    }
    
    @RequestMapping(value = { "/grupos-{id}-update" }, method = RequestMethod.GET)
    public ModelAndView editGrupo(@PathVariable Long id, ModelAndView model,
    		HttpSession session) throws DataAccessException, Exception {
    	
      session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());	
      
      model.addObject("grupo", this.servicePermisos.viewGrupo(id));
      List<PermisoDto> permisos = this.servicePermisos.findAllPermisosDto();
  	  model.addObject("administrador", SingletonConstants.GRUPO_DEFAULT_ADMIN);
      model.addObject("permisos", permisos);
      model.setViewName("editar-grupo");
      
      return model;
    }
    
    @RequestMapping(value = { "/grupos-{id}-update" }, method = RequestMethod.POST)
    public ModelAndView updategrupo(@PathVariable Long id, GrupoDto grupoDto, 
    		BindingResult result, ModelAndView model, HttpSession session)
	    throws DataAccessException, Exception {

      session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
      if(!this.servicePermisos.updateGrupo(grupoDto)) {    	  
          model.addObject("error", "Error: Ya existe otro grupo con nombre '" + ManagerString.cleanString(grupoDto.getDescripcion()) + "'.");   	  
      } else {
    	  model.addObject("success", "Correcto: El grupo '" + ManagerString.cleanString(grupoDto.getDescripcion()) + "' ha sido actualizado.");
      }
      model.setViewName("redirect:" + "/grupos-listado");
      return model;
    }

    @RequestMapping(value = { "/grupos-{id}-delete" }, method = RequestMethod.GET)
    public String deleteGrupo(@PathVariable("id") Long id, ModelMap model,
    		HttpSession session) throws DataAccessException, Exception {

        session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
        
    	Grupo grupo = this.servicePermisos.findById(id);
    	if(!this.servicePermisos.deleteGrupo(id)) {    		   		
    		model.addAttribute("error", "Error: El grupo '" + ManagerString.cleanString(grupo.getDescripcion()) + "' no puede ser eliminado porque posee usuarios asignados.");
    	} else {
    		model.addAttribute("success", "Correcto: El grupo '" + ManagerString.cleanString(grupo.getDescripcion()) + "' ha sido eliminado.");
    	}
    	return "redirect:/grupos-listado";
    }
     
}
