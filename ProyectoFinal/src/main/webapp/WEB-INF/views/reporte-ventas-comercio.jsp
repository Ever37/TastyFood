<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" type="text/css"
	rel="stylesheet" media="screen,projection" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/usuarios.css" />" />
<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- Recurso externo para las fechas -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.99.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.99.0/js/materialize.min.js"></script>


<style type="text/css">
.modal {
	overflow: visible;
	width: 50% !important;
}

.error {
	color: red;
}
</style>

<title>ReporteMisPedidos</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">
			<br>

			<div class="container">
				<form:form modelAttribute="busqueda" class="col s12"
					id="formBusqueda" method="POST" role="form">
					<div class="row">
						<div class="input-field col s6">
							<label for="fechaDesde">Fecha desde</label>
							<form:input id="fechaDesde" path="fechaDesde" type="date"
								class="datepicker" />
						</div>
						<div class="input-field col s6">
							<label for="fechaHasta">Fecha hasta</label>
							<form:input id="fechaHasta" path="fechaHasta" type="date"
								class="datepicker" />
						</div>

						<div class="col s6">
							<label id="fechaDesde-error" class="error"> </label>
						</div>
						<div class="col s6">
							<label id="fechaHasta-error" class="error"> </label>
						</div>

					</div>
					<div class="row">
						<div class="row center">
							<button onClick="return validarAlta();"
								class="waves-effect waves-light btn orange accent-3">Buscar</button>
						</div>
					</div>
					<br>
					<br>
				</form:form>
				<div class="col s12">
					<div class="material-table">
						<div align="center" id="preload" style="display: none;">
							<div class="progress">
								<div class="indeterminate"></div>
							</div>
						</div>
						<div class="table-header">
							<span class="table-title">Mis Ventas - Desde <fmt:formatDate
									pattern="dd-MM-yyyy" value="${fechaDesde}" /> hasta <fmt:formatDate
									pattern="dd-MM-yyyy" value="${fechaHasta}" />
							</span>

							<div class="actions">
								<a href="#"
									class="search-toggle waves-effect btn-flat nopadding"><i
									class="material-icons">search</i></a>
							</div>
						</div>
						<table id="tableData"
							class="dataTable no-footer bordered highlight"
							style="text-align: left;">
							<thead>
								<tr>
									<th width="5%">Nro.</th>
									<th width="18%">Fecha y hora</th>
									<th width="15%">Nombre y apellido</th>
									<th width="15%">Dirección</th>
									<th width="9%">Envio</th>
									<th class="center" width="24%">Estado</th>
									<th width="10%">Total</th>
									<th class="center" width="10%">Detalle</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="ped" value="0" />
								<c:forEach items="${misVentas}" var="ped">
									<tr>
										<td>${ped.nroPedido}</td>
										<td><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
												value="${ped.fechaHora}" /></td>
										<td>${ped.usuario.nombre} ${ped.usuario.apellido}</td>
										<td>${ped.direccion} ${ped.nro} ${ped.piso} ${ped.depto}</td>
										<td>
										<c:choose>
											<c:when test="${ped.conEnvio == 'S'}">
												Si
											</c:when>
											<c:otherwise>
												No
											</c:otherwise>
										</c:choose>
										<td>
											<div id="cEstado_${ped.idPedido}">
												<div id="chip_${ped.idPedido}"
													class="chip ${ped.ultimoEstado.color}">
													<b>${ped.ultimoEstado.descripcion}</b>
												</div>

												<c:if test="${ped.estadosPermitidos.size() > 0}">
													<a id="modalEstados_${ped.idPedido}"
														class="btn-floating btn-small waves-effect waves-light indigo"
														onClick="javascript:guardarIdPedido(${ped.idPedido});"
														href="#estadosModal_${ped.idPedido}"> <i
														class="material-icons">mode_edit</i></a>
												</c:if>

												<input type="hidden" id="hiddenPedido" /> <input
													type="hidden" id="hiddenEstado" /> <input type="hidden"
													id="hiddenAclaraciones" />

												<!-- Modal Structure -->
												<div id="estadosModal_${ped.idPedido}" class="modal"
													data-backdrop="static" data-keyboard="false">

													<div class="modal-content" data-backdrop="static"
														data-keyboard="false">

														<div id="datosModal_${ped.idPedido}">
															<h5>Estado de pedido:</h5>

															<div id="divSelect_${ped.idPedido}">

																<select id="estados_${ped.idPedido}"
																	onChange="guardarIdEstado(this.value);">
																	<option selected disabled>--Seleccione un estado--</option>
																	<c:forEach items="${ped.estadosPermitidos}" var="e">
																		<option id="option_${ped.idPedido}"
																			value="${e.idEstadoPedido}">${e.descripcion}</option>
																	</c:forEach>
																</select>

															</div>

														</div>

														<div class="row">
															<div class="input-field col s12">
																<textarea id="aclaraciones" name="aclaraciones"
																	class="materialize-textarea" maxlength="100"
																	data-length="100"
																	onblur="guardarAclaracion(this.value);"></textarea>
																<label for="aclaraciones">Aclaraciones</label>
															</div>
														</div>

													</div>

													<div class="modal-footer">
														<a onClick="return updateEstadoPedido();"
															class="modal-action modal-close waves-effect waves-green btn-flat">OK</a>
														<a
															class="modal-action modal-close waves-effect waves-red btn-flat">Cancelar</a>
													</div>

												</div>
											</div>
										</td>
										<td>${ped.total}</td>
										<td class="center"><sec:authorize
												access="hasAnyAuthority('ADMIN', 'RE_VENTAS_SU', 'RE_VENTAS_CU', 'RE_VENTAS_RU')">

												<c:choose>
													<c:when
														test="${ped.ultimoEstado.descripcion != 'Iniciado' 
								&& ped.ultimoEstado.descripcion != 'Rechazado'}">
														<a id="enlace"
															href="<c:url value='/pedidos-${ped.idPedido}-view'/>"
															target="_blank"
															class="btn-floating btn-medium waves-effect waves-light light-blue"><i
															class="material-icons">remove_red_eye</i></a>
													</c:when>
													<c:otherwise>
														<a
															class="btn-floating btn-medium waves-effect waves-light disabled">
															<i class="material-icons">remove_red_eye</i>
														</a>
													</c:otherwise>

												</c:choose>

											</sec:authorize></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
					<div class="row">
							<div class="input-field col s2">
								<label for="total">Total</label> <input id="totalVentas"
									value="${totalVentas}" type="text" readOnly="readOnly"
									class="black-text" />
							</div>
					</div>
					<!--  
	        <c:if test="${misVentas.size() > 0}">
	        <div class="row">
        	    <div class="row center">
  	 				<button type="submit" class="waves-effect waves-light btn orange accent-3">Generar pdf</button>
  				</div>
        	</div>
        	</c:if>
        	-->
				</div>
				<span class="details"> </span> <br>
			</div>

		</div>
	</div>
	<br>
	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<!--  Scripts-->
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>
	<script src="<c:url value="resources/js/turn.js" />"></script>
	<script
		src="<c:url value="resources/js/material.sortable.datatable.js" />"></script>
	<script src="<c:url value="resources/js/dataTables.materialize.js" />"></script>

	<script type="text/javascript">
	
	window.onload = function() {
		document.getElementById("totalVentas").focus();
	}

  function validarAlta() {
		
		document.getElementById("fechaDesde-error").textContent = "";
		document.getElementById("fechaHasta-error").textContent = "";
		
	  	var rta = true;
		
		var msj = "";
		var desde = document.getElementById("fechaDesde").value;
		var hasta = document.getElementById("fechaHasta").value;
	
		if(desde == "")
		{
			document.getElementById("fechaDesde-error").style.display = "block";
			msj = "Este campo es obligatorio";
			document.getElementById("fechaDesde-error").textContent = msj;
			rta = false;
		} 
		
		if(hasta == ""){
			document.getElementById("fechaHasta-error").style.display = "block";
			msj = "Este campo es obligatorio";
			document.getElementById("fechaHasta-error").textContent = msj;
			rta = false;
		}
		
		if(rta && desde > hasta) {
				document.getElementById("fechaDesde-error").style.display = "block";
				msj = "La fecha hasta debe ser mayor a la fecha desde";
				document.getElementById("fechaDesde-error").textContent = msj;
				rta = false;
		}
		
		if(rta) {
			document.getElementById("formBusqueda").submit();
		} else {
			return rta;
		}		
	}
  
  function guardarAclaracion(aclaraciones) {
	  document.getElementById("hiddenAclaraciones").value = aclaraciones;
  }
  
  function guardarIdEstado(idEstado) {
	  document.getElementById("hiddenEstado").value = idEstado;
  }
  
  function guardarIdPedido(idPedido) {
	  //Recupero el idPedido del pedido que elegí para cambiarle el estado.
	  document.getElementById("hiddenPedido").value = idPedido;
  }
  
  function updateEstadoPedido() {
	
	  var estado = document.getElementById("hiddenEstado").value;	
	  if(estado == "") {
		  Materialize.toast('No se ha cambiado el estado del pedido', 3000, 'rounded')
		  return false;
	  }
	  
	  var pedido = document.getElementById("hiddenPedido").value;
	  var aclaracion = document.getElementById("hiddenAclaraciones").value;
	  	
	  $.post('ActualizarEstadoPedidoServlet', {
				idPedido : pedido,
				idEstado : estado,
				aclaraciones : aclaracion,
	  }, function(responseJson) {
				$('#chip_'+pedido).remove();
				
		        $("div#cEstado_"+pedido)
	        	.prepend($('<div>', {id: 'chip_'+pedido , class:  'chip '+ responseJson.ultimoEstado["color"]})
	        			.append($('<b>', {text: responseJson.ultimoEstado["descripcion"]}))
	        	);	
		        
		        /*
		        * Construir modal
		        */
		        var estadoActual = responseJson.ultimoEstado["descripcion"];
		        switch(estadoActual) {
		        	case "Rechazado": {
		        		$('#modalEstados_'+pedido).remove();	        		
		        		break;}
		        	case "Recepcionado": {
		        		document.getElementById("preload").style.display = "block";		        		
		        		location.reload(true);	        				        		
		        		break;}
		        	case "Listo": {	
		        		document.getElementById("preload").style.display = "block";
		        		location.reload(true);   		
		        		/*
		        		$('#divSelect_'+pedido).remove();
		        		$('#datosModal_'+pedido)
		        		.append($('<div>', {id: 'divSelect_'+pedido})
		        				.append($('<select>', {id: 'estados_'+pedido, onChange: 'guardarIdEstado(this.value);'})
		        					.append($('<option>', {selected: 'selected', disabled: 'disabled', text: '--Seleccione un estado--'}))				        				
		        				)				
		        		);
		        		*/        		
		        		break;}
		        	case "Desechado": {
		        		$('#modalEstados_'+pedido).remove();
		        		break;}
		        	case "Entregado": {
		        		$('#modalEstados_'+pedido).remove();
		        		break;}
		        }		       		        				
	  });
	  
  }
  
  $('.datepicker').pickadate({
	    selectMonths: true, // Creates a dropdown to control month
	    selectYears: 15 // Creates a dropdown of 15 years to control year
	  });
  
  $(document).ready(function() {
	  $('#tableData').dataTable({
	    "oLanguage": {
	      "sZeroRecords": "No se encontraron resultados",
	      "sStripClasses": "",
	      "sSearch": "",
	      "sSearchPlaceholder": "Filtrar por alguna columna",
	      "sInfo": "_START_ -_END_ of _TOTAL_",
	      "sLengthMenu": '<span>Filas por página:</span><select class="browser-default">' +
	      	'<option value="5">5</option>' +
	        '<option value="10">10</option>' +
	        '<option value="20">20</option>' +
	        '<option value="30">30</option>' +
	        '<option value="40">40</option>' +
	        '<option value="50">50</option>' +
	        '<option value="-1">All</option>' +
	        '</select></div>'
	    },
	    bAutoWidth: false
	  });
	});
  
  function goBack() {
	    window.history.back();
	}
  
  function mostrarSideNav() {
	 // Show sideNav
	 $('.button-collapse').sideNav('show');
  }

  function ocultarSideNav() {
	// Hide sideNav
	$('.button-collapse').sideNav('hide');		
  }
  
  $(document).ready(function(){
	    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
	    $('.modal').modal();
	}); 
  
  $(document).ready(function() {
	    $('select').material_select();
  });
  
  </script>
	<script type="text/javascript">
	$(function () {
		  $("#fechaDesde").datepicker("option", { dateFormat: "yyyy-mm-dd" });
		  $("#fechaHasta").datepicker("option", { dateFormat: "yyyy-mm-dd" });
		});
	
  </script>

</body>
</html>