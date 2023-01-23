package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "sabores_de_item", catalog = "proyecto_final")
public class SaboresDeItem implements java.io.Serializable {

	/*
	 * Principales
	 */
	private Long idSaborItem;
	private Sabor sabor;
	private ItemPedido itemPedido;
	
	public SaboresDeItem() {
		
	}
	
	public SaboresDeItem(Sabor sabor, ItemPedido itemPedido) {
		this.sabor = sabor;
		this.itemPedido = itemPedido;
	}
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_sabores_de_item", unique = true, nullable = false)
	public Long getIdSaborItem() {
		return idSaborItem;
	}
	public void setIdSaborItem(Long idSaborItem) {
		this.idSaborItem = idSaborItem;
	}
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sabor", nullable = false)	
	public Sabor getSabor() {
		return sabor;
	}
	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}
    	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_item_de_pedido", nullable = false)	
	public ItemPedido getItemPedido() {
		return itemPedido;
	}
	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}
		
}
