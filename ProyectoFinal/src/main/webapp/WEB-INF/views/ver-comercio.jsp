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

<title>VerComercio</title>
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
					del comercio
				</h5>
				<form:form class="col s12" id="frmc" method="POST"
					modelAttribute="comercio" role="form">
					<div class="row right">
						<sec:authorize
							access="hasAnyAuthority('ADMIN', 'COMERCIOS_SU', 'COMERCIOS_CU', 'COMERCIOS_RU')">
							<div class="details">
								<a href="<c:url value='comercios-listado' />" id="listComercios"
									class="btn-floating btn-medium btn tooltipped waves-effect waves-light blue"
									data-position="left" data-delay="50" data-tooltip="Listado">
									<i class="material-icons">list</i>
								</a>
							</div>
						</sec:authorize>

						<br> <a href="<c:url value='comercio-profile-update' />"
							id="editComercio"
							class="btn-floating btn-medium btn tooltipped waves-effect waves-light indigo"
							data-position="left" data-delay="50" data-tooltip="Editar"> <i
							class="material-icons">edit</i>
						</a>

					</div>

					<br>
					<div class="modal-content">
						<div class="row col s12">
							<div class="row">
								<div class="input-field col s3">
									<form:hidden path="usuario.idUsuario" />
									<form:input path="usuario.nombreUsuario" id="usuario"
										type="text" readOnly="readOnly" class="black-text" />
									<label for="usuario">Usuario</label>
								</div>
								<div class="input-field col s3">
									<form:input path="usuario.apellido" id="apellido" type="text"
										readOnly="readOnly" class="black-text" />
									<label for="apellido">Apellido</label>
								</div>
								<div class="input-field col s3">
									<form:input path="usuario.nombre" id="nombre" type="text"
										readOnly="readOnly" class="black-text" />
									<label for="nombre">Nombre</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="descripcion" id="descripcion" type="text"
										maxlength="45" class="black-text" readOnly="readOnly" />
									<label for="descripcion">Descripción</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="direccion" id="direccion" type="text"
										maxlength="30" class="black-text"
										onKeyPress="return soloLetras(event)" readOnly="readOnly" />
									<label for="direccion">Dirección</label>
								</div>
								<div class="input-field col s2">
									<form:input path="nro" id="nro" type="text" readOnly="readOnly"
										maxlength="6" class="black-text" />
									<label for="nro">Nro</label>
								</div>
								<div class="input-field col s2">
									<form:input path="piso" id="piso" type="text"
										readOnly="readOnly" maxlength="1" class="black-text" />
									<label for="piso">Piso</label>
								</div>
								<div class="input-field col s2">
									<form:input path="depto" id="depto" type="text"
										readOnly="readOnly" maxlength="1" class="black-text" />
									<label for="depto">Depto</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="cuit" id="cuit" type="text"
										readOnly="readOnly" maxlength="12" class="black-text" />
									<label for="cuit">Cuit</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s3">
									<p>
										<input type="checkbox" id="realizaEnvios" readOnly="readOnly"
											disabled="disabled" /> <label for="realizaEnvios">Realiza
											envios</label>
										<form:hidden path="realizaEnvios" id="hiddenEnvio" />
									</p>
								</div>
							</div>
							<br>
							<div id="detalleEnvio" class="row" style="display: none;">
								<fieldset class="grey lighten-5">
									<legend>
										<b>Detalles del envio</b>
									</legend>
									<div class="input-field col s3">
										<form:input path="tiempoEnvioMin" id="tiempoEnvioMin"
											type="text" readOnly="readOnly" class="black-text" />
										<label for="tiempoEnvioMin">Tiempo de envio mínimo</label>
									</div>
									<div class="input-field col s3">
										<form:input path="tiempoEnvioMax" id="tiempoEnvioMax"
											type="text" readOnly="readOnly" class="black-text" />
										<label for="tiempoEnvioMax">Tiempo de envio máximo</label>
									</div>
									<div class="input-field col s3">
										<form:input path="compraMinima" id="compraMinima" type="text"
											readOnly="readOnly" class="black-text" />
										<label for="nro">Compra mínima</label>
									</div>
									<div class="input-field col s3">
										<form:input path="costoEnvio" id="costoEnvio" type="text"
											readOnly="readOnly" class="black-text" />
										<label for="nro">Costo de envio</label>
									</div>
								</fieldset>
							</div>
							<div class="row">
								<fieldset class="grey lighten-5">
									<legend>
										<b>Teléfonos</b>
									</legend>
									<div class="row">
										<div class="input-field col s3"></div>
										<div class="input-field col s6">
											<c:choose>
												<c:when test="${telefonos.size() == 0}">
													<div class="input-field col s3"></div>
													<div class="input-field col s6">
														<p style="color: red; font: bold;">No existen
															teléfonos cargados</p>
													</div>
												</c:when>
												<c:otherwise>
													<table id="tablaTelefonos" class="table bordered">
														<thead>
															<tr>
																<th><div align="center">
																		<h6>Número</h6>
																	</div></th>
															</tr>
														</thead>
														<tbody>
															<c:set var="t" value="0" />
															<c:forEach items="${telefonos}" var="t">
																<tr>
																	<td><div align="center">${t.numero}</div></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</fieldset>
							</div>

							<div class="row">
								<fieldset class="grey lighten-5">
									<legend>
										<b>Horarios de atención</b>
									</legend>
									<div class="row">
										<div class="input-field col s3"></div>
										<div class="input-field col s6">
											<c:choose>
												<c:when test="${horarios.size() == 0}">
													<div class="input-field col s3"></div>
													<div class="input-field col s6">
														<p style="color: red; font: bold;">No existen horarios
															cargados</p>
													</div>
												</c:when>
												<c:otherwise>
													<table id="tablaHorarios" class="table bordered">
														<thead>
															<tr>
																<th><div align="center">
																		<h6>Día</h6>
																	</div></th>
																<th><div align="center">
																		<h6>Franja Horaria</h6>
																	</div></th>
															</tr>
														</thead>
														<tbody>
															<c:if test="${horarios.size()!= 0}">
																<c:set var="h" value="0" />
																<c:forEach items="${horarios}" var="h">
																	<tr>
																		<td><div align="center">${h.dia}</div></td>
																		<td><div align="center">${h.horaDesde}-
																				${h.horaHasta}</div></td>
																	</tr>
																</c:forEach>
															</c:if>
														</tbody>
													</table>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<br>
								</fieldset>
							</div>

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
		window.onload = function() {
			checkEnvios();
		};

		function checkEnvios() {
			var realizaEnvios = document.getElementById("hiddenEnvio").value;
			if (realizaEnvios == "S") {
				document.getElementById("realizaEnvios").checked = true;
				document.getElementById("detalleEnvio").style.display = "block";
			} else {
				document.getElementById("detalleEnvio").style.display = "none";
			}
		}

		function envios() {
			if (document.getElementById("realizaEnvios").checked) {
				document.getElementById("detalleEnvio").style.display = "block";
				document.getElementById("hiddenEnvio").value = "S";
			} else {
				document.getElementById("detalleEnvio").style.display = "none";
				document.getElementById("hiddenEnvio").value = "N";
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

		/*Select*/
		$(document).ready(function() {
			$('select').material_select();
		});
		$('select').material_select('destroy');
	</script>

</body>
</html>