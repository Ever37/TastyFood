<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

<title>Login</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">

			<div class="container">
			<br>
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
				<form method="POST" action="<%=request.getContextPath()%>/login"
					id="frmLogin" name="frmLogin" class="col s6">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

					<div class="row col s10">
						<div class="row center">
							<h4>Login</h4>
						</div>

						<div class="row">
							<div class="input-field col s3"></div>
							<div class="input-field col s6">
								<i class="material-icons prefix">perm_identity</i> <input
									id="username" name="username" type="text" class="validate"
									maxlength="30" autofocus="autofocus" /> <label for="username">Nombre
									de usuario</label>
							</div>
							<br>
							<label id="nombreUsuario-error" class="error"></label>
						</div>
							
						<div class="row">
							<div class="input-field col s3"></div>
							<div class="input-field col s6">
								<i class="material-icons prefix">lock_outline</i> <input
									id="password" name="password" type="password" class="validate"
									maxlength="30" /> <label for="password">Contraseña</label>
							</div>
							<br>
							<label id="contrasena-error" class="error"></label>
						</div>
							
						<div class="row center">
							<a class="waves-effect waves-light btn grey lighten-2 black-text"
								onClick="goBack()">Volver</a>
							<button onClick="return validar();"
								class="waves-effect waves-light btn orange accent-3">Ingresar</button>
						</div>
					</div>

				</form>
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
			document.getElementById("nombreUsuario-error").textContent = "";
			document.getElementById("contrasena-error").textContent = "";

			var nombreUsuario = document.getElementById("username").value;
			var contrasena = document.getElementById("password").value;

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
				document.getElementById("frmLogin").submit();
			} else {
				return rta;
			}
		}

		function validarAlta() {
			document.getElementById("descripcion-error").textContent = "";

			var rta = true;

			var msj = "";
			var descripcion = document.getElementById("descripcion").value;

			if (descripcion == "") {
				document.getElementById("descripcion-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("descripcion-error").textContent = msj;
				rta = false;
			}

			if (rta) {
				document.getElementById("frmc").submit();
			} else {
				return rta;
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