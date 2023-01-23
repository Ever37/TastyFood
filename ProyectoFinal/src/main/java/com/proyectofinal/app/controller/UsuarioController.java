package com.proyectofinal.app.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.proyectofinal.app.core.security.CustomUserDetails;
import com.proyectofinal.app.dto.UsuarioDto;
import com.proyectofinal.app.exceptions.AccessDeniedException;
import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Grupo;
import com.proyectofinal.app.model.Producto;
import com.proyectofinal.app.util.ManagerString;
import com.proyectofinal.app.util.SingletonConstants;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.service.CategoriaProductoService;
import com.proyectofinal.app.service.ComercioService;
import com.proyectofinal.app.service.PermisoService;
import com.proyectofinal.app.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioService serviceUsuario;

	@Autowired
	PermisoService servicPermiso;

	@Autowired
	ComercioService serviceComercio;

	@Autowired
	CategoriaProductoService serviceCategoriaProducto;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	Carrito shoppingCart;

	/*
	 * La variable Profile la uso para distinguir si el usuario está editando su
	 * propio perfil, o si un administrador está editando algún usuario desde el
	 * listado.
	 */

	@SuppressWarnings("finally")
	/**
	 * Muestra los datos de un usuario.
	 *
	 */
	@RequestMapping(value = { "/usuario-profile" }, method = RequestMethod.GET)
	public ModelAndView profileUsuario(String success, String error, ModelAndView model,
			HttpSession session)
			throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		try {
			Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());

			Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
			model.addObject("comercio", comercio);
			
			model.addObject("success", success);
			model.addObject("error", error);
			model.addObject("profile", "true");
			model.addObject("usuario", usuario);
			model.setViewName("ver-usuario");
			
		} catch (AccessDeniedException e) {
			throw new AccessDeniedException();
		} finally {
			return model;
		}
	}

	/**
	 * Carga en la vista el usuario a actualizar.
	 */
	@RequestMapping(value = { "/usuario-profile-update" }, method = RequestMethod.GET)
	public ModelAndView editProfileUsuario(ModelAndView model, String success,
			HttpSession session) throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());
		model.addObject("usuario", usuario);

		Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
		model.addObject("comercio", comercio);

		model.addObject("id", usuario.getIdUsuario());
		model.addObject("profile", "true");
		model.addObject("success", success);
		model.addObject("grupo", usuario.getGrupo().getDescripcion());
		model.addObject("vendedor", SingletonConstants.GRUPO_DEFAULT_VENDEDOR);
		model.setViewName("editar-usuario");
		return model;
	}

	/**
	 * Actualizar usuario mediante el submit del formulario de modificacion de
	 * usuario.
	 */
	@RequestMapping(value = { "/usuario-profile-update" }, method = RequestMethod.POST)
	public ModelAndView updateProfileUsuario(UsuarioDto usuario, BindingResult result, ModelAndView model,
			SessionStatus status, HttpSession session) throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		if (this.serviceUsuario.updateUsuario(usuario)) {
			model = new ModelAndView("redirect:" + "/usuario-profile");
			model.addObject("success",
					"Correcto: El usuario '" + ManagerString.cleanString(usuario.getNombreUsuario()) + "' ha sido actualizado.");
		} else {
			model = new ModelAndView("redirect:" + "/usuario-profile-update");
			model.addObject("error", "Error: Ya existe otro usuario con nombre '" + ManagerString.cleanString(usuario.getNombreUsuario()) + "'.");
		}

		model.addObject("profile", "true");
		return model;
	}
	
	/**
	 * Agrega un nuevo usuario cliente.
	 *
	 * @throws Exception
	 * @throws DataAccessException
	 */
	@RequestMapping(value = { "/registrate" }, method = RequestMethod.GET)
	public ModelAndView newCliente(ModelAndView model, String error,
			HttpSession session) throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		Usuario usuario = new Usuario("S");
		model.addObject("usuario", usuario);
		model.addObject("error", error);
		model.setViewName("registrate");
		return model;
	}
	
	/**
	 * Agrega un nuevo usuario.
	 *
	 * @throws Exception
	 * @throws DataAccessException
	 */
	@RequestMapping(value = { "/usuarios-new" }, method = RequestMethod.GET)
	public ModelAndView newUsuario(ModelAndView model, String error,
			HttpSession session) throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		Usuario usuario = new Usuario("S");
		model.addObject("usuario", usuario);

		List<Grupo> grupos = this.servicPermiso
				.findAllGrupos(); 
		model.addObject("grupos", grupos);

		model.addObject("error", error);
		model.setViewName("re-usuario");

		return model;
	}

	/**
	 * Se utiliza en el submit del formulario de alta de usuario.
	 *
	 * @throws Exception
	 * @throws DataAccessException
	 */
	@RequestMapping(value = { "/usuarios-new" }, method = RequestMethod.POST)
	public ModelAndView saveUsuario(@ModelAttribute("usuario") Usuario usuario, SessionStatus status,
			BindingResult result, HttpSession session, ModelAndView model, HttpServletRequest request)
			throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		boolean flagAutoregistracion = false;
		
		//Autoregistración
		if(null == usuario.getGrupo()) {
			flagAutoregistracion = true;			
		} 
		
		if (!result.hasErrors()) {
				if (this.serviceUsuario.validaSaveUsuario(usuario)) {
					String pass = usuario.getContrasena();
					String success = "";
					
					if(flagAutoregistracion) {
						success = "Gracias por registrarte " + ManagerString.cleanString(usuario.getNombreUsuario()) + "!";
					} else {
						success = "Correcto: El usuario '" + ManagerString.cleanString(usuario.getNombreUsuario()) + "' ha sido creado.";
					}					
					
					this.serviceUsuario.saveUsuario(usuario);
					model.addObject("success",success);

					if (flagAutoregistracion && usuario.getGrupo().getDescripcion().equals(SingletonConstants.GRUPO_DEFAULT_CLIENTE)) {
						this.authenticateUserAndSetSession(usuario, request, pass);
						session.setAttribute("carrito", new ArrayList<Producto>());
						model.setViewName("redirect:" + "/");
					} else {
						model.setViewName("redirect:" + "/usuarios-listado");
					}
				} else {
					if (flagAutoregistracion) {
						model.addObject("error",
								"Error: El nombre de usuario '" + ManagerString.cleanString(usuario.getNombreUsuario()) + "' ya se encuentra en uso.");
						model.setViewName("redirect:" + "/registrate");	
					} else {
						model.addObject("error",
								"Error: El nombre de usuario '" + ManagerString.cleanString(usuario.getNombreUsuario()) + "' ya se encuentra en uso.");
						model.setViewName("redirect:" + "/usuarios-new");	
					}			
				}
		}
		return model;
	}

	/*
	 * Login after registration
	 */
	private void authenticateUserAndSetSession(Usuario user, HttpServletRequest request, String pass) {
			
		String username = user.getNombreUsuario();
		/*
		 * Como en el user, la contraseña está encriptada, la traigo por otro
		 * lado.
		 */
		String password = pass;
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		// generate session if one doesn't exist
		request.getSession();
		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}

	/**
	 * Lista todos los usuarios.
	 *
	 */
	@RequestMapping(value = { "/usuarios-listado" }, method = RequestMethod.GET)
	public ModelAndView listUsuarios(ModelAndView model, String success, String error,
			HttpSession session)
			throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		List<UsuarioDto> usuarios = new ArrayList<UsuarioDto>();
		usuarios = this.serviceUsuario.findAllUsuariosWithoutDate();
		model.addObject("usuarios", usuarios);
		model.addObject("success", success);
		model.addObject("error", error);
		model.setViewName("lista-de-usuarios");
		return model;
	}

	/**
	 * Muestra los datos de un usuario.
	 *
	 */
	@RequestMapping(value = { "/usuarios-{nombreUsuario}-view" }, method = RequestMethod.GET)
	public ModelAndView viewUsuario(@PathVariable("nombreUsuario") String nombreUsuario, 
			ModelAndView model, HttpSession session)
			throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
		UsuarioDto usuario = null;
		usuario = this.serviceUsuario.findDtoByUsername(nombreUsuario);

		model.addObject("profile", "false");
		model.addObject("usuario", usuario);
		model.setViewName("ver-usuario");
		return model;
	}

	/**
	 * Carga en la vista el usuario a actualizar.
	 */
	@RequestMapping(value = { "/usuarios-{id}-update" }, method = RequestMethod.GET)
	public ModelAndView editUsuario(@PathVariable Long id, ModelAndView model, String success,
			HttpSession session)
			throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		UsuarioDto usuario = this.serviceUsuario.findById(id);
		model.addObject("usuario", usuario);

		Comercio comercio = this.serviceComercio.findByUsuario(usuario.getIdUsuario());
		model.addObject("comercio", comercio);

		model.addObject("profile", "false");
		model.addObject("success", success);
		model.addObject("grupo", usuario.getGrupo().getDescripcion());
		model.addObject("vendedor", SingletonConstants.GRUPO_DEFAULT_VENDEDOR);
		model.setViewName("editar-usuario");
		return model;
	}

	/**
	 * Actualizar usuario mediante el submit del formulario de modificacion de
	 * usuario.
	 */
	@RequestMapping(value = { "/usuarios-{id}-update" }, method = RequestMethod.POST)
	public ModelAndView updateUsuario(@PathVariable Long id, UsuarioDto usuario, BindingResult result,
			ModelAndView model, SessionStatus status, HttpSession session) throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		if (this.serviceUsuario.updateUsuario(usuario)) {
			model = new ModelAndView("redirect:" + "/usuarios-listado");
			model.addObject("success",
					"Correcto: El usuario '" + ManagerString.cleanString(usuario.getNombreUsuario()) + "' ha sido actualizado.");
		} else {
			model = new ModelAndView("redirect:" + "/usuarios-listado");
			model.addObject("error", "Error: Ya existe otro usuario con nombre '" + ManagerString.cleanString(usuario.getNombreUsuario()) + "'.");
		}

		model.addObject("profile", "false");
		return model;
	}

	/**
	 * Agrega fechaBaja a los usuarios que se den de baja.
	 *
	 * @throws Exception
	 * @throws DataAccessException
	 */
	@RequestMapping(value = { "/usuarios-{id}-delete" }, method = RequestMethod.GET)
	public ModelAndView deleteUsuario(@PathVariable("id") Long id,
			HttpSession session) throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		ModelAndView modelMap = new ModelAndView("redirect:" + "/usuarios-listado");
		UsuarioDto usuario = this.serviceUsuario.findById(id);
		if (this.serviceUsuario.deleteUsuario(id)) {
			modelMap.addObject("success",
					"Correcto: El usuario '" + ManagerString.cleanString(usuario.getNombreUsuario()) + "' ha sido eliminado.");
		} else {
			modelMap.addObject("error",
					"Error: El usuario '" + ManagerString.cleanString(usuario.getNombreUsuario()) + "' posee un comercio asignado.");
		}
		return modelMap;
	}

	/*
	 * Carga en la vista para cambiar la contraseña
	 */
	@RequestMapping(value = { "/usuarios-{id}-password" }, method = RequestMethod.GET)
	public ModelAndView changePassword(@PathVariable Long id, ModelAndView model, 
			String error, String success, HttpSession session)
			throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		UsuarioDto usuario = this.serviceUsuario.findById(id);
		usuario.setContrasena("");
		model.addObject("usuario", usuario);

		model.addObject("success", success);
		model.addObject("error", error);
		model.setViewName("editar-password");
		return model;
	}

	/**
	 * Cambia la contraseña
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = { "/usuarios-{id}-password" }, method = RequestMethod.POST)
	public ModelAndView saveNewPassword(@PathVariable Long id, UsuarioDto usuario, BindingResult result,
			ModelAndView model, HttpSession session) throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		/*
		 * 1 - Validar contraseña actual. 2 - Encriptar contraseña nueva y
		 * actualizar.
		 */
		CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Usuario usu = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());

		try {
			if (this.serviceUsuario.validarClaveActual(usuario, usu)) {
				this.serviceUsuario.updateClave(usuario);
				model.addObject("success", "Correcto: La contrase&ntilde;a ha sido actualizada.");
				model.setViewName("redirect:/usuarios-" + usuario.getIdUsuario() + "-update");
			} else {
				model.addObject("error", "Error: La contrase&ntilde;a actual es incorrecta.");
				model.setViewName("redirect:/usuarios-" + usuario.getIdUsuario() + "-password");
			}
			model.addObject("usuario", usuario);
		} catch (AccessDeniedException e) {
			model.setViewName("redirect:/");
		} finally {
			return model;
		}
	}

}
