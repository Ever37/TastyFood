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
<link href="<c:url value="/resources/css/animate.css" />"
	type="text/css" rel="stylesheet" media="screen,projection" />
<style type="text/css">
</style>
<script src="http://code.jquery.com/jquery-latest.js">
	
</script>
<style type="text/css">
.error {
	color: red;
}
</style>
<title>AltaConsulta</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">
			<br>

			<div class="container">

				<div class="row">

					<div class="col s12">
						<c:if test="${success != null}">
							<div id="card-alert"
								class="card green lighten-5 alert alert-info alert-dismissable">
								<div class="card-content green-text">
									<p>${success}</p>
								</div>
							</div>
						</c:if>
					</div>
					
					<div class="col s6">
					<h5 class="flow-text">Formulario de contacto</h5>
					<form:form id="frmc" method="POST"
						modelAttribute="consulta"
						action="${pageContext.request.contextPath}/contacto" role="form">
						<form:hidden path="idConsulta" />
						<div class="row">
							<div class="input-field col s12">
								<i class="material-icons prefix">perm_identity</i>
								<form:input path="nombreRemitente" id="nombreRemitente"
									name="nombreRemitente" type="text" maxlength="45"
									onkeypress="return soloLetras(event)" autofocus="autofocus"
									required="required" />
								<label for="nombre">Nombre</label>
							</div>
							<br> <br>
							<div class="col s6">
								<label id="nombreRemitente-error" class="error"> </label>
							</div>
						</div>
						<div class="row">
							<div class="input-field col s12">
								<i class="material-icons prefix">email</i>
								<form:input path="emailRemitente" id="emailRemitente"
									name="emailRemitente" type="email" maxlength="45"
									required="required" />
								<label for="email">Email</label>
							</div>
							<br> <br>
							<div class="col s6">
								<label id="emailRemitente-error" class="error"> </label>
							</div>
						</div>
						<div class="row">
							<div class="input-field col s12">
								<i class="material-icons prefix">clear_all</i>
								<form:input path="asunto" id="asunto" name="asunto" type="text"
									maxlength="30" required="required" />
								<label for="asunto">Asunto</label>
							</div>
							<br> <br>
							<div class="col s6">
								<label id="asunto-error" class="error"> </label>
							</div>
						</div>
						<div class="row">
							<div class="input-field col s12">
								<i class="material-icons prefix">comment</i>
								<form:textarea path="mensaje" id="mensaje" name="mensaje"
									class="materialize-textarea" length="150" maxlength="150"
									required="required" />
								<label for="mensaje">Mensaje</label>
							</div>
							<br> <br>
							<div class="col s6">
								<label id="mensaje-error" class="error"> </label>
							</div>
						</div>
						
						<div class="row">
						<div class="input-field col s12">
						<a class="waves-effect waves-red btn grey lighten-2 black-text"
							onclick="limpiar();">Borrar</a>
						<button onClick="return validarConsulta();"
							class="waves-effect waves-light btn orange accent-3">Enviar</button>
						</div>
						</div>
						
					</form:form>
					</div>
					<div class="col s6">
						<iframe
							src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d26754.823421631216!2d-61.1860336804784!3d-33.047174127020405!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x95b62d3658299251%3A0xe21da5902a99ddf1!2sCasilda%2C+Provincia+de+Santa+Fe!5e0!3m2!1ses-419!2sar!4v1506386902946"
							width="600" height="450" frameborder="0" style="border: 0"
							allowfullscreen> </iframe>
					</div>
				</div>
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
					onClick="return validar();">Registrarme</button>
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
		$('#contacto').addClass('active');

		$('#logo').addClass('animated bounceIn');

		function validarConsulta() {

			document.getElementById("nombreRemitente-error").textContent = "";
			document.getElementById("emailRemitente-error").textContent = "";
			document.getElementById("asunto-error").textContent = "";
			document.getElementById("mensaje-error").textContent = "";

			var rta = true;

			var msj = "";
			var nombre = document.getElementById("nombreRemitente").value;
			var email = document.getElementById("emailRemitente").value;
			var asunto = document.getElementById("asunto").value;
			var mensaje = document.getElementById("mensaje").value;

			if (nombre == "") {
				document.getElementById("nombreRemitente-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("nombreRemitente-error").textContent = msj;
				rta = false;
			}

			if (email == "") {
				document.getElementById("emailRemitente-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("emailRemitente-error").textContent = msj;
				rta = false;
			} else if (!validarEmail(email)) {
				document.getElementById("emailRemitente-error").style.display = "block";
				msj = "El email no tiene un formato correcto";
				document.getElementById("emailRemitente-error").textContent = msj;
				rta = false;
			}

			if (asunto == "") {
				document.getElementById("asunto-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("asunto-error").textContent = msj;
				rta = false;
			}

			if (mensaje == "") {
				document.getElementById("mensaje-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("mensaje-error").textContent = msj;
				rta = false;
			}

			if (rta) {
				document.getElementById("frmc").submit();
			} else {
				return rta;
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

		function limpiar() {
			document.getElementById("frm").reset();
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

		$(document).ready(function() {
			$('.slider').slider();
		});

		$(document).ready(function() {
			$('select').material_select();
		});

		$('select').material_select('destroy');

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