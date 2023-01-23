package com.proyectofinal.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.proyectofinal.app.core.security.CustomUserDetails;
import com.proyectofinal.app.dto.BusquedaDto;
import com.proyectofinal.app.dto.PedidoDto;
import com.proyectofinal.app.exceptions.CarritoVacioException;
import com.proyectofinal.app.exceptions.CompraMinimaExcepcion;
import com.proyectofinal.app.exceptions.FueraDeHorarioComercioException;
import com.proyectofinal.app.exceptions.TodosItemsCarritoMismoComercio;
import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.CategoriaProducto;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.EstadoPedido;
import com.proyectofinal.app.model.ItemPedido;
import com.proyectofinal.app.model.Pedido;
import com.proyectofinal.app.model.Precio;
import com.proyectofinal.app.model.Producto;
import com.proyectofinal.app.model.Sabor;
import com.proyectofinal.app.model.SaboresDeItem;
import com.proyectofinal.app.model.Salsa;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.service.CategoriaProductoService;
import com.proyectofinal.app.service.ComercioService;
import com.proyectofinal.app.service.EstadoService;
import com.proyectofinal.app.service.PedidoService;
import com.proyectofinal.app.service.PermisoService;
import com.proyectofinal.app.service.ProductoService;
import com.proyectofinal.app.service.SaborService;
import com.proyectofinal.app.service.SendEmailService;
import com.proyectofinal.app.service.UsuarioService;
import com.proyectofinal.app.util.ClassToDto;
import com.proyectofinal.app.util.ManagerString;
import com.proyectofinal.app.util.SingletonConstants;

@Controller
@SessionAttributes({"direccion", "nro", "piso", "depto", "observaciones"})
public class PedidoController {

	@Autowired
	EstadoService serviceEstado;

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
	SaborService serviceSabor;

	@Autowired
	PedidoService servicePedido;

	@Autowired
	CategoriaProductoService serviceCategoriaProducto;
	
	@Autowired
	SendEmailService serviceSendEmail;

	@Autowired
	Carrito shoppingCart;

