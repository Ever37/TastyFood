package com.proyectofinal.app.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import com.proyectofinal.app.core.security.CustomUserDetails;
import com.proyectofinal.app.dto.PedidoDto;

@Component("shoppingCart")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Carrito {

    private CustomUserDetails usuario;
    private List<ItemPedido> itemsDePedido = new ArrayList<ItemPedido>(0);
    private Comercio comercio;
    
    /*
     * Auxiliares
     */
    private PedidoDto pedido;
    
	public CustomUserDetails getUsuario() {
		return usuario;
	}
	public void setUsuario(CustomUserDetails usuario) {
		this.usuario = usuario;
	}
	
	public List<ItemPedido> getItemsDePedido() {
		return itemsDePedido;
	}
	public void setItemsDePedido(List<ItemPedido> itemsDePedido) {
		this.itemsDePedido = itemsDePedido;
	}

	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	
	public PedidoDto getPedido() {
		return pedido;
	}
	public void setPedido(PedidoDto pedido) {
		this.pedido = pedido;
	}

}
