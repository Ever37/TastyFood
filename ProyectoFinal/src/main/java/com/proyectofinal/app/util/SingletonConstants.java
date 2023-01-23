package com.proyectofinal.app.util;

public final class SingletonConstants {
	
	public static final String ENTITY_NAME = "TastyFood";

	public static final String GRUPO_DEFAULT_ADMIN = "Administrador";	
	public static final String GRUPO_DEFAULT_CLIENTE = "Cliente";
	public static final String GRUPO_DEFAULT_VENDEDOR = "Vendedor";
	
	public static final String ESTADO_PEDIDO_INICIADO = "Iniciado";
	public static final String ESTADO_PEDIDO_RECHAZADO = "Rechazado";	
	public static final String ESTADO_PEDIDO_RECEPCIONADO = "Recepcionado";
	public static final String ESTADO_PEDIDO_DESECHADO = "Desechado";
	public static final String ESTADO_PEDIDO_LISTO = "Listo";
	public static final String ESTADO_PEDIDO_ENTREGADO = "Entregado";
	
	public static final String[] dayOfWeek = new String[]{
													"Domingo",
													"Lunes",
													"Martes",
													"Miercoles",
													"Jueves",
													"Viernes",
													"Sábado"};
	public static final String MESSAGE_COMERCIO_CERRADO 
	= "No puede realizar un pedido en este momento. Fuera de la franja horaria del comercio.";
	
	public static final String MESSAGE_CARRITO_VACIO
	= "El carrito se encuentra vacio. Cargue al menos un producto para continuar.";
	
	public static final String MESSAGE_PRODUCTOS_COMERCIO
	= "No puede agregar productos de diferentes comercios al carrito.";
	
	public static final String MESSAGE_COMPRA_MINIMA
	= "La compra m&iacute;nima del comercio para realizar envios es de ";
}
