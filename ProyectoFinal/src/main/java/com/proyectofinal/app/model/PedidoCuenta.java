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

@Entity
@Table(name = "pedido_cuenta", catalog = "proyecto_final")
public class PedidoCuenta {

		/*
		 * Principales
		 */
		private Long  idPedidoCuenta;
		
		/*
		 * Relaciones
		 */		
		private Pedido pedido;
		private Cuenta cuenta;
		
	    @Id
	    @GeneratedValue(strategy = IDENTITY)
	    @Column(name = "id_pedido_cuenta", unique = true, nullable = false)
		public Long getIdPedidoCuenta() {
			return idPedidoCuenta;
		}
		public void setIdPedidoCuenta(Long idPedidoCuenta) {
			this.idPedidoCuenta = idPedidoCuenta;
		}
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "id_pedido")
		public Pedido getPedido() {
			return pedido;
		}
		public void setPedido(Pedido pedido) {
			this.pedido = pedido;
		}
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "id_cuenta")
		public Cuenta getCuenta() {
			return cuenta;
		}
		public void setCuenta(Cuenta cuenta) {
			this.cuenta = cuenta;
		}
		
}
