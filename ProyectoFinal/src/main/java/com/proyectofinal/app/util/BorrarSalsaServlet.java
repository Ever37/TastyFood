package com.proyectofinal.app.util;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
import com.proyectofinal.app.model.Producto;
import com.proyectofinal.app.model.Salsa;
import com.proyectofinal.app.service.ProductoService;


@WebServlet("/BorrarSalsaServlet")
@Transactional
public class BorrarSalsaServlet extends HttpServlet {
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
    
    public BorrarSalsaServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws DataAccessException, NumberFormatException, Exception 
	{	
		
	 	response.setContentType("text/html;charset=ISO-8859-1");
        //String descripcion = request.getParameter("descripcion");
        String idProducto = request.getParameter("idProducto");
        String idSalsa = request.getParameter("idSalsa");
        
        
        //List<Salsa> salsas = borrarSalsa(descripcion, idProducto, idSalsa);	
        
        this.serviceProducto.borrarSalsa(Long.valueOf(idSalsa));
        List<Salsa> sal = this.serviceProducto.findByProducto(Long.valueOf(idProducto));
        
        List<Salsa> salsas = new ArrayList<Salsa>();
        Salsa salsa;
        for(Salsa s: sal) {
        	salsa = new Salsa(s.getIdSalsa(), s.getDescripcion(), s.getPrecio(), s.getFechaBaja());
        	salsas.add(salsa);
        }
        
        Gson gson = new Gson();        
        Salsa [] arraySalsas = new Salsa[salsas.size()];
        for (int i = 0; i < arraySalsas.length; i++) {
        	arraySalsas[i] = salsas.get(i);
        }
        
        String json = gson.toJson(arraySalsas);   
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);   
      
	}
	
	/*
	 * Mètodo en desuso, reemplazado por borrarSalsa y serviceProducto.
	 */
	/*Borra una salsa segun id, y devuelve la lista de salsas dadas de alta*/
	public List<Salsa> borrarSalsa(String descripcion, String idProducto, String idSalsa) 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver") ;
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/proyecto_final?useUnicode=true&characterEncoding=UTF-8", "root", "") ;
			
			/*
			 * Dar de baja la salsa por id.
			 */
			Calendar calendar = Calendar.getInstance();
			Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
			String queryDelete = "UPDATE salsa SET fecha_baja = ? WHERE id_salsa = ?";
			PreparedStatement ps = conn.prepareStatement(queryDelete);
			ps.setTimestamp(1, currentTimestamp);
			ps.setString(2,idSalsa);
		    ps.executeUpdate();
		    ps.close();
		    
			/*
			 * Se recuperan todas las salsas del producto no dadas de baja.
			 */
			Statement s = conn.createStatement() ;
			String querySalsas = "SELECT * FROM salsa WHERE id_producto = '" + idProducto + "' AND "
									+ "fecha_baja IS NULL";
			ResultSet rs = s.executeQuery(querySalsas);
								
			/*
			 * Se recupera el producto
			 */
			String queryProducto = "SELECT * FROM producto WHERE id_producto = '" + idProducto + "'";
			Statement stmt2 = conn.createStatement();
			ResultSet rsPro = stmt2.executeQuery(queryProducto);
			
			Producto producto = new Producto();
			while(rsPro.next()) {
				producto.setIdProducto(rsPro.getLong("id_producto"));
				producto.setDescripcion(rsPro.getString("descripcion"));				
			}
			
			/*
			 * Completa la lista de salsas a devolver
			 */
			List<Salsa> salsas = new ArrayList<Salsa>();
			Salsa salsa;
			while(rs.next())
			{
				salsa = new Salsa();
				salsa.setIdSalsa(rs.getLong("id_salsa"));
				salsa.setDescripcion(rs.getString("descripcion"));
				salsa.setPrecio(rs.getDouble("precio"));
				salsa.setProducto(producto);
				salsas.add(salsa);
			}
			
			conn.close();
			return salsas;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
