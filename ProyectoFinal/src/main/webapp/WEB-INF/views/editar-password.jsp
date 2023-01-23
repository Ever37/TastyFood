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

<!-- Sweet Alert -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/sweetalert.css" />">

<script type="text/javascript">
	function finalizar() {
		var URLactual = window.location.toString();
		var pos = URLactual.indexOf("password");
		URLactual = URLactual.substring(pos, 0);
		document.getElementById("frmc").action = URLactual + "password";
		document.getElementById("frmc").submit();
		return val;
	}
</script>

<title>EditarPassword</title>
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

				<h5 class="flow-text">
					<i class="material-icons">edit</i>&nbsp;&nbsp;Editar contraseña
				</h5>
				<form:form class="col s12" id="frmc" method="POST"
					modelAttribute="usuario" onsubmit="return finalizar()" role="form">
					<form:hidden path="idUsuario" />
					<br>
					<div class="modal-content">
						<div class="row col s12">
							<div class="row">
								<div class="input-field col s6">
									<form:input path="nombreUsuario" id="username" type="text"
										class="black-text" maxlength="30" readOnly="readOnly" />
									<label for="username">Nombre de usuario</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="contrasenaAntigua" id="contrasenaAntigua"
										name="contrasenaAntigua" type="password" class="active"
										maxlength="15" autofocus="autofocus" />
									<label for="password">Contraseña Actual</label>
								</div>

								<div class="col s12">
									<label id="contrasenaAntigua-error" class="error"> </label>
								</div>

							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="contrasena" id="password" name="password"
										type="password" class="active" maxlength="15" />
									<label for="password">Nueva Contraseña</label>
								</div>
								<div class="input-field col s6">
									<input id="repetirpassword" name="repetirpassword"
										type="password" maxlength="15" class="active" /> <label
										for="password">Repetir Contraseña</label>
								</div>

								<div class="col s6">
									<label id="contrasena-error" class="error"> </label>
								</div>
								<div class="col s6">
									<label id="repetircontrasena-error" class="error"> </label>
								</div>

							</div>
						</div>
					</div>
					<br>
					<div class="row center">
						<a class="waves-effect waves-light btn grey lighten-2 black-text"
							onClick="goBack()">Volver</a>
						<button onClick="return validarEdicion();"
							class="waves-effect waves-light btn orange accent-3">
							Guardar</button>
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
	<script src="<c:url value="resources/js/sweetalert.min.js" />"></script>

	<script type="text/javascript">
		function validarEdicion() {

			document.getElementById("contrasenaAntigua-error").textContent = "";
			document.getElementById("contrasena-error").textContent = "";
			document.getElementById("repetircontrasena-error").textContent = "";

			var rta = true;
			var msj = "";
			var contrasenaAntigua = document
					.getElementById("contrasenaAntigua").value;
			var contrasena = document.getElementById("password").value;
			var repetir_contrasena = document.getElementById("repetirpassword").value;

			if (contrasenaAntigua == "") {
				document.getElementById("contrasenaAntigua-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("contrasenaAntigua-error").textContent = msj;
				rta = false;
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
				document.getElementById("frmc").submit();
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

		function comprobarContrasena() {
			var clave1 = document.getElementById("password").value;
			var clave2 = document.getElementById("repetirpassword").value;

			if (clave1 != clave2) {
				return true;
			} else {
				return false;
			}
		}
	</script>

</body>
</html>