	/**
	 *
	 * @throws Exception
	 * @throws DataAccessException
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = { "/producto-{id}-pedido-add" }, method = RequestMethod.GET)
	public ModelAndView addItemPedidoDetails(ModelAndView model, @PathVariable Long id, String success,
			String alert, HttpSession session)
			throws DataAccessException, Exception {

		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		Producto producto = this.serviceProducto.findById(id);
		Comercio comercio = this.serviceComercio.findById(producto.getComercio().getIdComercio());
		try {

			if (!this.serviceComercio.validaFranjaHorariaComercio(comercio.getIdComercio())) {
				throw new FueraDeHorarioComercioException(SingletonConstants.MESSAGE_COMERCIO_CERRADO);
			}

			Usuario usuario = new Usuario("S");
			model.addObject("usuario", usuario);

			ItemPedido itemPedido = new ItemPedido();

			itemPedido.setProducto(producto);
			model.addObject("itemPedido", itemPedido);

			if (producto.getCategoria().getDescripcion().equals("Helados")) {
				List<Sabor> sabores = this.serviceSabor.findAllByComercio(producto.getComercio().getIdComercio());
				model.addObject("sabores", sabores);
			} else if (producto.getCategoria().getDescripcion().equals("Pastas")) {
				List<Salsa> salsas = this.serviceProducto.findByProducto(itemPedido.getProducto().getIdProducto());
				model.addObject("salsas", salsas);
			}

			List<Precio> precios = this.serviceProducto.findPreciosByProducto(producto.getIdProducto());
			Precio ultimoPrecio = precios.get(precios.size() - 1);
			// model.addObject("precios", precios);
			model.addObject("ultimoPrecio", ultimoPrecio.getValor());

			model.addObject("categoria", producto.getCategoria().getDescripcion());
			model.addObject("success", success);
			model.addObject("alert", alert);
			model.setViewName("re-item-pedido");
		} catch (FueraDeHorarioComercioException e) {
			model.addObject("alert", SingletonConstants.MESSAGE_COMERCIO_CERRADO);
			model.setViewName("redirect:/comercio-" + comercio.getIdComercio() + "-menu");
		} finally {
			return model;
		}
	}

	/**
	 *
	 * @throws Exception
	 * @throws DataAccessException
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = { "/producto-pedido-add" }, method = RequestMethod.POST)
	public ModelAndView addItemPedido(ModelAndView model, HttpServletRequest request, ItemPedido itemPedido,
			BindingResult result, String success, HttpSession session) throws DataAccessException, Exception {

		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		Comercio comercio = this.serviceComercio.findById(itemPedido.getProducto().getComercio().getIdComercio());
		try {

			if (!this.serviceComercio.validaFranjaHorariaComercio(comercio.getIdComercio())) {
				throw new FueraDeHorarioComercioException(SingletonConstants.MESSAGE_COMERCIO_CERRADO);
			}

			if (this.shoppingCart.getItemsDePedido().size() > 0) {
				if (!comercio.getIdComercio().equals(
						this.shoppingCart.getItemsDePedido().get(0).getProducto().getComercio().getIdComercio())) {
					throw new TodosItemsCarritoMismoComercio(SingletonConstants.MESSAGE_PRODUCTOS_COMERCIO);
				}
			}

			int nroItem = this.servicePedido.generateNroItem();
			itemPedido.setNroItem(nroItem);

			CategoriaProducto categoria = this.serviceCategoriaProducto
					.findById(itemPedido.getProducto().getCategoria().getIdCategoria());
			itemPedido.setSubtotal(this.servicePedido.calcularSubtotalItem(itemPedido, categoria));

			if (categoria.getDescripcion().equals("Helados")) {
				Sabor sabor;
				SaboresDeItem saborItem;
				for (String id : itemPedido.getSabores()) {
					sabor = new Sabor();
					sabor = this.serviceSabor.findById(new Long(id));
					/*
					 * Ver si esto lo tengo que hacer recien cuando guardo el
					 * pedido en la DB
					 */
					saborItem = new SaboresDeItem(sabor, itemPedido);
					// sabor.getSaboresItem().add(saborItem); //No se si sirve
					// que guarde el saborItem en el sabor.
					itemPedido.getSaboresItem().add(saborItem);

				}
			} else if (categoria.getDescripcion().equals("Pastas")) {
				Salsa salsa = this.serviceProducto.findSalsaById(new Long(itemPedido.getSalsa()));
				itemPedido.setSalsaItem(salsa);
			}

			/*
			 * Agrego el item al carrito.
			 */
			List<Precio> precios = this.serviceProducto.findPreciosByProducto(itemPedido.getProducto().getIdProducto());
			itemPedido.getProducto().getPrecios().addAll(precios);
			this.shoppingCart.getItemsDePedido().add(itemPedido);

			this.shoppingCart.setComercio(itemPedido.getProducto().getComercio());
		
			model.addObject("carrito", this.shoppingCart.getItemsDePedido());

			model.addObject("success", "El producto " + ManagerString.cleanString(categoria.getDescripcion()) + ": "
					+ ManagerString.cleanString(itemPedido.getProducto().getDescripcion()) + " se agreg&oacute; correctamente al carrito");
			model.setViewName("redirect:/comercio-" + itemPedido.getProducto().getComercio().getIdComercio() + "-menu");

		} catch (FueraDeHorarioComercioException e) {
			model.addObject("alert", SingletonConstants.MESSAGE_COMERCIO_CERRADO);
			model.setViewName("redirect:/comercio-" + comercio.getIdComercio() + "-menu");
		} catch (TodosItemsCarritoMismoComercio t) {
			model.addObject("alert", SingletonConstants.MESSAGE_PRODUCTOS_COMERCIO);
			model.setViewName("redirect:/producto-" + itemPedido.getProducto().getIdProducto() + "-pedido-add");
		} finally {
			return model;
		}
	}

	/**
	 * Muestra los datos de un item de pedido.
	 *
	 */
	@RequestMapping(value = { "/item-{nroItem}-pedido-view" }, method = RequestMethod.GET)
	public ModelAndView viewItemPedido(@PathVariable Integer nroItem, ModelAndView model, HttpSession session)
			throws DataAccessException, Exception {

		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		Usuario usuario = new Usuario("S");
		model.addObject("usuario", usuario);

		List<ItemPedido> items = this.shoppingCart.getItemsDePedido();
		ItemPedido item = new ItemPedido();
		for (ItemPedido ip : items) {
			if (ip.getNroItem() == nroItem) {
				item = ip;
			}
		}

		Producto producto = this.serviceProducto.findById(item.getProducto().getIdProducto());
		item.setProducto(producto);
		model.addObject("itemPedido", item);

		if (producto.getCategoria().getDescripcion().equals("Helados")) {
			List<Sabor> sabores = new ArrayList<Sabor>();
			Sabor sabor = new Sabor();
			for (String s : item.getSabores()) {
				sabor = this.serviceSabor.findById(Long.valueOf(s));
				sabores.add(sabor);
			}
			model.addObject("sabores", sabores);
		} else if (producto.getCategoria().getDescripcion().equals("Pastas")) {

			model.addObject("salsas", "");
		}

		List<Precio> precios = this.serviceProducto.findPreciosByProducto(producto.getIdProducto());
		Precio ultimoPrecio = precios.get(precios.size() - 1);
		// model.addObject("precios", precios);
		model.addObject("ultimoPrecio", ultimoPrecio.getValor());

		model.addObject("categoria", producto.getCategoria().getDescripcion());
		model.setViewName("ver-item-pedido");
		return model;

	}

	/**
	 * Carga en la vista el item de pedido a actualizar.
	 */
	@RequestMapping(value = { "/producto-{id}-pedido-update" }, method = RequestMethod.GET)
	public ModelAndView editItemPedido(@PathVariable Long id, ModelAndView model, String success, HttpSession session)
			throws DataAccessException, Exception {

		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		model.setViewName("messages/en-construccion");
		return model;
	}
	
	/**
	 * Carga en la vista el item de pedido a actualizar.
	 */
	@RequestMapping(value = { "/pedidos-{id}-repeat" }, method = RequestMethod.GET)
	public ModelAndView repeatPedido(@PathVariable Long id, ModelAndView model, String success, HttpSession session)
			throws DataAccessException, Exception {

		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		model.setViewName("messages/en-construccion");
		return model;
	}

	@RequestMapping(value = { "/pedidos-new" }, method = RequestMethod.GET)
	public ModelAndView newPedido(HttpSession sesion, ModelAndView model, String alert) throws DataAccessException, Exception {
		
		sesion.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		String direccion = (String) sesion.getAttribute("direccion");
		String nro = (String) sesion.getAttribute("nro");
		String piso = (String) sesion.getAttribute("piso");
		String depto = (String) sesion.getAttribute("depto");
		String observaciones = (String) sesion.getAttribute("observaciones");

		Usuario usuario = new Usuario("S");
		model.addObject("usuario", usuario);

		PedidoDto pedido = this.servicePedido.createPedido();
		this.shoppingCart.setPedido(pedido);
		
		if(direccion != null)
			pedido.setDireccion(direccion);
		
		if(nro != null)
			pedido.setNro(nro);
		
		if(piso != null)
			pedido.setPiso(piso);
		
		if(depto != null)
			pedido.setDepto(depto);
		
		if(observaciones != null)
			pedido.setObservaciones(observaciones);
			
		model.addObject("pedido", pedido);

		model.setViewName("re-pedido");
		model.addObject("alert", alert);
		return model;
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = { "/preview-pedido" }, method = RequestMethod.POST)
	public ModelAndView previewPedido(HttpSession sesion, ModelAndView model, PedidoDto pedidoDto) throws DataAccessException, Exception {

		sesion.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		sesion.setAttribute("direccion", pedidoDto.getDireccion());
		sesion.setAttribute("nro", pedidoDto.getNro());
		sesion.setAttribute("piso", pedidoDto.getPiso());
		sesion.setAttribute("depto", pedidoDto.getDepto());
		sesion.setAttribute("observaciones", pedidoDto.getObservaciones());
		
		try {

			if (!this.servicePedido.validaCarritoVacio()) {
				throw new CarritoVacioException(SingletonConstants.MESSAGE_CARRITO_VACIO);
			}

			if (!this.serviceComercio.validaFranjaHorariaComercio(pedidoDto.getComercio().getIdComercio())) {
				throw new FueraDeHorarioComercioException(SingletonConstants.MESSAGE_COMERCIO_CERRADO);
			}

			if (pedidoDto.getConEnvio() != null) {
				String conEnvio = pedidoDto.getConEnvio();
				if (conEnvio.equals("S")) {
					Double total = pedidoDto.getTotal();
					if (pedidoDto.getComercio().getCompraMinima() != null) {
						if (total < pedidoDto.getComercio().getCompraMinima()) {
							throw new CompraMinimaExcepcion(SingletonConstants.MESSAGE_COMPRA_MINIMA);
						}
					}
					Comercio comercio = this.serviceComercio.findById(pedidoDto.getComercio().getIdComercio());
					total += comercio.getCostoEnvio();
					pedidoDto.setComercio(comercio);
					pedidoDto.setTotal(total);
					pedidoDto.setCostoEnvio(comercio.getCostoEnvio());
				}
			} else {
				pedidoDto.setConEnvio("N");
			}

			Usuario usuario = new Usuario("S");
			model.addObject("usuario", usuario);

			List<ItemPedido> items = pedidoDto.getItems();
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getSabores().size() != 0) {
					Sabor sabor;
					SaboresDeItem saborItem;
					for (String idSabor : items.get(i).getSabores()) {
						sabor = new Sabor();
						sabor = this.serviceSabor.findById(new Long(idSabor));
						saborItem = new SaboresDeItem(sabor, items.get(i));
						items.get(i).getSaboresItem().add(saborItem);
					}
				}
			}

			model.addObject("pedido", pedidoDto);

			// Validar si está autenticado el usuario.
			model.setViewName("preview-pedido");
		} catch (CarritoVacioException cve) {
			model.addObject("alert", SingletonConstants.MESSAGE_CARRITO_VACIO);
			model.setViewName("redirect:/pedidos-new");
		} catch (FueraDeHorarioComercioException e) {
			model.addObject("alert", SingletonConstants.MESSAGE_COMERCIO_CERRADO);
			model.setViewName("redirect:/comercio-" + pedidoDto.getComercio().getIdComercio() + "-menu");
		} catch (CompraMinimaExcepcion cme) {
			model.addObject("alert",
					SingletonConstants.MESSAGE_COMPRA_MINIMA + "$" + pedidoDto.getComercio().getCompraMinima());
			model.setViewName("redirect:/pedidos-new");
		} finally {
			return model;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = { "/pedidos-new" }, method = RequestMethod.POST)
	public ModelAndView savePedido(HttpSession sesion, 
			ModelAndView model, PedidoDto pedidoDto) throws DataAccessException, Exception {

		try {
			
			sesion.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());

			if (!this.serviceComercio.validaFranjaHorariaComercio(pedidoDto.getComercio().getIdComercio())) {
				throw new FueraDeHorarioComercioException(SingletonConstants.MESSAGE_COMERCIO_CERRADO);
			}

			CustomUserDetails auth = new CustomUserDetails();
			if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
				if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
					auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				} else {
					model.addObject("error", ManagerString.cleanString("Ingrese con su usuario y contraseña para poder realizar el pedido"));
					model.setViewName("redirect:/login");
				}
			}

			Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());
			List<ItemPedido> items = pedidoDto.getItems();
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getSabores().size() > 0) {
					Sabor sabor;
					SaboresDeItem saborItem;
					for (String idSabor : items.get(i).getSabores()) {
						sabor = new Sabor();
						sabor = this.serviceSabor.findById(new Long(idSabor));
						saborItem = new SaboresDeItem(sabor, items.get(i));
						items.get(i).getSaboresItem().add(saborItem);
					}
				}
			}

			Pedido pedido = ClassToDto.dtoToPedido(pedidoDto);
			pedido.setUsuario(usuario);
			
			if(pedido.getCostoEnvio() == null) {
				pedido.setCostoEnvio(0.0);
			}

			try {
				this.servicePedido.savePedido(pedido);
				String mensajeComun = "Excelente! Ya realizaste tu pedido! Ahora solo";
				if (pedido.getConEnvio().equals("S")) {
					model.addObject("success", mensajeComun + " espera a que llegue el del&iacutevery!");
				} else {
					model.addObject("success", mensajeComun + " tienes que ir a buscarlo sin esperas!");
				}
				
				if(null != pedido.getUsuario().getEmail() && pedido.getUsuario().getNotificaciones().equals("S")) {
					this.serviceSendEmail.emailChargeStatus(EstadoPedido.TipoEstadoPedido.INICIADO.getDescripcion(),
							pedido.getUsuario(), null);	
				} 
				
			} catch (Exception e) {
				e.getMessage();
			}	

			sesion.removeAttribute("direccion");
			sesion.removeAttribute("nro");
			sesion.removeAttribute("piso");
			sesion.removeAttribute("depto");
			sesion.removeAttribute("observaciones");
			
			model.addObject("pedido", pedido);
			model.setViewName("redirect:/reporte-mis-pedidos");
		} catch (FueraDeHorarioComercioException e) {
			model.addObject("alert", SingletonConstants.MESSAGE_COMERCIO_CERRADO);
			model.setViewName("redirect:/comercio-" + pedidoDto.getComercio().getIdComercio() + "-menu");
		} finally {
			return model;
		}

	}

	/**
	 * Muestra los datos de un comercio.
	 *
	 */
	@RequestMapping(value = { "/pedidos-{id}-view" }, method = RequestMethod.GET)
	public ModelAndView viewPedido(@PathVariable("id") Long id, ModelAndView model, HttpSession session)
			throws DataAccessException, Exception {
		
		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());

		if (usuario == null) {
			throw new AccessDeniedException(null);
		} else {					
			PedidoDto pedido = this.servicePedido.findDtoById(id);		
			Comercio comercio = this.serviceComercio.findById(pedido.getComercio().getIdComercio());
			if(comercio.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
				//El comercio mira el detalle del pedido.
				//Validar estado del mismo.
				
				EstadoPedido estado = this.serviceEstado.findById(pedido.getUltimoEstado().getIdEstadoPedido(), false);
				if(estado.getDescripcion().equals(SingletonConstants.ESTADO_PEDIDO_INICIADO) 
						|| estado.getDescripcion().equals(SingletonConstants.ESTADO_PEDIDO_RECHAZADO)) {
					throw new AccessDeniedException(null);
				} else {
					model.addObject("pedido", pedido);
					model.setViewName("ver-pedido");
					return model;
				}
			} else {
				//El cliente mira el detalle del pedido.
				if(pedido.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
					model.addObject("pedido", pedido);
					model.setViewName("ver-pedido");
					return model;
				} else {
					throw new AccessDeniedException(null);
				}
			}
		}

	}

	@RequestMapping(value = { "/pedidos-listado" }, method = RequestMethod.POST)
	public ModelAndView reporteVentasComercio(ModelAndView model, BusquedaDto busqueda, HttpSession session)
			throws DataAccessException, Exception {

		session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
		CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Usuario usuario = this.serviceUsuario.findUsuarioById(auth.getIdUsuario());

		if (usuario == null) {
			throw new AccessDeniedException(null);
		} else {
			SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy hh:mm aaa", Locale.US);
			busqueda.setFechaDesde(format.parse(busqueda.getDesde()));
			busqueda.setFechaHasta(format.parse(busqueda.getHasta()));

			List<Pedido> pedidos = this.servicePedido.findPedidosDevengables(busqueda.getComercio().getIdComercio(),
					busqueda.getFechaDesde(), busqueda.getFechaHasta());

			List<PedidoDto> pedidosDto = this.servicePedido.sorterListPedidos(pedidos);
			pedidosDto = this.serviceEstado.findByEstadoActual(pedidosDto);

			model.addObject("pedidos", pedidosDto);
			model.addObject("fechaDesde", busqueda.getFechaDesde());
			model.addObject("fechaHasta", busqueda.getFechaHasta());
			model.addObject("comercio", busqueda.getComercio().getDescripcion());
			model.setViewName("lista-de-pedidos-comercio");
			return model;
		}

	}

	public boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
				&& authentication.isAuthenticated();
	}

}
