package com.proyectofinal.app.util;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.proyectofinal.app.core.security.CustomUserDetails;
import com.proyectofinal.app.dto.PedidoDto;
import com.proyectofinal.app.model.EstadoDePedido;
import com.proyectofinal.app.model.EstadoPedido;
import com.proyectofinal.app.model.Pedido;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.service.EstadoService;
import com.proyectofinal.app.service.PedidoService;
import com.proyectofinal.app.service.SendEmailService;
import com.proyectofinal.app.service.UsuarioService;

@WebServlet("/ActualizarEstadoPedidoServlet")
@Transactional
public class ActualizarEstadoPedidoServlet extends HttpServlet {

	@Autowired
	private UsuarioService serviceUsuario;
	
	@Autowired
	private EstadoService serviceEstado;
	
	@Autowired
	private SendEmailService serviceSendEmail;
		
	@Autowired
	private PedidoService servicePedido;
	
	@Autowired
    private WebApplicationContext springContext;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }
    
	private static final long serialVersionUID = 1L;
	
    public ActualizarEstadoPedidoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public String getServletInfo() 
	{
	   return "Short description";
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws DataAccessException, NumberFormatException, ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws DataAccessException, NumberFormatException, ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws DataAccessException, NumberFormatException, Exception 
	{			
	 	response.setContentType("text/html;charset=ISO-8859-1");
	 	String idPedido = request.getParameter("idPedido");
        String idEstado = request.getParameter("idEstado");
        String aclaraciones = request.getParameter("aclaraciones");
        
        Long idUsuario = null;
    	if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
    		CustomUserDetails auth = 
    				(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
    		idUsuario = auth.getIdUsuario();
    	}
    	
    	Usuario usuario = this.serviceUsuario.findUsuarioById(idUsuario);   	
    	Pedido pedido = this.servicePedido.findById(Long.valueOf(idPedido));
    	EstadoPedido estadoPedido = this.serviceEstado.findById(Long.valueOf(idEstado), true);
    	
    	
    	//Guardo el Estado de pedido.
    	EstadoDePedido estadoDePedido = new EstadoDePedido(); 	
    	estadoDePedido.setEstadoPedido(estadoPedido);
    	estadoDePedido.setAclaraciones(aclaraciones);
    	estadoDePedido.setUsuario(usuario); 
    	estadoPedido.getEstadosDePedido().add(estadoDePedido);
    	pedido.getEstados().add(estadoDePedido);
    	estadoDePedido.setPedido(pedido); 
    	Long idMaxEstado = this.serviceEstado.saveEstado(estadoDePedido);
    	
    	pedido.setIdUltimoEstado(idMaxEstado);
    	this.servicePedido.updatePedido(pedido);
    	
    	EstadoPedido nuevoEstado = new EstadoPedido();
    	nuevoEstado = this.serviceEstado.findById(Long.valueOf(idEstado), false);
	
        nuevoEstado.setEstadosDePedido(null);
        PedidoDto dto = new PedidoDto();
        dto.setUltimoEstado(nuevoEstado);
        dto = this.serviceEstado.findByEstadoActual(dto);
        	
		//Si tiene seteado email y las notificaciones en SI - Le envio emails por 
		//los cambios de estado.
		if(null != pedido.getUsuario().getEmail() && pedido.getUsuario().getNotificaciones().equals("S")) {
			this.serviceSendEmail.emailChargeStatus(nuevoEstado.getDescripcion(), pedido.getUsuario(), aclaraciones);	
		} 
      
        Gson gson = new Gson();  
        String json = gson.toJson(dto);   
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json); 
	}
	
	/*
	 * Metodo obsoleto
	 */
	/*Actualizar estado de pedido seleccionado */
	public EstadoPedido actualizarEstadoPedido(String idPedido,String idEstado, String aclaraciones) 
			throws SQLException 
	{
		PreparedStatement preparedStatementInsert = null;
		PreparedStatement preparedStatementUpdate = null;
		PreparedStatement preparedStatementSelect = null;
		Connection conn = null;
		Long idUsuario = null;
		EstadoPedido nuevoEstado = new EstadoPedido();
		
    	if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
    		CustomUserDetails auth = 
    				(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
    		idUsuario = auth.getIdUsuario();
    	}
			
		try
		{
			Class.forName("com.mysql.jdbc.Driver") ;
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/proyecto_final?useUnicode=true&characterEncoding=UTF-8", "root", "") ;
					
			String insertEstadoPedido = "INSERT INTO estados_de_pedido"
					+ "(id_pedido, id_estado, aclaraciones, id_usuario) VALUES"
					+ "(?,?,?,?)";
			
			preparedStatementInsert = conn.prepareStatement(insertEstadoPedido);

			preparedStatementInsert.setLong(1, Long.valueOf(idPedido));
			preparedStatementInsert.setLong(2, Long.valueOf(idEstado));
			preparedStatementInsert.setString(3, aclaraciones);
			preparedStatementInsert.setLong(4, idUsuario);

			preparedStatementInsert.executeUpdate();
			
			/*
			 * Recuperar el id de el estado recien insertado.
			 */
			String selectSQL = "SELECT MAX(id_estado_de_pedido) FROM estados_de_pedido WHERE id_pedido = ?";

			preparedStatementSelect = conn.prepareStatement(selectSQL);
			preparedStatementSelect.setString(1, idPedido);

			ResultSet rs = preparedStatementSelect.executeQuery();		
			Long idEstadoPedido = 0L;
			while (rs.next()) {
					idEstadoPedido = rs.getLong(1);
			}		

			/*
			 * UPDATE de pedido.
			 * 1 - UltimoEstadoDePedido.
			 */
			String updatePedido = "UPDATE pedido SET ultimo_estado = ? "+ " WHERE id_pedido = ?";
			
			preparedStatementUpdate = conn.prepareStatement(updatePedido);

			preparedStatementUpdate.setLong(1, Long.valueOf(idEstadoPedido));
			preparedStatementUpdate.setString(2, idPedido);

			preparedStatementUpdate.executeUpdate();
			
			/*
			 * Buscar nuevo estado por id y devolver descripcion.
			 */
			nuevoEstado = this.serviceEstado.findById(Long.valueOf(idEstado), false);
	
			/*
			 * ENVIO DE EMAIL.
			 * Cuando los estados sean:
			 * 1 - Rechazado. En este caso, debería ser obligatorio el campo 'aclaraciones'
			 * 2 - Recepcionado.
			 * 3 - Listo.
			 */
			
			PedidoDto pedido = this.servicePedido.findDtoById(Long.valueOf(idPedido));
			
			//Si tiene seteado email y las notificaciones en SI - Le envio emails por 
			//los cambios de estado.
			if(null != pedido.getUsuario().getEmail() && pedido.getUsuario().getNotificaciones().equals("S")) {
				this.serviceSendEmail.emailChargeStatus(nuevoEstado.getDescripcion(), pedido.getUsuario(), aclaraciones);	
			} 
			
			conn.close();
			return nuevoEstado;	
		}
		catch(Exception e)
		{
			return null;
		} finally {

			if (preparedStatementInsert != null) {
				preparedStatementInsert.close();
			}
			if (preparedStatementUpdate != null) {
				preparedStatementUpdate.close();
			}
			if (preparedStatementSelect != null) {
				preparedStatementSelect.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}
	
}
