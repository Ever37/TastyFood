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


<title>VerPedido</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">

			<div class="container">
				<div class="col s12"></div>
				<form:form modelAttribute="pedido" id="frmPedido" name="frmPedido">
					<div class="col s12">
						<div class="material-table">
							<div class="table-header">
								<h5 id="comercio">
									${pedido.comercio.descripcion}
									<form:hidden path="comercio.idComercio" />
								</h5>
							</div>
							<div class="table-header red lighten-5">
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
										<th style="width: 4%;">Nro</th>
										<th style="width: 20%;">Descripción</th>
										<th>Precio unitario ($)</th>
										<th class="center" style="width: 20%;">Adicional</th>
										<th>Cantidad</th>
										<th>Subtotal ($)</th>
										<th style="width: 10%;"></th>
									</tr>
								</thead>
								<tbody>
									<c:set var="item" value="0" />
									<c:forEach items="${pedido.items}" var="item" varStatus="i">
										<tr>
											<td>${item.nroItem}</td>
											<td>${item.producto.descripcion}</td>
											<td>${item.precio}</td>
											<td><c:if
													test="${item.producto.categoria.descripcion == 'Helados'}">
													<a class="waves-effect waves-light btn red lighten-5"
														href="#saboresModal_${item.nroItem}">Sabores</a>

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
											<td>
											<c:choose>
													<c:when test="${item.aclaraciones != null && item.aclaraciones !=''}">
													<a href="#aclaracionesModal_${item.nroItem}"
													class="btn-floating btn-medium waves-effect waves-light light-green">
													<i class="material-icons">info_outline</i></a> &nbsp;
													<!-- Modal Structure -->
														<div id="aclaracionesModal_${item.nroItem}" class="modal">
															<div class="modal-content">
																<h5>Aclaraciones:</h5>
																<a>${item.aclaraciones}</a>
															</div>
															<div class="modal-footer">
																<a
																	class="modal-action modal-close waves-effect waves-green btn-flat">OK</a>
															</div>
													</div>
													</c:when>
													<c:otherwise>
														<a class="btn-floating btn-medium waves-effect waves-light grey lighten-2">
														<i class="material-icons">info_outline</i></a> &nbsp;
													</c:otherwise>
											</c:choose>
											</td>
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
													<b>$${pedido.comercio.costoEnvio}</b>
												</h6>
											</td>
										</tr>
									</c:if>
									<tr class="red lighten-5">
										<td style="width: 20%;"><h5>&nbsp;&nbsp; Total:</h5> <form:hidden
												path="total" id="total" /></td>
										<td>
											<h5 id="totalPed">$${pedido.total}</h5>
										</td>
									</tr>
								</tbody>
							</table>
							<br>

							<div class="row">
								<fieldset class="grey lighten-5">
									<legend>
										<b>Cliente</b>
									</legend>
									<div class="input-field col s4">
										<form:input path="usuario.nombre" id="nombre" type="text"
											class="black-text" readOnly="readOnly" />
										<label for="nombre">Nombre</label>
									</div>
									<div class="input-field col s4">
										<form:input path="usuario.apellido" id="apellido" type="text"
											class="black-text" readOnly="readOnly" />
										<label for="apellido">Apellido</label>
									</div>
									
									<sec:authorize 
									access="hasAnyAuthority('ADMIN', 'USUARIOS_SU', 'USUARIOS_CU', 'USUARIOS_RU')">
									<div class="input-field col s4">
										<a id="enlace"
											href="<c:url value='/usuarios-${pedido.usuario.nombreUsuario}-view' />"
											class="btn-floating btn-medium waves-effect waves-light light-blue"
											class=""><i class="material-icons">remove_red_eye</i></a>
									</div>
									</sec:authorize>
									
								</fieldset>
							</div>
						</div>

						<form:hidden path="conEnvio" id="conEnvio"
							value="${pedido.conEnvio}" />
						<c:choose>
							<c:when test="${pedido.conEnvio == 'S'}">
								<div class="row">
									<fieldset class="grey lighten-5">
										<legend>
											<b>Detalles del envio</b>
										</legend>
										<div class="input-field col s5">
											<form:input path="direccion" id="direccion" type="text"
												class="black-text" readOnly="readOnly" />
											<label for="direccion">Dirección</label>
										</div>
										<div class="input-field col s1">
											<form:input path="nro" id="nro" type="text"
												class="black-text" readOnly="readOnly" />
											<label for="nro">Nro</label>
										</div>
										<div class="input-field col s1">
											<form:input path="piso" id="piso" type="text"
												class="black-text" readOnly="readOnly" />
											<label for="piso">Piso</label>
										</div>
										<div class="input-field col s1">
											<form:input path="depto" id="depto" type="text"
												class="black-text" readOnly="readOnly" />
											<label for="depto">Depto</label>
										</div>
									</fieldset>
								</div>

								<c:if test="${pedido.observaciones != ''}">
									<div class="row" id="observacionGeneral">
										<div class="input-field col s12">
											<form:textarea path="observaciones" id="observaciones"
												name="observaciones" class="materialize-textarea black-text"
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
								</c:if>
							</c:otherwise>
						</c:choose>

					</div>

					<div class="row center">
						<a class="waves-effect waves-light btn grey lighten-2 black-text"
							onClick="goBack()">Volver</a>
					</div>
				</form:form>
			</div>
			<br>
		</div>

	</div>
	<br>


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
		$(document)
				.ready(
						function() {
							$('#tableData')
									.dataTable(
											{
												"oLanguage" : {
													"sZeroRecords" : "No se encontraron resultados",
													"sStripClasses" : "",
													"sSearch" : "",
													"sSearchPlaceholder" : "Filtrar por alguna columna",
													"sInfo" : "_START_ -_END_ of _TOTAL_",
													"sLengthMenu" : '<span>Filas por página:</span><select class="browser-default">'
															+ '<option value="5">5</option>'
															+ '<option value="10">10</option>'
															+ '<option value="20">20</option>'
															+ '<option value="30">30</option>'
															+ '<option value="40">40</option>'
															+ '<option value="50">50</option>'
															+ '<option value="-1">All</option>'
															+ '</select></div>'
												},
												bAutoWidth : false
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

		$(document).ready(function() {
			// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
			$('.modal').modal();
		});
	</script>

</body>
</html>