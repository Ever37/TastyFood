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
import com.proyectofinal.app.model.TelefonoComercio;
import com.proyectofinal.app.service.ComercioService;
import com.proyectofinal.app.model.Comercio;

@WebServlet("/BorrarTelefonoComercioServlet")
@Transactional
public class BorrarTelefonoComercioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComercioService serviceComercio;
	
	@Autowired	
    private WebApplicationContext springContext;
	
    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }
    
    public BorrarTelefonoComercioServlet() {
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
        String idTelefono = request.getParameter("idTelefono");
        String idComercio = request.getParameter("idComercio");
        //List<TelefonoComercio> telefonos = borrarTelefono(idTelefono, idComercio);
        
        this.serviceComercio.deleteTelefono(Long.valueOf(idTelefono));
        List<TelefonoComercio> tel = this.serviceComercio.findTelefonosComercio(Long.valueOf(idComercio));
        List<TelefonoComercio> telefonos = new ArrayList<TelefonoComercio>();
        TelefonoComercio tc;
        for(TelefonoComercio t: tel) {
        	tc = new TelefonoComercio(t.getIdTelefono(), t.getNumero(), t.getFechaBaja());        	       	
        	telefonos.add(tc);
        }        
                  
        Gson gson = new Gson();        
        TelefonoComercio [] arrayTelefonos = new TelefonoComercio[telefonos.size()];
        for (int i = 0; i < arrayTelefonos.length; i++) {
        	arrayTelefonos[i] = telefonos.get(i);
        }
        
        String json = gson.toJson(arrayTelefonos);   
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);   
        
	}
	
	/*
	 * Método obsoleto, reemplazado.
	 */
	/*Borra el telefono segun id, y devuelve la lista de telefonos activos*/
	public List<TelefonoComercio> borrarTelefono(String idTelefono, String idComercio) 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver") ;
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/proyecto_final?useUnicode=true&characterEncoding=UTF-8", "root", "") ;
			
			/*
			 * Dar de baja el numero de telefono por id.
			 */
			Calendar calendar = Calendar.getInstance();
			Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
			String queryDelete = "UPDATE telefono_comercio SET fecha_baja = ? WHERE id_telefono_comercio = ?";
			PreparedStatement ps = conn.prepareStatement(queryDelete);
			ps.setTimestamp(1, currentTimestamp);
			ps.setString(2,idTelefono);
		    ps.executeUpdate();
		    ps.close();
						
			/*
			 * Se recuperan todos los telefonos del comercio no dados de baja.
			 */
			Statement stmt = conn.createStatement() ;
			String queryTelefonos = "SELECT * FROM telefono_comercio WHERE comercio = '" + idComercio + "' AND "
									+ "fecha_baja IS NULL";
			ResultSet rs = stmt.executeQuery(queryTelefonos) ;
			
			/*
			 * Se recupera el comercio
			 */
			String queryComercio = "SELECT * FROM comercio WHERE id_comercio = '" + idComercio + "'";
			Statement stmt2 = conn.createStatement();
			ResultSet rsCom = stmt2.executeQuery(queryComercio);
			
			Comercio comercio = new Comercio();
			while(rsCom.next()) {
				comercio.setIdComercio(rsCom.getLong("id_comercio"));
				comercio.setDescripcion(rsCom.getString("descripcion"));				
			}
			
			/*
			 * Completa la lista de telefonos a devolver
			 */
			List<TelefonoComercio> telefonos = new ArrayList<TelefonoComercio>();
			TelefonoComercio telefono;
			while(rs.next())
			{
				telefono = new TelefonoComercio();
				telefono.setIdTelefono(rs.getLong("id_telefono_comercio"));
				telefono.setNumero(rs.getString("numero"));
				telefono.setComercio(comercio);	
				telefonos.add(telefono);
			}
			
			conn.close();
			return telefonos;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
    
}
