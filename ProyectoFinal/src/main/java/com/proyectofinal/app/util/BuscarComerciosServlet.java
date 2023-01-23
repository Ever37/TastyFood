package com.proyectofinal.app.util;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
import com.mysql.jdbc.Connection;
import com.proyectofinal.app.model.CategoriaProducto;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Promo;
import com.proyectofinal.app.service.ComercioService;
import com.proyectofinal.app.service.PromoService;
import com.google.gson.Gson;

/**
 * Servlet implementation class BuscarComercios
 */
@WebServlet("/BuscarComerciosServlet")
@Transactional
public class BuscarComerciosServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComercioService serviceComercio;
	
	@Autowired
	private PromoService servicePromo;
	
    private WebApplicationContext springContext;
	
    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }
    
    public BuscarComerciosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws DataAccessException, Exception 
	{
		 	response.setContentType("text/html;charset=ISO-8859-1");
	        String descripcion = request.getParameter("descripcion");
	        String categoria = request.getParameter("categoria");
	        
	        List<Comercio> comercios = new ArrayList<Comercio>();
	        if(descripcion.equals("") && categoria.equals("")) {
	        	comercios = this.serviceComercio.findAllWithoutDate();      	
	        } else {
		        comercios = this.serviceComercio
		        		.findAllByDescriptionAndCategoriaProducto(descripcion, categoria);	        	
	        }
	        
	        /*
	         * Esto se hace por las lista en LAZY, para que no genere error.
	         * Seguramente hay una forma mejor de hacerlo.
	         */
	        Promo promo = this.servicePromo.findPromoToday();
	        List<Comercio> comerciosTransformados = new ArrayList<Comercio>();
	        Comercio comer;
	        for(Comercio c: comercios) {
	        	comer = new Comercio();
	        	comer = ClassToDto.dtoToComercio(ClassToDto.comercioToDto(c));
	        	comer.setIdComercio(c.getIdComercio());
	        	comer.setUsuario(null);
	        	comer.setAbierto(this.serviceComercio.validaFranjaHorariaComercio(c.getIdComercio()));
	        	if(promo != null) {
	        		if(promo.getProducto().getComercio().getIdComercio().equals(c.getIdComercio())) {
	        			comer.setTienePromo(true);
	        		} else {
	        			comer.setTienePromo(false);
	        		}  
	        	}
	        	comerciosTransformados.add(comer);
	        }       
	        	        
	        Gson gson = new Gson();        
	        Comercio [] arrayComercios = new Comercio[comerciosTransformados.size()];
	        for (int i = 0; i < arrayComercios.length; i++) {
	        	arrayComercios[i] = comerciosTransformados.get(i);
	        }
	        String json = gson.toJson(arrayComercios);
	        
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
	 * Mètodo en desuso, cumple la misma función que 
	 * findAllByDescriptionAndCategoriaProducto()
	 */
	/*Busca los comercios segun la descripcion ingresada por el usuario*/
	public List<Comercio> buscarComercio(String descripcion, String categoria) 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver") ;
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/proyecto_final?useUnicode=true&characterEncoding=UTF-8", "root", "") ;
						
			Statement stmt = conn.createStatement() ;
			String queryComercios = "SELECT * FROM comercio WHERE descripcion LIKE '%" + descripcion + "%' AND fecha_baja IS NULL";
			ResultSet rs = stmt.executeQuery(queryComercios) ;
			
			Statement stmt4 = conn.createStatement() ;
			String queryCategoria = "SELECT * FROM categoria_de_producto WHERE id_categoria = '" + categoria + "' AND fecha_baja IS NULL";
			ResultSet rs4 = stmt4.executeQuery(queryCategoria) ;
			
			CategoriaProducto cat = new CategoriaProducto();
			while(rs4.next()) {
				cat.setIdCategoria(new Long(categoria));
				cat.setDescripcion(rs4.getString("descripcion"));				
			}
			
			/*
			 * Se recuperan los comercios con la descripcion ingresada.
			 */
			List<Comercio> comercios = new ArrayList<Comercio>();
			Comercio comercio;
			//TelefonoComercio telefono;
			while(rs.next())
			{
				comercio = new Comercio();	
				comercio.setIdComercio(rs.getLong("id_comercio"));
				comercio.setDescripcion(rs.getString("descripcion"));
				comercio.setDireccion(rs.getString("direccion"));
				comercio.setNro(rs.getString("nro"));
				comercio.setPiso(rs.getString("piso"));
				comercio.setDepto(rs.getString("depto"));
				comercio.setRealizaEnvios(rs.getString("realiza_envios"));
				comercio.setCostoEnvio(rs.getDouble("costo_envio"));
				comercio.setTiempoEnvioMin(rs.getString("tiempo_envio_min"));
				comercio.setTiempoEnvioMax(rs.getString("tiempo_envio_max"));
				comercio.setCompraMinima(rs.getDouble("compra_minima"));
				
				/*
				 * Se recuperan los telefonos de cada comercio.
				 */
				
				/* Esto funciona pero se suprime. 
				 * 
				String queryTelefonos = "SELECT * FROM telefono_comercio "
						+ "WHERE comercio = '" + rs.getLong("id_comercio") + "' AND fecha_baja IS NULL";
				Statement stmt2 = conn.createStatement();
				ResultSet rsTel = stmt2.executeQuery(queryTelefonos);
				while(rsTel.next()) 
				{
					telefono = new TelefonoComercio();
					telefono.setNumero(rsTel.getString("numero"));
					comercio.addTelefono(telefono);
				}
				*/
				
				//Verificar si la categoria es Todas
				if(!categoria.equals("")) {
								
				/*
				 * Se filtran los comercios que tengan productos pertenecientes a la categoria ingresada.
				 */
				String queryCategorias = "";
				queryCategorias = "SELECT * FROM producto "
							+ "WHERE id_categoria = '" + categoria + "' AND id_comercio = '" + rs.getLong("id_comercio") + "' "
							+ "AND fecha_baja IS NULL";					

				Statement stmt3 = conn.createStatement();	
				ResultSet rsCat = stmt3.executeQuery(queryCategorias);
				if(rsCat.next()) 
				{
					comercios.add(comercio);
				}	
				
				} else {
					comercios.add(comercio);
				}
			}
			
			return comercios;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
