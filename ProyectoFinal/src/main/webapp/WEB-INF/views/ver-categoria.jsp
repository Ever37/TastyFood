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

<title>VerCategoria</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">
		<%@ include file="/WEB-INF/views/title.jsp"%>

		<div class="white">
			<br>

			<div class="container">
				<h5 class="flow-text">
					<i class="material-icons">remove_red_eye</i>&nbsp;&nbsp;Detalles de
					la categoría
				</h5>
				<form:form class="col s12" id="frmc" method="POST"
					modelAttribute="categoria" role="form">
					<form:hidden path="idCategoria" />
					<div class="row right">
						<div class="details">
							<a href="<c:url value='categorias-listado' />" id="listCategoria"
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
									<form:input path="descripcion" id="descripcion" type="text"
										class="black-text" readOnly="readOnly" />
									<label for="nombre">Descripción</label>
								</div>
							</div>


							<!-- ACÁ VAN LOS PRODUCTOS DE LA CATEGORIA -->
							<div class="row">
								<div class="input-field col s12">
									<c:choose>
										<c:when test="${empty productos}">
											<div class="input-field col s4"></div>
											<div class="input-field col s8">
												<p class="error">No hay productos para la categoría</p>
											</div>
										</c:when>
										<c:otherwise>
											<div>
												<h5 class="flow-text">Productos asignados</h5>
												<table class="bordered">
													<thead>
														<tr>
															<th><div align="center">
																	<h6>
																		<b>Id</b>
																	</h6>
																</div></th>
															<th><div align="center">
																	<h6>
																		<b>Descripción</b>
																	</h6>
																</div></th>
															<th><div align="center">
																	<h6>
																		<b>Comercio</b>
																	</h6>
																</div></th>
														</tr>
													</thead>
													<tbody>
														<c:set var="p" value="0" />
														<c:forEach items="${productos}" var="p">
															<tr>
																<td><div align="center">${p.idProducto}</div></td>
																<td><div align="center">${p.descripcion}</div></td>
																<td><div align="center">${p.comercio.descripcion}</div></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
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