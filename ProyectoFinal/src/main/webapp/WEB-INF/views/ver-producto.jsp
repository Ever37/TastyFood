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

<title>VerProducto</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">
			<br>

			<div class="container">
				<h5 class="flow-text">
					<i class="material-icons">remove_red_eye</i>&nbsp;&nbsp;Ver
					producto
				</h5>
				<form:form class="col s12" id="frmc" name="frmc" method="POST"
					modelAttribute="producto" role="form">
					<form:hidden path="idProducto" />
					<form:hidden path="comercio.idComercio" />
					<div class="row right">
						<div class="details">
							<a href="<c:url value='productos-listado' />" id="listProducto"
								class="btn-floating btn-medium btn tooltipped waves-effect waves-light blue"
								data-position="left" data-delay="50" data-tooltip="Listado">
								<i class="material-icons">list</i>
							</a>
						</div>
					</div>
					<br>
					<div class="modal-content">
						<div class="row col s12">
							<div class="row">
								<div class="input-field col s6">
									<form:input path="categoria.descripcion" id="categoria"
										type="text" readOnly="readOnly" class="black-text" />
									<label for="categoria">Descripción</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="descripcion" id="descripcion" type="text"
										readOnly="readOnly" class="black-text" />
									<label for="descripcion">Descripción</label>
								</div>
								<div class="input-field col s2"></div>
								<div class="input-field col s2">
									<p>
										<input type="checkbox" id="activo" readOnly="readOnly"
											disabled="disabled" /> <label for="activo">Activo</label>
										<form:hidden path="activo" id="hiddenActivo" />
									</p>
								</div>
								<div class="input-field col s2"></div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:textarea path="observaciones" id="observaciones"
										name="observaciones" class="materialize-textarea black-text"
										readOnly="readOnly" />
									<label for="observaciones">Observaciones</label>
								</div>
							</div>
							<div class="row" id="precioUnico">
								<div class="input-field col s6">
									<input value="${ultimoPrecio}" id="precio" type="text"
										readOnly="readOnly" class="black-text" /> <label for="precio">Precio</label>
								</div>
								<div class="input-field col s6"></div>
							</div>

							<div id="cantGustos" class="row" style="display: none;">
								<p>Cantidad de gustos:</p>
								<div class="input-field col s1">
									<p>
										<input type="radio" value="1" name="grupoA" id="cant1"
											class="with-gap" onclick="javascript: return false;">
										<label for="cant1">1</label>
									</p>
								</div>
								<div class="input-field col s1">
									<p>
										<form:radiobutton path="cantidadGustos" value="2"
											name="grupoA" id="cant2" class="with-gap"
											onclick="javascript: return false;" />
										<label for="cant2">2</label>
									</p>
								</div>
								<div class="input-field col s1">
									<p>
										<form:radiobutton path="cantidadGustos" value="3"
											name="grupoA" id="cant3" class="with-gap"
											onclick="javascript: return false;" />
										<label for="cant3">3</label>
									</p>
								</div>
								<div class="input-field col s1">
									<p>
										<form:radiobutton path="cantidadGustos" value="4"
											name="grupoA" id="cant4" class="with-gap"
											onclick="javascript: return false;" />
										<label for="cant4">4</label>
									</p>
								</div>
								<div class="input-field col s1">
									<p>
										<form:radiobutton path="cantidadGustos" value="5"
											name="grupoA" id="cant5" class="with-gap"
											onclick="javascript: return false;" />
										<label for="cant5">5</label>
									</p>
								</div>
							</div>


							<!-- Salsas Pastas -->
							<div class="row" id="pastas" style="display: none;">
								<fieldset class="grey lighten-5">
									<legend>
										<b>Salsas</b>
									</legend>
									<div class="row">
										<div class="input-field col s3"></div>
										<div class="input-field col s6">
											<c:choose>
												<c:when test="${salsas.size() == 0}">
													<div class="input-field col s3"></div>
													<div class="input-field col s6">
														<p style="color: red; font: bold;">No existen salsas
															cargadas</p>
													</div>
												</c:when>
												<c:otherwise>
													<table id="tablaSalsas" class="table bordered">
														<thead>
															<tr>
																<th><div align="center">
																		<h6>Sabor</h6>
																	</div></th>
																<th><div align="center">
																		<h6>Precio</h6>
																	</div></th>
															</tr>
														</thead>
														<tbody>
															<c:set var="t" value="0" />
															<c:forEach items="${salsas}" var="s">
																<tr>
																	<td><div align="center">${s.descripcion}</div></td>
																	<td><div align="center">${s.precio}</div></td>
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
		<br> <br> <br>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>


	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>
	<script src="<c:url value="resources/js/sweetalert.min.js" />"></script>

	<script type="text/javascript">
		window.onload = function() {
			checkActivo();
		};

		function checkActivo() {
			var activo = document.getElementById("hiddenActivo").value;
			if (activo == 'true') {
				document.getElementById("activo").checked = true;
			} else {
				document.getElementById("activo").checked = false;
			}
		}

		$(document)
				.ready(
						function() {
							document.getElementById("descripcion").focus();
							var valor = document.getElementById("categoria").value;
							if (valor == 'Milanesas' || valor == 'Pollo'
									|| valor == 'Papas Fritas'
									|| valor == 'Viandas' || valor == 'Bebidas'
									|| valor == 'Sandwiches'
									|| valor == 'Combos' || valor == 'Postres') {
								document.getElementById("precioUnico").style.display = "block";
							} else if (valor == 'Pizza') {
								document.getElementById("tamaños-pizza").style.display = "block";
								document.getElementById("saboresPizza").style.display = "block";
								document.getElementById("definirPrecios").style.display = "block";
							} else if (valor == 'Helados') {
								document.getElementById("cantGustos").style.display = "block";
							} else if (valor == 'Pastas') {
								document.getElementById("pastas").style.display = "block";
								document.getElementById("precioUnico").style.display = "block";
							}
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
	</script>

</body>
</html>