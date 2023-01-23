package com.proyectofinal.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpSession;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.proyectofinal.app.model.Carrito;
import com.proyectofinal.app.model.Consulta;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.service.ConsultaService;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


@Controller
public class ConsultasController {

    @Autowired
    ConsultaService serviceConsulta;
    
    @Autowired
    Carrito shoppingCart;
    
    /**
     * Muestra la página de contacto
     *
     */
    @RequestMapping(value = {"/contacto"}, method = RequestMethod.GET)
    public ModelAndView newConsulta(ModelAndView model, String success,
    		HttpSession session) throws DataAccessException, Exception {

	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
		
    Usuario usuario = new Usuario("S");
    model.addObject("usuario", usuario);

    Consulta consulta = new Consulta();
    model.addObject("consulta", consulta);
	
    model.addObject("success", success);
	model.setViewName("re-consulta");
	return model;
	
    }
    
    /**
     * Se utiliza en el submit del formulario de alta de consulta.
     */
    @RequestMapping(value = {"/contacto"}, method = RequestMethod.POST)
    public ModelAndView saveConsulta(Consulta consulta, SessionStatus status, BindingResult result,
	    HttpSession session, ModelAndView model) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	if (!result.hasErrors()) {
    		
    		try {
    				this.serviceConsulta.saveConsulta(consulta); 
    			    model.addObject("success", "Correcto: La consulta ha sido enviada. Recibir&aacute; una respuesta en su email.");
    			    model.setViewName("redirect:/contacto");
    		
    		}catch (ConstraintViolationException e) {
    			System.out.println(e.getMessage());
    		}     	
    	}
    	return model;	
    }
    
    /**
     * Lista todas las consultas.
     *
     */
    @RequestMapping(value = {"/consultas-listado"}, method = RequestMethod.GET)
    public ModelAndView listConsultas(ModelAndView model, String success, String error,
    		HttpSession session)
	    throws DataAccessException, Exception {

    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	List<Consulta> consultas = new ArrayList<Consulta>();
    	consultas = this.serviceConsulta.findAllWithoutDate();
    	model.addObject("consultas", consultas);
    	model.addObject("success", success);
    	model.addObject("error", error);
    	model.setViewName("lista-de-consultas");
    	return model;   	    	
    }  
    
    /**
     * Agrega fechaBaja a las consultas que se den de baja.
     *
     * @throws Exception
     * @throws DataAccessException
     */
    @RequestMapping(value = {"/consultas-{id}-delete"}, method = RequestMethod.GET)
    public ModelAndView deleteConsulta(@PathVariable("id") Long id,
    		HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	ModelAndView modelMap = new ModelAndView("redirect:" + "/consultas-listado");
    	Consulta consulta = this.serviceConsulta.findById(id);
    	if(this.serviceConsulta.deleteConsulta(id)) {
        	modelMap.addObject("success", "Correcto: La consulta Nro " + consulta.getIdConsulta()  + " ha sido eliminada.");
    	} else {       	
        	modelMap.addObject("error", "Error: La consulta Nro " + consulta.getIdConsulta() + "' se encuentra Pendiente.");
    	}
    	return modelMap;  	
    }
    
    
    /**
     * Muestra los datos de una consulta.
     *
     */
    @RequestMapping(value = {"/consultas-{id}-view"}, method = RequestMethod.GET)
    public ModelAndView viewConsulta(
	    @PathVariable("id") Long id,
	    ModelAndView model, HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	Consulta consulta = this.serviceConsulta.findById(id);
    	model.addObject("consulta", consulta);

        Date date = consulta.getFechaAlta();
        SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	model.addObject("fechaAlta", targetDateFormat.format(date));

    	model.setViewName("ver-consulta");
    	return model;
    }
    
    /**
     * Carga en la vista de la consulta a actualizar.
     */
    @RequestMapping(value = {"/consultas-{id}-update"}, method = RequestMethod.GET)
    public ModelAndView editConsulta(@PathVariable Long id, ModelAndView model, String success,
    		HttpSession session)
	    throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	Consulta consulta = this.serviceConsulta.findById(id);
    	model.addObject("consulta", consulta);

        Date date = consulta.getFechaAlta();
        SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	model.addObject("fechaAlta", targetDateFormat.format(date));

    	model.addObject("success", success); 	
    	
    	model.setViewName("editar-consulta");
    	return model;
    }
    
    /**
     * Actualizar consulta mediante el submit del formulario de modificacion de
     * consulta.
     */
    @RequestMapping(value = {"/consultas-{id}-update"}, method = RequestMethod.POST)
    public ModelAndView updateConsulta(@PathVariable Long id, Consulta consulta,
	    BindingResult result, ModelAndView model, SessionStatus status,
	    HttpSession session) throws DataAccessException, Exception {
    	
    	session.setAttribute("contador", this.shoppingCart.getItemsDePedido().size());
    	
    	//EMAIL RECEPTOR
		String mailU = consulta.getEmailRemitente();
		
		//Envio de email.
		Properties properties = new Properties();
		Session mailsession;
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.socketFactory.port", "587");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.mail.sender", mailU);
		
		//EMAIL EMISOR
		properties.put("mail.smtp.user", "tastyfoodnotification@gmail.com");
		properties.put("mail.smtp.auth", "true");
		//Modificar contraseña
		properties.put("mail.smtp.password", "delivery.system");
		mailsession = Session.getDefaultInstance(properties);
		try
		{
			MimeMessage message = new MimeMessage(mailsession);
			message.setFrom(new InternetAddress("TastyFood <tastyfood.casilda@gmail.com>"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailU));
			message.setSubject("Respuesta a consulta", "UTF-8");
			String msj = consulta.getRespuesta(); 
			
			MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText(msj, "UTF-8", "html");
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	        message.setContent(multipart);
	                  
			Transport t = mailsession.getTransport("smtp");
			/* Crear cuenta gmail para el sistema */
			/* Activar acceso desde aplicaciones menos seguras en gmail */
			t.connect("tastyfoodnotification@gmail.com", "delivery.system"); 
			
			t.sendMessage(message, message.getAllRecipients());
			t.close();
					
			this.serviceConsulta.updateConsulta(consulta);
		    model = new ModelAndView("redirect:" + "/consultas-listado");
		    model.addObject("success", "Correcto: La consulta Nro.'" + consulta.getIdConsulta() + "' ha sido actualizada.");
		}
		catch (MessagingException me)
		{
		    me.printStackTrace();
		    model = new ModelAndView("redirect:" + "/consultas-listado");
		    model.addObject("error", "Error: No se pudo responder la consulta Nro. " + consulta.getIdConsulta() + ".");
			
		}
    	return model;
    }
    
}
