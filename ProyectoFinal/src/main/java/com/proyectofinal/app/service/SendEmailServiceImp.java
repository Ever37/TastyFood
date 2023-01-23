package com.proyectofinal.app.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.model.Usuario;
import com.proyectofinal.app.util.SingletonConstants;

@Service("sendEmailService")
@Transactional
public class SendEmailServiceImp implements SendEmailService {

	@Override
	public void emailChargeStatus(String estado, Usuario usuario, String aclaraciones) 
			throws DataAccessException, Exception {		
		
		String mailU = usuario.getEmail();
				
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
			message.setSubject("Estado de su pedido: " + estado, "UTF-8");
								
			String msj = "";
			
			switch (estado) {
			case SingletonConstants.ESTADO_PEDIDO_INICIADO: {
				msj += "<META content=\"text/html; charset=utf-8\" http-equiv=Content-Type>"
					+ "Hola " + usuario.getNombre() + "!<br> "
					+ "Gracias por utilizar nuestros servicios.<br> "
					+ "Su pedido ha sido iniciado y le estaremos informando el progreso del mismo.<br> "
					+ SingletonConstants.ENTITY_NAME + "<br><br>";
				break;				
			}
			
			case SingletonConstants.ESTADO_PEDIDO_RECHAZADO: {
				msj += "<META content=\"text/html; charset=utf-8\" http-equiv=Content-Type>"
					+ usuario.getNombre() + ":<br> "
					+ "Lamentamos informarle que su pedido ha sido rechazado por el comercio.<br> "
					+ "Esto puede deberse a diferentes motivos, ajenos a " + SingletonConstants.ENTITY_NAME + ".<br> "
					+ "Por favor, ante cualquier reclamo, póngase en contacto con nosotros.<br> "
					+ "Y no dude en consultar otros comercios para realizar su pedido.<br> "
					+ SingletonConstants.ENTITY_NAME + "<br><br>";
				break;				
			}

			case SingletonConstants.ESTADO_PEDIDO_RECEPCIONADO: {
				msj += "<META content=\"text/html; charset=utf-8\" http-equiv=Content-Type>"
					+ usuario.getNombre() + ":<br> "
					+ "Su pedido ha sido recepcionado y se encuentra en preparación.<br> "
					+ "Le avisaremos cuando esté listo.<br> "
					+ SingletonConstants.ENTITY_NAME + "<br><br>";
				break;				
			}
				
			case SingletonConstants.ESTADO_PEDIDO_LISTO: {
				msj = usuario.getNombre()+ ": su pedido está listo!<br> "
					+ "Que lo disfrutes!<br> "
					+ "Y esperamos que vuelvas a elegirnos pronto, Gracias!<br> "	
					+ SingletonConstants.ENTITY_NAME + "<br><br>";
				break;				
			}

			default:
				break;
			}		
			
			if(aclaraciones != null && !aclaraciones.equals("")) {
				msj += "Aclaraciones extras: " + aclaraciones;
			}
			
			//BodyPart adjunto = new MimeBodyPart();
			//adjunto.setDataHandler(new DataHandler(new FileDataSource("C:/Users/Ever/git/ProyectoFinal/ProyectoFinal/src/main/webapp/resources/img/Logo-horizontal.png")));
			
			MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText(msj, "UTF-8", "html");
	        
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	        //multipart.addBodyPart(adjunto);
	        message.setContent(multipart);
	                  
			Transport t = mailsession.getTransport("smtp");
			t.connect("tastyfoodnotification@gmail.com", "delivery.system"); 			
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}
		catch (MessagingException me)
		{
		    me.printStackTrace();		
		}
	}

}
