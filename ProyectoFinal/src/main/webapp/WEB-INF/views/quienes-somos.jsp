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
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css" />" />
<link href="<c:url value="/resources/css/animate.css" />"
	type="text/css" rel="stylesheet" media="screen,projection" />
<script src="http://code.jquery.com/jquery-latest.js">
	
</script>
<style type="text/css">
.error {
	color: red;
}
</style>
<title>QuienesSomos</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">
		<%@ include file="/WEB-INF/views/title.jsp"%>
		<div class="container">
			<div>
				<h4 class="white-text">¿Quienes Somos?</h4>
				<ul class="collapsible popout" data-collapsible="accordion">
					<li>
						<div class="collapsible-header">
							<i class="material-icons">history</i>Historia
						</div>
						<div class="collapsible-body grey lighten-4">
							<p>
								Somos una empresa emergente, que nace en el año 2017, encargada
								de conectar a miles de personas con la variada gastronomía que
								se ofrece en la ciudad de Casilda. <br> Lo invitamos a
								degustar la mejor comida que puede probar, de forma facil y
								rápida, a través de nuestros sistema de delivery.
							</p>
						</div>
					</li>
					<li>
						<div class="collapsible-header">
							<i class="material-icons">trending_up</i>Misión
						</div>
						<div class="collapsible-body grey lighten-4">
							<p>
								Nuestro objetivo es ofrecer un servicio de primera calidad tanto
								para los comercios gastronómicos como para los clientes que se
								conecten con ellos. <br> Queremos facilitar la forma de
								pedir comida de las personas. Y para aquellas pequeñas y grandes
								empresas, darle la posibilidad de llegar a un público más
								amplio.
							</p>
						</div>
					</li>
					<li>
						<div class="collapsible-header">
							<i class="material-icons">visibility</i>Visión
						</div>
						<div class="collapsible-body grey lighten-4">
							<p>
								Buscamos consolidarnos como el principal medio por el cual las
								personas realicen pedidos de comida. <br> Queremos
								revolucionar la industria gastronómica en la ciudad de Casilda.
							</p>
						</div>
					</li>
				</ul>
			</div>
			<br> <br>
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
							<i class="material-icons prefix">perm_identity</i> <input
								id="username" name="username" type="text" class="validate"
								maxlength="30" autofocus="autofocus" /> <label for="username">Nombre
								de usuario</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<i class="material-icons prefix">lock_outline</i> <input
								id="password" name="password" type="password" class="validate"
								maxlength="15" /> <label for="password">Contraseña</label>
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
	<script type="text/javascript">
		$('#quienesSomos').addClass('active');

		$('#logo').addClass('animated bounceIn');

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

		$(document).ready(function() {
			$('.collapsible').collapsible();
		});

		$('#modal1').modal('close');
		$('#modal1').modal('open');

		$('#modalLogin').modal('close');
		$('#modalLogin').modal('open');

		$(document).ready(function() {
			// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
			$('.modal').modal();
		});

		function mostrarSideNav() {
			// Show sideNav
			$('.button-collapse').sideNav('show');
		}

		function ocultarSideNav() {
			// Hide sideNav
			$('.button-collapse').sideNav('hide');
		}
	</script>
</body>
</html>