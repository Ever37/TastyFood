package com.productos.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.productos.app.service.ProductoService;

@Controller
@RequestMapping(value = "/")
public class MainController {
	
	@Autowired
	ProductoService serviceProducto;

	/**
	 * Muestra la pagina de inicio
	 *
	 */
	@RequestMapping(value = { "/productos" }, method = RequestMethod.GET)
	public ModelAndView main(ModelAndView model){
		model.setViewName("index");
		return model;
	}

}
