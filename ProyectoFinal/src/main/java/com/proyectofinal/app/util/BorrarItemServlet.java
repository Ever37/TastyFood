package com.proyectofinal.app.util;

import java.io.IOException;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.ItemPedido;
import com.proyectofinal.app.service.ComercioService;

/**
 * Servlet implementation class BorrarItemServlet
 */
@WebServlet("/BorrarItemServlet")
@Transactional
public class BorrarItemServlet extends HttpServlet {
	
    @Autowired
    Carrito shoppingCart;
    
    @Autowired
    ComercioService serviceComercio;

    private WebApplicationContext springContext;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }

	
	private static final long serialVersionUID = 1L;
       
    public BorrarItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
	{	
		
	 	response.setContentType("text/html;charset=ISO-8859-1");
        String nroItem = request.getParameter("nroItem");
        List<ItemPedido> items = borrarItemPedido(nroItem);	
        
		
		/*
		 * Con este código solucioné  - Cuando hay más de un items, no funciona el borrar con el json.
		 */	
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ItemPedido.class, new MyTypeAdapter<ItemPedido>())
                .create();
        
        ItemPedido [] arrayItems = new ItemPedido[items.size()];
        JsonObject object;
        JsonArray array = new JsonArray();
        for (int i = 0; i < arrayItems.length; i++) {
        	arrayItems[i] = items.get(i);
        	
        	object = new JsonObject();
        	object.addProperty("nroItem", items.get(i).getNroItem());
        	object.addProperty("subtotal", items.get(i).getSubtotal());
        	array.add(object);
        }
        //String jsonArray = array.toString();
        
        String json = gson.toJson(array);   
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json); 
      
	}

	class MyTypeAdapter<T> extends TypeAdapter<T> {
	    public T read(JsonReader reader) throws IOException {
	        return null;
	    }
	    public void write(JsonWriter writer, T obj) throws IOException {
	        if (obj == null) {
	            writer.nullValue();
	            return;
	        }
	        writer.value(obj.toString());
	    }
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<ItemPedido> borrarItemPedido(String nroItem) 
	{
		try
		{
			List<ItemPedido> items = this.shoppingCart.getItemsDePedido();
			for(ItemPedido ip : items) {
				if(String.valueOf(ip.getNroItem()).equals(nroItem)) {
					items.remove(ip);
					break;
				}
			}	
			/*
			 * Si es el último producto del carrito, borrar todos los datos del carrito.
			 */
			if(items.size() == 0) {
				this.shoppingCart.setComercio(null);
				this.shoppingCart.getPedido().setComercio(null);
				this.shoppingCart.getPedido().setTotal(null);
			} else {
			 	Double total = 0.0;
				for(ItemPedido ip : items) {
					total += ip.getPrecio() * ip.getCantidad();
					if(ip.getProducto().getCategoria().getDescripcion().equals("Pastas")) {
						total += ip.getSalsaItem().getPrecio() * ip.getCantidad();
					}
				}
				Comercio comercio = this.serviceComercio.findById(this.shoppingCart.getComercio().getIdComercio());
				total += comercio.getCostoEnvio();
				this.shoppingCart.getPedido().setTotal(total);
			}
			return items;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
