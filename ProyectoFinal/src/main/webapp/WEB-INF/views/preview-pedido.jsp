<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="<c:url value="/resources/css/materialize.css" />"
	type="text/css" rel="stylesheet" media="screen,projection" />
<link href="<c:url value="/resources/css/style.css" />" type="text/css"
	rel="stylesheet" media="screen,projection" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/usuarios.css" />" />
<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- Botón cerrar en mensajes de error -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
#saboresModal {
	width: 30% !important
}
</style>


<title>Preview-Pedido</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">

			<div class="container">
				<div class="col s12">
					<c:if test="${error != null}">
						<div id="card-alert"
							class="card red lighten-5 alert alert-info alert-dismissable">
							<div class="card-content red-text">
								<p>${error}</p>
							</div>
						</div>
					</c:if>
					<c:if test="${success != null}">
						<div id="card-alert"
							class="card green lighten-5 alert alert-info alert-dismissable">
							<div class="card-content green-text">
								<p>${success}</p>
							</div>
						</div>
					</c:if>
				</div>

				<c:if test="${carrito.size()!= 0}">

					<form:form method="POST"
						action="${pageContext.request.contextPath}/pedidos-new"
						modelAttribute="pedido" id="frmPedido" name="frmPedido">
						<div class="col s12">
							<div class="material-table">
								<div class="table-header">
									<h5 id="comercio">
										${pedido.comercio.descripcion} - Paso 2/2
										<form:hidden path="comercio.idComercio" />
									</h5>
								</div>
								<div class="table-header red lighten-4">
									<h6 class="flow-text">
										<i class="material-icons">shopping_cart</i>&nbsp;&nbsp;Productos
										añadidos al carrito
									</h6>
									<div class="actions">
										<a href="#"
											class="search-toggle waves-effect btn-flat nopadding"><i
											class="material-icons">search</i></a>
									</div>
								</div>
								<table id="tableData"
									class="dataTable no-footer bordered highlight grey lighten-4">
									<thead>
										<tr>
											<th style="width: 5%;">Nro</th>
											<th style="width: 30%;">Descripción</th>
											<th>Categoría</th>
											<th>Precio unitario ($)</th>
											<th class="center" style="width: 20%;">Adicional</th>
											<th class="center">Cantidad</th>
											<th>&nbsp;&nbsp;Subtotal ($)</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="item" value="0" />
										<c:forEach items="${pedido.items}" var="item" varStatus="i">
											<tr>
												<td>
													<!-- Item --> <form:hidden path="items[${i.index}].nroItem"
														value="${item.nroItem}" /> <form:hidden
														path="items[${i.index}].idItem" value="${item.idItem}" />
													<form:hidden path="items[${i.index}].cantidad"
														value="${item.cantidad}" /> <form:hidden
														path="items[${i.index}].aclaraciones"
														value="${item.aclaraciones}" /> <form:hidden
														path="items[${i.index}].precio" value="${item.precio}" />
													<form:hidden path="items[${i.index}].subtotal"
														value="${item.subtotal}" /> <!-- Producto --> <form:hidden
														path="items[${i.index}].producto.idProducto"
														value="${item.producto.idProducto}" /> <form:hidden
														path="items[${i.index}].producto.descripcion"
														value="${item.producto.descripcion}" /> <form:hidden
														path="items[${i.index}].producto.categoria.idCategoria"
														value="${item.producto.categoria.idCategoria}" /> <form:hidden
														path="items[${i.index}].producto.categoria.descripcion"
														value="${item.producto.categoria.descripcion}" /> <form:hidden
														path="items[${i.index}].producto.comercio.idComercio"
														value="${item.producto.comercio.idComercio}" /> <!-- Salsa -->
													<form:hidden path="items[${i.index}].salsaItem.idSalsa"
														value="${item.salsaItem.idSalsa}" /> <form:hidden
														path="items[${i.index}].salsaItem.descripcion"
														value="${item.salsaItem.descripcion}" /> <form:hidden
														path="items[${i.index}].salsaItem.precio"
														value="${item.salsaItem.precio}" /> <!-- Sabores --> <c:set
														var="sabor" value="0" /> <c:forEach
														items="${item.saboresItem}" var="sabor" varStatus="s">
														<form:hidden path="items[${i.index}].sabores[${s.index}]" />
													</c:forEach> <!-- Pedido --> <form:hidden
														path="items[${i.index}].pedido.direccion"
														value="${item.pedido.direccion}" /> <form:hidden
														path="items[${i.index}].pedido.nro"
														value="${item.pedido.nro}" /> <form:hidden
														path="items[${i.index}].pedido.piso"
														value="${item.pedido.piso}" /> <form:hidden
														path="items[${i.index}].pedido.depto"
														value="${item.pedido.depto}" /> <form:hidden
														path="items[${i.index}].pedido.observaciones"
														value="${item.pedido.observaciones}" /> <form:hidden
														path="items[${i.index}].pedido.conEnvio"
														value="${item.pedido.conEnvio}" /> ${item.nroItem}
												</td>
												<td>${item.producto.descripcion}</td>
												<td>${item.producto.categoria.descripcion}</td>
												<td>${item.precio}</td>
												<td><c:if
														test="${item.producto.categoria.descripcion == 'Helados'}">
														<a class="waves-effect waves-light btn red lighten-5"
															href="#saboresModal_${item.nroItem}">Sabores</a>

														<!--  DropDown List para mostrar sabores
							
							  <a class='dropdown-button btn red lighten-5' href="#" data-activates='dropdownSabores'>Sabores &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
  							  <ul id='dropdownSabores' class='dropdown-content'>
  							  	<c:set var="j" value="0" />          									
							  	<c:forEach items="${item.saboresItem}" var="j" varStatus="s">
    								<li class="small"><a>${j.sabor.descripcion}</a></li>
    							</c:forEach>	
  							  </ul>
  							  
  							 -->

														<!-- Modal Structure -->
														<div id="saboresModal_${item.nroItem}" class="modal">
															<div class="modal-content">
																<h5>Sabores elegidos:</h5>
																<c:set var="j" value="0" />
																<c:forEach items="${item.saboresItem}" var="j"
																	varStatus="s">
																	<li class="small"><a>${j.sabor.descripcion}</a></li>
																</c:forEach>
															</div>
															<div class="modal-footer">
																<a
																	class="modal-action modal-close waves-effect waves-green btn-flat">OK</a>
															</div>
														</div>
													</c:if> <c:if
														test="${item.producto.categoria.descripcion == 'Pastas'}">
														<a class='dropdown-button btn red lighten-5' href="#"
															data-activates='dropdownSalsa'>Salsa&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
														<ul id='dropdownSalsa' class='dropdown-content'>
															<li><a>${item.salsaItem.descripcion}
																	$${item.salsaItem.precio}</a></li>
														</ul>
													</c:if></td>
												<td>${item.cantidad}</td>
												<td>${item.subtotal}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<table id="tableData"
									class="dataTable no-footer bordered highlight">
									<thead>
									</thead>
									<tbody class="grey lighten-4">
										<c:if test="${pedido.conEnvio == 'S' }">
											<tr>
												<td style="width: 20%;">
													<h6>&nbsp;&nbsp;&nbsp;&nbsp; Costo de envio:</h6>
												</td>
												<td>
													<h6 id="costoEnvio">
														<b>$${pedido.comercio.costoEnvio}
														</b>
													</h6>
												</td>
											</tr>
										</c:if>
										<tr class="red lighten-4">
											<td style="width: 20%;"><h5>&nbsp;&nbsp; Total:</h5> <form:hidden
													path="total" id="total" /></td>
											<td>
												<h5 id="totalPed">$${pedido.total}</h5>
											</td>
										</tr>
									</tbody>
								</table>
								<br>

								<form:hidden path="conEnvio" id="conEnvio"
									value="${pedido.conEnvio}" />
								<form:hidden path="costoEnvio" id="costoEnvio"
									value="${pedido.costoEnvio}" />
								<c:choose>
									<c:when test="${pedido.conEnvio == 'S'}">
										<div id="detalleEnvio" class="row col s10">
											<div class="row">
												<fieldset class="grey lighten-5">
													<legend>
														<b>Detalles del envio</b>
													</legend>
													<div class="input-field col s6">
														<form:input path="direccion" id="direccion" type="text"
															class="black-text" readOnly="readOnly" />
														<label for="direccion">Dirección</label>
													</div>
													<div class="input-field col s2">
														<form:input path="nro" id="nro" type="text"
															class="black-text" readOnly="readOnly" />
														<label for="nro">Nro</label>
													</div>
													<div class="input-field col s2">
														<form:input path="piso" id="piso" type="text"
															class="black-text" readOnly="readOnly" />
														<label for="piso">Piso</label>
													</div>
													<div class="input-field col s2">
														<form:input path="depto" id="depto" type="text"
															class="black-text" readOnly="readOnly" />
														<label for="depto">Depto</label>
													</div>
												</fieldset>
											</div>
										</div>

										<c:if test="${pedido.observaciones != ''}">
											<div class="row" id="observacionGeneral">
												<div class="input-field col s12">
													<form:textarea path="observaciones" id="observaciones"
														name="observaciones"
														class="materialize-textarea black-text"
														readOnly="readOnly" />
													<label for="observaciones">Observaciones</label>
												</div>
											</div>
										</c:if>

									</c:when>
									<c:otherwise>
										<c:if test="${pedido.comercio.descripcion != null}">
											<div style="text-align: center">
												<p style="color: red; font: bold;">El pedido no será
													enviado, usted lo retira.</p>
											</div>
											<br>
										</c:if>
									</c:otherwise>
								</c:choose>

							</div>
						</div>
						<div class="row center">
							<a class="waves-effect waves-light btn grey lighten-2 black-text"
								onClick="goBack()">Volver</a>
							<button type="submit"
								class="waves-effect waves-light btn orange accent-3">Confirmar
								Pedido!</button>
						</div>
					</form:form>

				</c:if>
				<c:if test="${carrito.size()==0}">
					<div style="text-align: center">
						<p style="color: red; font: bold;">No existen productos
							añadidos al carrito</p>
					</div>
				</c:if>
				<br>
			</div>

		</div>
		<br>
	</div>

	<!-- Modal Structure -->
	<div id="modal1" class="modal">
		<form:form method="POST" modelAttribute="usuario"
			action="usuarios-new" id="frm" name="frm">

			<div class="modal-content">
				<h4>Regístrate</h4>
				<p>Es totalmente gratis!</p>

				<div class="row col s12">
					<div class="row">
						<div class="input-field col s6">
							<form:input path="nombre" id="nombre" type="text"
								class="validate" maxlength="30"
								onkeypress="return soloLetras(event)" autofocus="autofocus" />
							<label for="nombre">Nombre</label>
						</div>
						<div class="input-field col s6">
							<form:input path="apellido" id="apellido" type="text"
								class="validate" maxlength="30"
								onkeypress="return soloLetras(event)" />
							<label for="apellido">Apellido</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s6">
							<form:input path="telefono" id="telefono" type="tel"
								class="validate" maxlength="15"
								onKeyPress="return soloNumeros(event)" />
							<label for="telefono">Telefono</label>
						</div>
						<div class="input-field col s6">
							<form:input path="celular" id="celular" type="tel"
								class="validate" maxlength="15"
								onKeyPress="return soloNumeros(event)" />
							<label for="celular">Celular</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<form:input path="email" id="email" type="email" class="validate"
								maxlength="30" />
							<label for="email" data-error="incorrecto"
								data-success="correcto">Email</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s6">
							<form:input path="nombreUsuario" id="username" type="text"
								class="validate" maxlength="30" />
							<label for="username">Nombre de usuario</label>
						</div>
						<div class="input-field col s6">
							<form:input path="contrasena" id="password" type="password"
								class="validate" maxlength="15" />
							<label for="password">Contraseña</label>
						</div>
					</div>
					<div class="row">
						<p>Recibir notificacion por email</p>
						<div class="input-field col s3">
							<p>
								<form:radiobutton path="notificaciones" value="S" name="grupoA"
									id="notificacionSi" class="with-gap" />
								<label for="notificacionSi">Si</label>
							</p>
						</div>
						<div class="input-field col s3">
							<p>
								<form:radiobutton path="notificaciones" value="N" name="grupoA"
									id="notificacionNo" class="with-gap" />
								<label for="notificacionNo">No</label>
							</p>
						</div>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<a class="modal-action modal-close waves-effect waves-red btn-flat"
					href="#!">Cancelar</a>
				<button
					class="modal-action modal-close waves-effect waves-green btn-flat"
					type="submit">Guardar</button>
			</div>
		</form:form>
	</div>

	<div id="modalLogin" class="modal" style="width: 35%; height: 60%;">
		<form method="POST" action="<%=request.getContextPath()%>/login"
			id="frmLogin" name="frmLogin">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="modal-content">
				<h4>Login</h4>
				<div class="row col s20">
					<div class="row">
						<div class="input-field col s12">
							<input id="username" name="username" type="text" class="validate"
								maxlength="30" autofocus="autofocus" /> <label for="username">Nombre
								de usuario</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<input id="password" name="password" type="password"
								class="validate" maxlength="15" /> <label for="password">Contraseña</label>
						</div>
					</div>
					<div class="modal-footer col s10">
						<button
							class="modal-action modal-close waves-effect waves-green btn-flat"
							type="submit">Ingresar</button>
						<a
							class="modal-action modal-close waves-effect waves-red btn-flat"
							href="#!">Cancelar</a>
					</div>
				</div>
			</div>

		</form>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>
	<script src="<c:url value="resources/js/turn.js" />"></script>
	<script
		src="<c:url value="resources/js/material.sortable.datatable.js" />"></script>
	<script src="<c:url value="resources/js/dataTables.materialize.js" />"></script>

	<script type="text/javascript">
  
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
  
  $('#modal1').modal('close');
  $('#modal1').modal('open');
  
  $('#modalLogin').modal('close');
  $('#modalLogin').modal('open');  
  
  $(document).ready(function(){
	    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
	    $('.modal').modal();
  }); 
			
  </script>

</body>
</html>