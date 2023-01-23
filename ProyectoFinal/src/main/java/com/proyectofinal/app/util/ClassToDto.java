package com.proyectofinal.app.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import com.proyectofinal.app.dto.ComercioDto;
import com.proyectofinal.app.dto.CuentaDto;
import com.proyectofinal.app.dto.GrupoDto;
import com.proyectofinal.app.dto.PedidoDto;
import com.proyectofinal.app.dto.PermisoDto;
import com.proyectofinal.app.dto.ProductoDto;
import com.proyectofinal.app.dto.RequerimientoDto;
import com.proyectofinal.app.dto.RequerimientoDto.BuilderRequerimientoDto;
import com.proyectofinal.app.dto.UsuarioDto;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Cuenta;
import com.proyectofinal.app.model.Grupo;
import com.proyectofinal.app.model.Pedido;
import com.proyectofinal.app.model.PedidoCuenta;
import com.proyectofinal.app.model.Permiso;
import com.proyectofinal.app.model.Precio;
import com.proyectofinal.app.model.Producto;
import com.proyectofinal.app.model.Requerimiento;
import com.proyectofinal.app.model.RequerimientoPermisoGrupo;
import com.proyectofinal.app.model.Salsa;
import com.proyectofinal.app.model.Usuario;

public final class ClassToDto {

	/*Permiso a PermisoDto*/
    public static PermisoDto permisoToDto(Permiso permiso) throws DataAccessException, Exception {
    	return new PermisoDto(permiso.getIdPermiso(), permiso.getDescripcion(), permiso.getDescripcionCompleta());
    }
    
    /*Requerimiento a RequerimientoDto*/
    public static RequerimientoDto requerimientoToDto(Requerimiento requerimiento)
    	    throws DataAccessException, Exception {
    	RequerimientoDto requerimientoDto = new BuilderRequerimientoDto()
    		.hasIdRequerimiento(requerimiento.getIdRequerimiento()).hasDescripcion(requerimiento.getDescripcion())
    		.hasAlias(requerimiento.getAlias()).hasWorkflow(requerimiento.getWorkflow()).build();
    	return requerimientoDto;
    }
    
    /*Grupo a GrupoDto*/
    public static GrupoDto grupoToDto(Grupo grupo) throws DataAccessException, Exception {
    	return new GrupoDto(grupo.getIdGrupo(), grupo.getDescripcion());
    }
    
    /*RequerimientoPermisoGrupo a RequerimientoPermisoGrupoDto*/
    public static RequerimientoDto requerimientoToDto(RequerimientoPermisoGrupo reqGrupo)
    	    throws DataAccessException, Exception {
    	RequerimientoDto reqDto = new BuilderRequerimientoDto()
    		.hasIdRequerimientoPermisoGrupo(reqGrupo.getIdRequerimientoPermisoGrupo())
    		.hasIdRequerimiento(reqGrupo.getRequerimientoPermiso().getRequerimiento().getIdRequerimiento())
    		.hasDescripcion(reqGrupo.getRequerimientoPermiso().getRequerimiento().getDescripcion())
    		.hasWorkflow(reqGrupo.getRequerimientoPermiso().getRequerimiento().getWorkflow())
    		.hasPermiso(reqGrupo.getRequerimientoPermiso().getPermiso().getIdPermiso()).build();
    	return reqDto;
     
    }
    
    /*ComercioDto a Comercio*/
    public static Comercio dtoToComercio(ComercioDto comercio) throws DataAccessException, Exception {
    	Comercio c = new Comercio(
    			comercio.getDescripcion(),
    			comercio.getDireccion(),
    			comercio.getNro(),
    			comercio.getPiso(),
    			comercio.getDepto(),
    			comercio.getCuit(),
    			comercio.getRealizaEnvios(),
    			comercio.getCompraMinima(),
    			comercio.getTiempoEnvioMin(),
    			comercio.getTiempoEnvioMax(),
    			comercio.getCostoEnvio(),
    			comercio.getUsuario(),
    			comercio.getValoracionTotal());
    	return c;
    }
    
    /*ComercioDto a Comercio*/
    public static ComercioDto comercioToDto(Comercio comercio) throws DataAccessException, Exception {
    	ComercioDto c = new ComercioDto(
    			comercio.getIdComercio(),
    			comercio.getDescripcion(),
    			comercio.getDireccion(),
    			comercio.getNro(),
    			comercio.getPiso(),
    			comercio.getDepto(),
    			comercio.getCuit(),
    			comercio.getRealizaEnvios(),
    			comercio.getCompraMinima(),
    			comercio.getTiempoEnvioMin(),
    			comercio.getTiempoEnvioMax(),
    			comercio.getCostoEnvio(),
    			comercio.getUsuario(),
    			comercio.getValoracionTotal());
    	return c;
    }
    
    /*Usuario a UsuarioDto */
    public static UsuarioDto usuarioToDto(Usuario usuario) throws DataAccessException, Exception {
    	
    	UsuarioDto dto = new UsuarioDto(
    			usuario.getIdUsuario(),
    			usuario.getApellido(),
    			usuario.getNombre(),
    			usuario.getContrasena(),
    			usuario.getEmail(),
    			usuario.getNombreUsuario(),
    			ClassToDto.grupoToDto(usuario.getGrupo()),
    			usuario.getTelefono(),
    			usuario.getCelular(),
    			usuario.getNotificaciones(),
    			ManageDate.toGmtString(usuario.getFechaAlta()),
    			usuario.getValoracion());
		return dto;
    }
    
