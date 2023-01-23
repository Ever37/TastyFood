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

<title>VerItemDePedido</title>
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
				</div>
				<form:form id="frmitem" name="frmitem" modelAttribute="itemPedido">
					<form:hidden id="producto" path="producto.idProducto"></form:hidden>
					<form:hidden id="productoDesc" path="producto.descripcion"></form:hidden>
					<form:hidden id="categoria" path="producto.categoria.idCategoria"></form:hidden>
					<form:hidden id="comercio" path="producto.comercio.idComercio"></form:hidden>

					<c:forEach items="${producto.precios}" var="precios" varStatus="i">
						<form:hidden id="precio" path="precios[${i.index}].idPrecio"></form:hidden>
					</c:forEach>
					<div class="col s20">
						<div class="material-table">
							<div class="table-header">
								<span class="table-title"><i class="material-icons">remove_red_eye</i>
									Item de pedido</span>
							</div>
							<table id="tableData"
								class="dataTable no-footer bordered highlight"
								style="text-align: left;">
								<thead>
									<tr>
										<th width="5%">Nro.</th>
										<th>Categoría</th>
										<th width="20%">Descripción</th>
										<th width="20%">Observaciones</th>
										<th width="15%">Precio unitario</th>
										<th>Cant.</th>
										<th>Subtotal</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${itemPedido.nroItem}</td>
										<td>${itemPedido.producto.categoria.descripcion}</td>
										<td>${itemPedido.producto.descripcion}</td>
										<td>${itemPedido.producto.observaciones}</td>
										<td>${ultimoPrecio}</td>
										<td>${itemPedido.cantidad}</td>
										<td>${itemPedido.subtotal}</td>
									</tr>
								</tbody>
							</table>
						</div>

						<div>
							<c:choose>
								<c:when
									test="${itemPedido.producto.categoria.descripcion == 'Helados'}">
									<br>
									<label for="sabores">Sabores:</label>
									<table id="tableData"
										class="dataTable no-footer highlight striped"
										style="text-align: left;">
										<tbody>
											<c:forEach items="${sabores}" var="sabor" varStatus="i">
												<tr>
													<td>${sabor.descripcion}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:when>
								<c:when
									test="${itemPedido.producto.categoria.descripcion == 'Pastas'}">
									<br>

									<div class="row">
										<div class="input-field col s6">
											<form:input path="salsaItem.descripcion" id="salsaItem"
												type="text" maxlength="45" class="black-text"
												readOnly="readOnly" />
											<label for="salsaItem">Salsa</label>
										</div>
										<div class="input-field col s2">
											<form:input path="salsaItem.precio" id="precioSalsa"
												type="number" maxlength="45" class="black-text"
												readOnly="readOnly" />
											<label for="precioSalsa">Precio</label>
										</div>
										<div class="input-field col s2"></div>
										<div class="input-field col s2">
											<input id="total" type="number" maxlength="45"
												value="${itemPedido.subtotal + itemPedido.salsaItem.precio}"
												class="black-text" readOnly="readOnly" /> <label
												for="total">Total</label>
										</div>
									</div>

								</c:when>
							</c:choose>
						</div>
						<br>
						<div class="row center" id="aclaracionesItem">
							<div class="input-field col s6">
								<form:textarea path="aclaraciones" id="aclaraciones"
									name="aclaraciones" class="materialize-textarea black-text"
									readOnly="readOnly" />
								<label for="aclaraciones">Aclaraciones</label>
							</div>
						</div>
						<div class="row center">
							<div class="details">
								<a
									class="waves-effect waves-light btn grey lighten-2 black-text"
									onClick="goBack()">Volver</a>
							</div>
						</div>
					</div>
				</form:form>
				<br> <br> <br> <span class="details"> </span>
			</div>
		</div>
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

	<script type="text/javascript">
		$(document).ready(function() {

			var last_valid_selection = null;
			$('#sabores').change(function(event) {
				//Cambiar según la cantidad de gustos del producto.
				if ($(this).val().length > 3) {
					$(this).val(last_valid_selection);
				} else {
					last_valid_selection = $(this).val();
				}
			});
		});

		$(document).ready(function() {

			var select = document.getElementById('sabores');
			var optionCount = 0;
			var cant = $("#cantSabores").val();
			$('#sabores').change(function(event) {
				if ($(this).val().length < cant) {
					//Puede seguir seleccionando

				} else {
					//No se puede seguir seleccionando.

				}
			});
		});

		$(document).ready(function() {
			$('select').material_select();
		});

		$(document).ready(function() {
			// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
			$('.modal').modal();
		});
		$('#addCart').modal('open');
		$('#addCart').modal('close');

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