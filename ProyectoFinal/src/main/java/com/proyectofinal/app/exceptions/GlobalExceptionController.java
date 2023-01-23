package com.proyectofinal.app.exceptions;

import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.proyectofinal.app.util.SingletonConstants;

@ControllerAdvice
public class GlobalExceptionController {

	private static final Logger logger = Logger
			.getLogger(GlobalExceptionController.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e,String msj) {
	    	
		ModelAndView model = new ModelAndView("error");
		model.addObject("error", msj);
		logger.error("", e);

		return model;
	}

	@ExceptionHandler(DataAccessException.class)
	public ModelAndView handleDataAccessException(DataAccessException e) {
	    	String msj = "Ha ocurrido un error (DataAccessException). Si el error persiste, contactarse con " + SingletonConstants.ENTITY_NAME + ".";
		return this.handleException(e,msj);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView handleConstraintViolationException(
			ConstraintViolationException e) {
	    String msj = "Ha ocurrido un error (ConstraintViolationException). Si el error persiste, contactarse con " + SingletonConstants.ENTITY_NAME + ".";
		return this.handleException(e,msj);
	}

	@ExceptionHandler(NotYetImplementedException.class)
	public ModelAndView handleNotYetImplementedException(
			NotYetImplementedException e) {

		return new ModelAndView("messages/en-construccion");
	}

}

