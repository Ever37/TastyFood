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

<!-- Sweet Alert -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/sweetalert.css" />">
<style type="text/css">
.error {
	color: red;
}
</style>
<title>Carrito</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">
			<br>
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
					<c:if test="${alert != null}">
						<div id="card-alert"
							class="card yellow lighten-5 alert alert-info alert-dismissable">
							<div class="card-content black-text">
								<p>${alert}</p>
							</div>
						</div>
					</c:if>
				</div>

					<form:form method="POST" modelAttribute="pedido"
						action="${pageContext.request.contextPath}/preview-pedido"
						id="frmPedido" name="frmPedido">
						<div class="col s12">
							<div class="material-table">
								<div class="table-header">
									<h5 id="comercio">
										<c:if test="${pedido.comercio.idComercio != null}">
										<a
										class="btn-floating halfway-fab waves-effect waves-light orange accent-2"
										href="<c:url value='comercio-${pedido.comercio.idComercio}-menu' />">
										<i class="material-icons">restaurant_menu</i>
										</a>
										</c:if>
										${pedido.comercio.descripcion}
										<c:if test="${pedido.comercio.descripcion != null}">-</c:if>
										Paso 1/2
										<form:hidden path="comercio.idComercio" />
										<form:hidden path="comercio.descripcion" />
										<form:hidden path="comercio.compraMinima" />

									</h5>
								</div>
								<div class="table-header red lighten-4">
									<h6 class="flow-text">
										<i class="material-icons">shopping_cart</i>&nbsp;&nbsp;Productos añadidos al carrito
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
											<th style="width: 20%;">Descripción</th>
											<th>Categoría</th>
											<th>Precio unitario ($)</th>
											<th>Cantidad</th>
											<th>Subtotal ($)</th>
											<th class="center" style="width: 20%;">Opciones</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="item" value="0" />
										<c:forEach items="${pedido.items}" var="item" varStatus="i">
											<tr id="filaItem_${item.nroItem}">
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
												<td>${item.cantidad}</td>
												<td id="subtotal_${item.nroItem}">${item.subtotal}</td>
												<td class="center"><a id="enlace"
													href="<c:url value='/item-${item.nroItem}-pedido-view' />"
													class="btn-floating btn-medium waves-effect waves-light light-blue"
													class=""><i class="material-icons">remove_red_eye</i></a>
													&nbsp; 
													<!--  
													<a
													href="<c:url value='/producto-${item.producto.idProducto}-pedido-update'/>"
													class="btn-floating btn-medium waves-effect waves-light indigo"
													class=""><i class="material-icons">edit</i></a> &nbsp; 
													-->
													<a
													id="enlace"
													class="btn-floating btn-medium waves-effect waves-light  red"
													onClick="return eliminar('${item.producto.descripcion}','${item.nroItem}');"
													class=""><i class="material-icons">delete</i></a>
													&nbsp;
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
								<input type="hidden" value="${pedido.items.size()}"
									id="cantItems">
								<table id="tableData"
									class="dataTable no-footer bordered highlight ">
									<thead>
									</thead>
									<tbody>

										<c:set var="item" value="0" />
										<c:forEach items="${pedido.items}" var="item" varStatus="i">
											<c:if test="${item.salsaItem.idSalsa != null}">
												<tr id="filaSalsa_${item.nroItem}">
													<td style="width: 20%;">
														<h6>&nbsp;&nbsp;&nbsp;&nbsp;${item.nroItem} - Salsa:</h6>
													</td>
													<td>
														<h6>
															<b>${item.salsaItem.descripcion}
																$${item.salsaItem.precio} por unidad.</b>
														</h6>
													</td>
												</tr>
											</c:if>
										</c:forEach>

										<tr class="red lighten-4">
											<td style="width: 20%;"><h5>&nbsp;&nbsp; Total:</h5> <form:hidden
													path="total" id="total" /></td>
											<td id="filaTotalPedido">
												<h5 id="totalPed">$${pedido.total}</h5>
											</td>
										</tr>
									</tbody>
								</table>
								<br> <input type="hidden"
									value="${pedido.comercio.realizaEnvios}" id="hiddenEnvios">
								<c:choose>
									<c:when test="${pedido.comercio.realizaEnvios == 'S'}">
										<div class="row">
											<div class="input-field col s3">
												<p>
													<input type="checkbox" id="enviar"
														onClick="enviarPedido();" /> <label for="enviar">Enviar</label>
													<form:hidden path="conEnvio" id="conEnvio" />
												</p>
											</div>
										</div>
										<div id="detalleEnvio" class="row col s10"
											style="display: none;">
											<div class="row">
												<fieldset class="grey lighten-4">
													<legend>
														<b>Detalles del envio</b>
													</legend>
													<div class="input-field col s6">
														<form:input path="direccion" id="direccion" type="text"
															class="validate" maxlength="45"
															onkeypress="return soloLetras(event)" required="required"
															autofocus="autofocus" />
														<label for="direccion">Dirección</label>
													</div>
													<div class="input-field col s2">
														<form:input path="nro" id="nro" type="text"
															class="validate" maxlength="45"
															onkeypress="return soloNumeros(event)"
															required="required" />
														<label for="nro">Nro</label>
													</div>
													<div class="input-field col s2">
														<form:input path="piso" id="piso" type="text"
															class="validate" maxlength="45"
															onkeypress="return soloNumeros(event)" />
														<label for="piso">Piso</label>
													</div>
													<div class="input-field col s2">
														<form:input path="depto" id="depto" type="text"
															class="validate" maxlength="45" />
														<label for="depto">Depto</label>
													</div>
													<div class="col s8">
														<label id="envios-error" class="error"></label>
													</div>
												</fieldset>
											</div>

										</div>
										<div class="row" id="observacionGeneral">
											<div class="input-field col s12">
												<form:textarea path="observaciones" id="observaciones"
													name="observaciones" class="materialize-textarea"
													length="200" maxlength="200" />
												<label for="observaciones">Observaciones</label>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<c:if test="${pedido.comercio.descripcion != null}">
											<div style="text-align: center">
												<p style="color: red; font: bold;">Este comercio no realiza envios</p>
											</div>
										</c:if>
									</c:otherwise>
								</c:choose>

							</div>
						</div>

						<br>
						<br>
						<div class="row center">
							<a class="waves-effect waves-light btn grey lighten-2 black-text"
								onClick="goBack()">Volver</a>
							<button onClick="return validarPedido();"
								class="waves-effect waves-light btn orange accent-3">Continuar</button>
						</div>
					</form:form>
					<br>
			</div>
		</div>
	</div>

	<!-- Modal Structure -->
	<div id="modal1" class="modal">
		<form:form method="POST" modelAttribute="usuario"
			action="usuarios-new" id="frmRegistro" name="frmRegistro">

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

						<div class="col s6">
							<label id="nombre-error" class="error"> </label>
						</div>
						<div class="col s6">
							<label id="apellido-error" class="error"> </label>
						</div>

					</div>
					<div class="row">
						<div class="input-field col s6">
							<form:input path="telefono" id="telefono" type="tel"
								class="phone-group" maxlength="15"
								onKeyPress="return soloNumeros(event)" />
							<label for="telefono">Telefono</label>
						</div>
						<div class="input-field col s6">
							<form:input path="celular" id="celular" type="tel"
								class="phone-group" maxlength="15"
								onKeyPress="return soloNumeros(event)" />
							<label for="celular">Celular</label>
						</div>

						<div class="col s6">
							<label id="numero-error" class="error"> </label>
						</div>

					</div>
					<div class="row">
						<div class="input-field col s12">
							<form:input path="email" id="email" type="email" class=""
								maxlength="30" />
							<label for="email" data-error="incorrecto"
								data-success="correcto">Email</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s6">
							<form:input path="nombreUsuario" id="username" type="text"
								class="" maxlength="30" />
							<label for="username">Nombre de usuario</label>
						</div>
						<div class="input-field col s6">
							<form:input path="contrasena" id="password" type="password"
								class="" maxlength="15" />
							<label for="password">Contraseña</label>
						</div>

						<div class="col s6">
							<label id="nombreUsuario-error" class="error"> </label>
						</div>
						<div class="col s6">
							<label id="contrasena-error" class="error"> </label>
						</div>

					</div>
					<div class="row">
						<p>Recibir notificaciones por email</p>
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
				<a class="modal-action modal-close waves-effect waves-red btn-flat">Cancelar</a>
				<button
					class="modal-action modal-close waves-effect waves-green btn-flat"
					onClick="return validar();">Guardar</button>
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
	<script src="<c:url value="resources/js/sweetalert.min.js" />"></script>
	<script src="<c:url value="resources/js/turn.js" />"></script>
	<script
		src="<c:url value="resources/js/material.sortable.datatable.js" />"></script>
	<script src="<c:url value="resources/js/dataTables.materialize.js" />"></script>

	<script type="text/javascript">
	  
		function validarPedido() {

			document.getElementById("envios-error").textContent = "";

			var rta = true;
			var msj = "";
			var enviar = document.getElementById("enviar").checked;

			if (enviar) {
				var direccion = document.getElementById("direccion").value;
				var nro = document.getElementById("nro").value;
				if (direccion == "" || nro == "") {
					document.getElementById("envios-error").style.display = "block";
					msj = "Estos campos son obligatorios";
					document.getElementById("envios-error").textContent = msj;
					rta = false;
				}
			}

			if (rta) {
				document.getElementById("frmPedido").submit();
			} else {
				return rta;
			}
		}

		$('#carrito').addClass('active');

		function validar() {

			var rta = true;

			var msj = "";
			var nombre = document.getElementById("nombre").value;
			var apellido = document.getElementById("apellido").value;
			var telefono = document.getElementById("telefono").value;
			var celular = document.getElementById("celular").value;
			var nombreUsuario = document.getElementById("username").value;
			var contrasena = document.getElementById("password").value;

			if (nombre == "") {
				document.getElementById("nombre-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("nombre-error").textContent = msj;
				rta = false;
			}

			if (apellido == "") {
				document.getElementById("apellido-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("apellido-error").textContent = msj;
				rta = false;
			}

			if (telefono == "" && celular == "") {
				document.getElementById("numero-error").style.display = "block";
				msj = "Ingrese al menos un número";
				document.getElementById("numero-error").textContent = msj;
				rta = false;
			}

			if (nombreUsuario == "") {
				document.getElementById("nombreUsuario-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("nombreUsuario-error").textContent = msj;
				rta = false;
			}

			if (contrasena == "") {
				document.getElementById("contrasena-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("contrasena-error").textContent = msj;
				rta = false;
			}

			if (rta) {
				document.getElementById("frmRegistro").submit();
			} else {
				return rta;
			}
		}

		window.onload = function() {
			var URLactual = window.location;
			if(URLactual.toString().indexOf("pedidos-new") != -1) {
				document.getElementById("contador").style.display = "none";
			} else {
				document.getElementById("contador").style.display = "block";
			}	
			checkEnvios();
			document.getElementById("conEnvio").value = "S";
		};

		function checkEnvios() {
			var conEnvio = document.getElementById("hiddenEnvios").value;
			if (conEnvio == "S") {
				document.getElementById("enviar").checked = true;
				document.getElementById("detalleEnvio").style.display = "block";
			} else {
				document.getElementById("detalleEnvio").style.display = "none";
			}
		}

		function enviarPedido() {
			if (document.getElementById("enviar").checked) {
				document.getElementById("detalleEnvio").style.display = "block";
				document.getElementById("conEnvio").value = "S";
			} else {

				document.getElementById("detalleEnvio").style.display = "none";
				document.getElementById("conEnvio").value = "N";
			}
		}

		function soloNumeros(e) {
			var key = window.Event ? e.which : e.keyCode
			return (key >= 48 && key <= 57)
		}

		function soloLetras(e) {
			key = e.keyCode || e.which;
			tecla = String.fromCharCode(key).toLowerCase();
			letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
			especiales = "8-37-39-46";

			tecla_especial = false
			for ( var i in especiales) {
				if (key == especiales[i]) {
					tecla_especial = true;
					break;
				}
			}

			if (letras.indexOf(tecla) == -1 && !tecla_especial) {
				return false;
			}
		}

		$(document)
				.ready(
						function() {
							$('#tableData')
									.dataTable(
											{
												"oLanguage" : {
													"sZeroRecords" : "  No se encontraron resultados",
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

		function eliminar(producto, item) {

			swal({
				title : 'Estás seguro?',
				text : "El item '" + producto + "' será eliminado",
				type : 'warning',
				showCancelButton : true,
				confirmButtonColor : '#F44336',
				cancelButtonColor : '#d33',
				confirmButtonText : 'Si, eliminarlo',
				closeOnConfirm : true
			},

			function(isConfirm) {
				if (isConfirm) {
					$.post('BorrarItemServlet', {
						nroItem : item,
					}, function(responseJson) {
						$('#filaItem_' + item).remove();

						//Si existe la fila de la salsa, la borro.
						$('#filaSalsa_' + item).remove();

						var cont = 0;
						var total = 0;

						$.each(responseJson, function(index, item) {
							total = total + item.subtotal;
							cont++;
						});
						//Valida - Si estoy borrando el último item del carrito.
						if (cont == 0) {
							$('#comercio').remove();
							$('#totalPed').remove();
							$('#costoEnvio').remove();
							//Borrar detalles de envio.
							//Borrar campo observación.
						} else {
							$('#totalPed').remove();
							$('#filaTotalPedido').append($('<h5>', {
								id : 'totalPed',
								text : '$' + total
							}));
						}

					});

				}
			});
		}
	</script>

</body>
</html>