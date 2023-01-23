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
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Horario;
import com.proyectofinal.app.service.ComercioService;

@WebServlet("/BorrarHorarioComercioServlet")
@Transactional
public class BorrarHorarioComercioServlet extends HttpServlet {

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
    
    public BorrarHorarioComercioServlet() {
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
        String idHorario = request.getParameter("idHorario");
        String idComercio = request.getParameter("idComercio");
        //List<Horario> horarios = borrarHorario(idHorario, idComercio);	
        
        this.serviceComercio.deleteHorario(Long.valueOf(idHorario));
        List<Horario> hor = this.serviceComercio.findHorariosComercio(Long.valueOf(idComercio));
        List<Horario> horarios = new ArrayList<Horario>();
        Horario horario;
        for(Horario h: hor) {
        	horario = new Horario(h.getIdHorario(), h.getHoraDesde(), h.getHoraHasta(),
        						  h.getIndexDay(), h.getDia(), h.getFechaBaja());
        	horarios.add(horario);       	
        }
        
        Gson gson = new Gson();        
        Horario [] arrayHorarios = new Horario[horarios.size()];
        for (int i = 0; i < arrayHorarios.length; i++) {
        	arrayHorarios[i] = horarios.get(i);
        }
        
        String json = gson.toJson(arrayHorarios);   
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json); 
     
	}
	/*
	 * Método en desuso, reemplazado por deleteHorario
	 */
	/*Borra el telefono segun id, y devuelve la lista de telefonos activos*/
	public List<Horario> borrarHorario(String idHorario, String idComercio) 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver") ;
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/proyecto_final?useUnicode=true&characterEncoding=UTF-8", "root", "") ;
			
			/*
			 * Dar de baja el horario por id.
			 */
			Calendar calendar = Calendar.getInstance();
			Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
			String queryDelete = "UPDATE horario SET fecha_baja = ? WHERE id_horario = ?";
			PreparedStatement ps = conn.prepareStatement(queryDelete);
			ps.setTimestamp(1, currentTimestamp);
			ps.setString(2,idHorario);
		    ps.executeUpdate();
		    ps.close();
		    
			/*
			 * Se recuperan todos los horarios del comercio no dados de baja.
			 */
			Statement s = conn.createStatement() ;
			String queryHorarios = "SELECT * FROM horario WHERE comercio = '" + idComercio + "' AND "
									+ "fecha_baja IS NULL";
			ResultSet rs = s.executeQuery(queryHorarios);
								
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
			 * Completa la lista de horarios a devolver
			 */
			List<Horario> horarios = new ArrayList<Horario>();
			Horario horario;
			while(rs.next())
			{
				horario = new Horario();
				horario.setIdHorario(rs.getLong("id_horario"));
				horario.setDia(rs.getString("dia"));
				horario.setHoraDesde(rs.getTime("hora_desde"));
				horario.setHoraHasta(rs.getTime("hora_hasta"));
				horario.setComercio(comercio);
				horarios.add(horario);
			}
			
			conn.close();
			return horarios;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
}
