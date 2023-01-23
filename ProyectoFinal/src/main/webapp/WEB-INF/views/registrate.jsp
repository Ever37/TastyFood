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
	href="<c:url value="/resources/css/usuarios.css" />" />
<style type="text/css">
.error {
	color: red;
}
</style>
<script src="http://code.jquery.com/jquery-latest.js">
	
</script>

<title>AltaCliente</title>
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
				</div>

				<h4>Regístrate</h4>
				<p>Es totalmente gratis!</p>

				<form:form method="POST" modelAttribute="usuario"
					action="usuarios-new" id="frmRegistro" name="frmRegistro">

					<div class="modal-content">
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
								<div class="input-field col s6">
									<form:input path="email" id="email" type="email" class=""
										maxlength="30" />
									<label for="email" data-error="incorrecto"
										data-success="correcto">Email</label>
								</div>
								<div class="col s12">
									<label id="email-error" class="error"> </label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="nombreUsuario" id="username" type="text"
										class="" maxlength="30" />
									<label for="username">Nombre de usuario</label>
								</div>

								<div class="col s12">
									<label id="nombreUsuario-error" class="error"> </label>
								</div>

							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="contrasena" id="password" name="password"
										type="password" class="active" maxlength="30" />
									<label for="password">Contraseña</label>
								</div>
								<div class="input-field col s6">
									<input id="repetirpassword" name="repetirpassword"
										type="password" maxlength="30" class="active" /> <label
										for="password">Repetir Contraseña</label>
								</div>

								<div class="col s6">
									<label id="contrasena-error" class="error"> </label>
								</div>
								<div class="col s6">
									<label id="repetircontrasena-error" class="error"> </label>
								</div>

							</div>
							<div class="row">
								<p>Recibir notificaciones por email</p>
								<div class="input-field col s3">
									<p>
										<form:radiobutton path="notificaciones" value="S"
											name="grupoA" id="notificacionSi" class="with-gap" />
										<label for="notificacionSi">Si</label>
									</p>
								</div>
								<div class="input-field col s3">
									<p>
										<form:radiobutton path="notificaciones" value="N"
											name="grupoA" id="notificacionNo" class="with-gap" />
										<label for="notificacionNo">No</label>
									</p>
								</div>
							</div>
						</div>

					</div>
					<br>
					<div class="row center">
						<a class="waves-effect waves-light btn grey lighten-2 black-text"
							onClick="goBack()">Volver</a>
						<button onClick="return validar();"
							class="waves-effect waves-light btn orange accent-3">Guardar</button>
					</div>
				</form:form>
				<br>
			</div>

		</div>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>

	<script type="text/javascript">
		function validar() {

			var rta = true;
			document.getElementById("nombre-error").textContent = "";
			document.getElementById("apellido-error").textContent = "";
			document.getElementById("numero-error").textContent = "";
			document.getElementById("email-error").textContent = "";
			document.getElementById("nombreUsuario-error").textContent = "";
			document.getElementById("contrasena-error").textContent = "";
			document.getElementById("repetircontrasena-error").textContent = "";

			var msj = "";
			var nombre = document.getElementById("nombre").value;
			var apellido = document.getElementById("apellido").value;
			var telefono = document.getElementById("telefono").value;
			var celular = document.getElementById("celular").value;
			var nombreUsuario = document.getElementById("username").value;
			var contrasena = document.getElementById("password").value;
			var repetir_contrasena = document.getElementById("repetirpassword").value;
			var email = document.getElementById("email").value;

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

			if (email != "") {
				if (!validarEmail(email)) {
					document.getElementById("email-error").style.display = "block";
					msj = "El email no tiene un formato correcto";
					document.getElementById("email-error").textContent = msj;
					rta = false;
				}
			}

			if (contrasena == "") {
				document.getElementById("contrasena-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("contrasena-error").textContent = msj;
				rta = false;
			}

			if (repetir_contrasena == "") {
				document.getElementById("repetircontrasena-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("repetircontrasena-error").textContent = msj;
				rta = false;
			}

			if (contrasena != "" && repetir_contrasena != "") {
				if (comprobarContrasena()) {
					document.getElementById("contrasena-error").style.display = "block";
					msj = "Las contraseñas no coinciden";
					document.getElementById("contrasena-error").textContent = msj;
					document.getElementById("password").text = "";
					document.getElementById("repetirpassword").text = "";
					rta = false;
				}
			}

			if (rta) {
				document.getElementById("frmRegistro").submit();
			} else {
				return rta;
			}
		}

		function comprobarContrasena() {
			var clave1 = document.getElementById("password").value;
			var clave2 = document.getElementById("repetirpassword").value;

			if (clave1 != clave2) {
				return true;
			} else {
				return false;
			}
		}

		function validarEmail(valor) {
			var expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if (expr.test(valor)) {
				return true;
			} else {
				return false;
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
	</script>

</body>
</html>