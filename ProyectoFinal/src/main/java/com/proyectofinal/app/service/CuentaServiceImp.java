package com.proyectofinal.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.proyectofinal.app.dao.ComercioDao;
import com.proyectofinal.app.dao.CuentaDao;
import com.proyectofinal.app.dao.PedidoDao;
import com.proyectofinal.app.dto.CuentaDto;
import com.proyectofinal.app.model.Comercio;
import com.proyectofinal.app.model.Cuenta;
import com.proyectofinal.app.model.Pedido;
import com.proyectofinal.app.util.ClassToDto;

@Service("cuentaService")
@Transactional
public class CuentaServiceImp implements CuentaService {
	
	@Autowired
	PedidoDao daoPedido;
	
	@Autowired
	ComercioDao daoComercio;
	
	@Autowired
	CuentaDao daoCuenta;
	
	@Override
	public List<CuentaDto> generateCuentas(Double comision, Date fechaDesde, Date fechaHasta) 
			throws DataAccessException, Exception {
		
		List<CuentaDto> cuentas = new ArrayList<CuentaDto>();
		
		List<Comercio> comercios = this.daoComercio.findAllWithoutDate();
		CuentaDto cuenta;
		Double montoTotal = 0.0;
		Double montoComision = 0.0;
		for(Comercio co: comercios) {
			cuenta = new CuentaDto();
			cuenta.setFechaDesde(fechaDesde);
			cuenta.setFechaHasta(fechaHasta);
			cuenta.setComisionPorcentual(comision);
			cuenta.setComercio(co);
			List<Pedido> pedidos = this.daoPedido
								   .findPedidosDevengables(co.getIdComercio(), fechaDesde, fechaHasta);
			cuenta.setPedidos(pedidos);
			for(Pedido p: pedidos) {				
				montoTotal += p.getTotal() - p.getCostoEnvio();
			}
			cuenta.setMontoTotal(montoTotal);
			/*
			 * Calculo porcentaje a partir de total.
			 */
			montoComision = (montoTotal * comision) / 100;
			cuenta.setComision(montoComision);
			cuentas.add(cuenta);
			montoTotal = 0.0;
			montoComision = 0.0;
		}
		
		return cuentas;
	}

	@Override
	public void saveAllCuentas(List<CuentaDto> cuentas) throws DataAccessException, Exception {
		
		Cuenta cuenta;
		for(CuentaDto c : cuentas) {
			cuenta = new Cuenta();
			cuenta = ClassToDto.dtoToCuenta(c);
			this.daoCuenta.save(cuenta);
		}
	}
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<Cuenta> findByComercio(Long idComercio) throws DataAccessException, Exception {
		
    	List<Cuenta> cuentas = this.daoCuenta.findByComercio(idComercio);
    	for(Cuenta c: cuentas) {
    		Hibernate.initialize(c.getPedidosCuentas());
    	}
    	return cuentas;
	}
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, 
			   rollbackFor = DataAccessException.class)
	public List<CuentaDto> findDtoByComercio(Long idComercio) throws DataAccessException, Exception {
		
    List<CuentaDto> dtos = new ArrayList<CuentaDto>();	
 	List<Cuenta> cuentas = this.daoCuenta.findByComercio(idComercio);
 	if(cuentas != null) {
 	 	for(Cuenta c: cuentas) {
 	 		dtos.add(ClassToDto.cuentaToDto(c));
 	 	}		
 	}
 	return dtos;
	}

	
}