    /*ProductoDto a Producto*/
    public static Producto dtoToProducto(ProductoDto producto) throws DataAccessException, Exception {  	
    	Producto pro = new Producto(
    			producto.getIdProducto(),
    			producto.getDescripcion(),
    			producto.getObservaciones(),
    			producto.getCantidadGustos(),
    			producto.getCategoria(),
    			producto.getComercio());  	

    	pro.setActivo(producto.getActivo());
    	
    	for(Precio p : producto.getPrecios()) {
    		p.setProducto(pro);
    		pro.getPrecios().add(p);
    	}
    	
    	if(producto.getCategoria().getDescripcion().equals("Pastas")) {
        	for(Salsa s : producto.getSalsas()) {
        		s.setProducto(pro);
        		pro.getSalsas().add(s);
        	}   		
    	}
    	
    	//EXCEPCIÓN Id_producto no puede ser null.

    	return pro;
    }
    
    /*Producto a ProductoDto*/
    public static ProductoDto productoToDto(Producto producto) throws DataAccessException, Exception {  	
    	ProductoDto dto = new ProductoDto(
    			producto.getIdProducto(),
    			producto.getDescripcion(),
    			producto.getObservaciones(),
    			producto.getCantidadGustos(),
    			producto.getCategoria(),
    			producto.getComercio());
    	
    	dto.setActivo(producto.getActivo());
    	
		if(producto.getCategoria().getDescripcion().equals("Pastas")) {		
        	for(Salsa sal : producto.getSalsas()) {
        		dto.getSalsas().add(sal);
        	}  			
		} else if(producto.getCategoria().getDescripcion().equals("Helados")) {
			dto.setCantidadGustos(producto.getCantidadGustos());
		} else if(producto.getCategoria().getDescripcion().equals("Pizza")) {
			/*
			 * EN DESARROLLO.
			 */
		} 
		
    	return dto;
    }

	public static Pedido dtoToPedido(PedidoDto dto) throws DataAccessException, Exception {   
		
		
		Pedido pedido = new Pedido(
		   			dto.getConEnvio(),
		   			dto.getComercio(),
		   			dto.getItems(),
		   			dto.getTotal());
		
		pedido.setCostoEnvio(dto.getCostoEnvio());
		pedido.setIdPedido(dto.getIdPedido());
		pedido.setDireccion(dto.getDireccion());
		pedido.setNro(dto.getNro());
		pedido.setPiso(dto.getPiso());
		pedido.setDepto(dto.getDepto());		
		pedido.setObservaciones(dto.getObservaciones());
		
		return pedido;
	}
	
	public static PedidoDto pedidoToDto(Pedido pedido) throws DataAccessException, Exception {   
			
		PedidoDto dto = new PedidoDto(
				pedido.getConEnvio(),
				pedido.getComercio(),
				pedido.getTotal());
		/*
		 * Armar un mejor constructor de PedidoDto.
		 */
		dto.setCostoEnvio(pedido.getCostoEnvio());
		dto.setIdPedido(pedido.getIdPedido());
		dto.setFechaHora(pedido.getFechaHora());
		dto.setDireccion(pedido.getDireccion());
		dto.setNro(pedido.getNro());
		dto.setPiso(pedido.getPiso());
		dto.setDepto(pedido.getDepto());		
		dto.setObservaciones(pedido.getObservaciones());
		
		return dto;
	}

	public static Cuenta dtoToCuenta(CuentaDto c) {

		Cuenta cuenta = new Cuenta();
		cuenta.setComercio(c.getComercio());
		cuenta.setComision(c.getComision());
		cuenta.setComisionPorcentual(c.getComisionPorcentual());
		cuenta.setFechaDesde(c.getFechaDesde());
		cuenta.setFechaHasta(c.getFechaHasta());
		cuenta.setMontoTotal(c.getMontoTotal());
		
		List<PedidoCuenta> lista = new ArrayList<PedidoCuenta>();
		for(Pedido p:  c.getPedidos()) {
			PedidoCuenta cp = new PedidoCuenta();
			cp.setPedido(p);
			cp.setCuenta(cuenta);	
			lista.add(cp);
		}
		Set<PedidoCuenta> set = new HashSet<PedidoCuenta>(lista);
		cuenta.setPedidosCuentas(set);
		
		return cuenta;
	}

	public static CuentaDto cuentaToDto(Cuenta c) {
		
		CuentaDto cuenta = new CuentaDto();
		cuenta.setComercio(c.getComercio());
		cuenta.setComision(c.getComision());
		cuenta.setComisionPorcentual(c.getComisionPorcentual());
		cuenta.setFechaDesde(c.getFechaDesde());
		cuenta.setFechaHasta(c.getFechaHasta());
		cuenta.setMontoTotal(c.getMontoTotal());

		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy hh:mm aaa", Locale.US);  		
		String stringDesde = format.format(c.getFechaDesde());
		String stringHasta = format.format(c.getFechaHasta());
		
		cuenta.setDesde(stringDesde);
		cuenta.setHasta(stringHasta);
		
		List<Pedido> lista = new ArrayList<Pedido>();
		for(PedidoCuenta pc:  c.getPedidosCuentas()) {
			lista.add(pc.getPedido());
		}
		cuenta.setPedidos(lista);
		return cuenta;
	}
	
	
}
