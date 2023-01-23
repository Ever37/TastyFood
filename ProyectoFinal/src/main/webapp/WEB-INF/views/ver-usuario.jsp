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

<title>VerUsuario</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">
			<br>

			<div class="container">
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
				<div class="col s12">
					<c:if test="${error != null}">
						<div id="card-alert"
							class="card green lighten-5 alert alert-info alert-dismissable">
							<div class="card-content green-text">
								<p>${error}</p>
							</div>
						</div>
					</c:if>
				</div>
				<h5 class="flow-text">
					<i class="material-icons">remove_red_eye</i>&nbsp;&nbsp;Detalles
					del usuario
				</h5>
				<form:form class="col s12" id="frmc" method="POST"
					modelAttribute="usuario" role="form">
					<form:hidden path="idUsuario" />

					<c:if test="${profile == 'false' }">
						<div class="row right">
							<div class="details">
								<a href="<c:url value='usuarios-listado' />" id="listUsuario"
									class="btn-floating btn-medium btn tooltipped waves-effect waves-light blue"
									data-position="left" data-delay="50" data-tooltip="Listado">
									<i class="material-icons">list</i>
								</a>
							</div>
						</div>
					</c:if>

					<c:if test="${profile == 'true'}">
						<div class="row right">
							<div class="details">
								<a href="<c:url value='usuario-profile-update' />"
									id="editUsuario"
									class="btn-floating btn-medium btn tooltipped waves-effect waves-light indigo"
									data-position="left" data-delay="50" data-tooltip="Editar">
									<i class="material-icons">edit</i>
								</a>
							</div>
							<br>
							<sec:authorize
								access="hasAnyAuthority('ADMIN', 'MICOMER_SU', 'MICOMER_CU', 'MICOMER_RU')">
								<c:if test="${comercio != null}">
								<div class="details">
									<a href="<c:url value='comercio-profile' />" id="enlace"
										class="btn-floating btn-medium btn tooltipped waves-effect waves-light purple lighten-2"
										data-position="left" data-delay="50"
										data-tooltip="Mi comercio"> <i class="material-icons">home</i>
									</a>
								</div>
								</c:if>
							</sec:authorize>
						</div>

					</c:if>
					<br>
					<div class="modal-content">
						<div class="row col s12">
							<div class="row">
								<div class="input-field col s6">
									<form:input path="grupo.descripcion" id="tipo" type="text"
										class="black-text" maxlength="30" readOnly="readOnly" />
									<label for="tipo">Tipo</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="nombre" id="nombre" type="text"
										class="black-text" maxlength="30"
										onkeypress="return soloLetras(event)" readOnly="readOnly" />
									<label for="nombre">Nombre</label>
								</div>
								<div class="input-field col s6">
									<form:input path="apellido" id="apellido" type="text"
										class="black-text" maxlength="30" readOnly="readOnly" />
									<label for="apellido">Apellido</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="telefono" id="telefono" type="text"
										class="black-text" maxlength="15" readOnly="readOnly" />
									<label for="telefono">Telefono</label>
								</div>
								<div class="input-field col s6">
									<form:input path="celular" id="celular" type="text"
										class="black-text" maxlength="15" readOnly="readOnly" />
									<label for="celular">Celular</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="email" id="email" type="email"
										class="black-text" maxlength="30" readOnly="readOnly" />
									<label for="email" data-error="incorrecto"
										data-success="correcto">Email</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="nombreUsuario" id="username" type="text"
										class="black-text" maxlength="30" readOnly="readOnly" />
									<label for="username">Nombre de usuario</label>
								</div>
							</div>
							<sec:authorize access="hasAnyAuthority('ADMIN', 'USUARIOS_SU')">
								<div class="row">
									<div class="input-field col s6">
										<form:input path="fechaAlta" id="fechaAlta" type="text"
											class="black-text" maxlength="15" readOnly="readOnly" />
										<label for="fechaAlta">Fecha de Alta</label>
									</div>
									<div class="input-field col s6">
										<form:input path="valoracion" id="valoracion" type="text"
											class="black-text" maxlength="15" readOnly="readOnly" />
										<label for="valoracion">Valoración</label>
									</div>
								</div>
								<div class="row">
									<p>Recibir notificaciones por email</p>
									<div class="input-field col s3">
										<p>
											<form:radiobutton path="notificaciones" value="S"
												name="grupoA" id="notificacionSi"
												class="with-gap black-text" disabled="true" />
											<label for="notificacionSi">Si</label>
										</p>
									</div>
									<div class="input-field col s3">
										<p>
											<form:radiobutton path="notificaciones" value="N"
												name="grupoA" id="notificacionNo"
												class="with-gap black-text" disabled="true" />
											<label for="notificacionNo">No</label>
										</p>
									</div>
								</div>
							</sec:authorize>

						</div>

					</div>
					<br>
					<div class="row center">
						<a class="waves-effect waves-light btn grey lighten-2 black-text"
							onClick="goBack()">Volver</a>
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

		/*Select*/
		$(document).ready(function() {
			$('select').material_select();
		});
		$('select').material_select('destroy');
	</script>

</body>
</html>