package com.proyectofinal.app.util;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.proyectofinal.app.model.Salsa;
import com.proyectofinal.app.service.ProductoService;

@WebServlet("/MostrarPrecioSalsaServlet")
@Transactional
public class MostrarPrecioSalsaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProductoService serviceProducto;
	
	@Autowired
    private WebApplicationContext springContext;
    
    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }
    
    public MostrarPrecioSalsaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
        
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws DataAccessException, NumberFormatException, Exception 
	{
		 	response.setContentType("text/html;charset=ISO-8859-1");
	        String idSalsa = request.getParameter("idSalsa");
	        
	        Salsa s = this.serviceProducto.findSalsaById(Long.valueOf(idSalsa));
	        Salsa salsa = new Salsa(s.getIdSalsa(), s.getDescripcion(),s.getPrecio() ,s.getFechaBaja());
	        //Salsa salsa = buscarPrecioSalsa(idSalsa);	   
	        
	        Gson gson = new Gson();        
	        String json = gson.toJson(salsa);	        
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(json);       	        
	}
    
	@Override
	public String getServletInfo() 
	{
	   return "Short description";
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Metodo en desuso, reemplazado por findSalsaById.
	 */
	/*Busca el precio de la salsa*/
	public Salsa buscarPrecioSalsa(String idSalsa) 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver") ;
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/proyecto_final?useUnicode=true&characterEncoding=UTF-8", "root", "") ;
						
			Statement stmt = conn.createStatement() ;
			String querySalsa = "SELECT * FROM salsa WHERE id_salsa = '" + idSalsa + "'";
			ResultSet rs = stmt.executeQuery(querySalsa) ;
			
			Salsa salsa = new Salsa();
			while(rs.next())
			{
				salsa = new Salsa();
				salsa.setIdSalsa(rs.getLong("id_salsa"));
				salsa.setDescripcion(rs.getString("descripcion"));
				salsa.setPrecio(rs.getDouble("precio"));
			}			
			return salsa;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
