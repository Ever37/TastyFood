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
<link href="<c:url value="/resources/css/animate.css" />"
	type="text/css" rel="stylesheet" media="screen,projection" />
<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- Botón cerrar en mensajes de error -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Sweet Alert -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/sweetalert.css" />">

<title>MenuComercio</title>
</head>
<body>

	<%@ include file="/WEB-INF/views/nav.jsp"%>

	<div class="section no-pad-bot" id="index-banner">
		<br>

		<div class="white">
			<br> <span class="ir-arriba icon-arrow-up2"><i
				class="material-icons small">keyboard_arrow_up</i></span>
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
					<c:if test="${alert != ''}">
						<div id="card-alert"
							class="card yellow lighten-5 alert alert-info alert-dismissable">
							<div class="card-content black-text">
								<p>${alert}</p>
							</div>
						</div>
					</c:if>
				</div>

				<div class="row center">
					<div class="header" align="left">
						<h3 class="bold thin">${comercio.descripcion}</h3>
					</div>
					<div class="col s12">
						<ul class="tabs">
							<li class="tab col s3"><a class="active"
								href="#menuComercio">Menú</a></li>
							<li class="tab col s3"><a href="#horariosComercio">Horarios</a></li>
							<li class="tab col s3"><a href="#informacion">Información</a></li>
							<!--
        <li class="tab col s3"><a href="#comentarios">Comentarios</a></li>
        -->
						</ul>
					</div>

					<div id="menuComercio" class="col s12">
						<br>
						<c:if test="${productos.size()!= 0}">

							<div class="table-header" align="left">
								<h5>
									Menú<i id="menu" class="material-icons">restaurant_menu</i>
								</h5>
							</div>

							<c:set var="cat" value="0" />
							<c:forEach items="${categorias}" var="cat">
								<ul class="collection with-header">
									<!-- Head -->
									<li class="collection-header blue-grey lighten-5"><h5>${cat.descripcion}</h5></li>
									<c:forEach items="${cat.productos}" var="pro">
										<c:choose>
											<c:when
												test="${pro.categoria.idCategoria == cat.idCategoria}">
												<!-- Content -->
												<li class="collection-item">
													<div align="left">
													<c:if test="${comercio.tienePromo && (pro.idProducto == promo.producto.idProducto)}">
													 	<h5>
													 	<a
														class="btn-floating halfway-fab waves-effect waves-light red accent-2
														href="<c:url value='' />">
														<i class="material-icons">whatshot</i>
														</a>    PROMO DEL DÍA: 
													 	</h5>
													 										
												    </c:if>
														${pro.descripcion}
														<div class="right">
															<div class="col s8">$${pro.ultimoPrecio}</div>
															<div class="col s4">
															<sec:authorize access="!isAuthenticated()">
																<c:choose>
																	<c:when test="${EN_HORARIO}">
																		<a
																			href="${pageContext.request.contextPath}/producto-${pro.idProducto}-pedido-add"
																			class="secondary-content"> <i id="addShopping"
																			class="material-icons">add_shopping_cart</i>
																		</a>
																	</c:when>
																	<c:otherwise>
																		<a class="secondary-content"> <i id="addShopping"
																			class="material-icons">remove_shopping_cart</i>
																		</a>
																	</c:otherwise>
																</c:choose>
															</sec:authorize>
															<sec:authorize access="isAuthenticated()">
															<sec:authorize
																access="hasAnyAuthority('ADMIN', 'PEDIDOS_SU', 'PEDIDOS_CU')">
																<c:choose>
																	<c:when test="${EN_HORARIO}">
																		<a
																			href="${pageContext.request.contextPath}/producto-${pro.idProducto}-pedido-add"
																			class="secondary-content"> <i id="addShopping"
																			class="material-icons">add_shopping_cart</i>
																		</a>
																	</c:when>
																	<c:otherwise>
																		<a class="secondary-content"> <i id="addShopping"
																			class="material-icons">remove_shopping_cart</i>
																		</a>
																	</c:otherwise>
																</c:choose>
															</sec:authorize>	
															</sec:authorize>
															</div>
														</div>
													</div>
												</li>
											</c:when>
										</c:choose>
									</c:forEach>
								</ul>

							</c:forEach>
						</c:if>
						<br>
					</div>

					<div id="horariosComercio" class="col s12">
						<br>
						<c:if test="${horarios.size()!= 0}">
							<div class="table-header" align="left">
								<h5>
									Horario<i id="menu" class="material-icons">access_time</i>
								</h5>
							</div>

							<c:set var="hor" value="0" />
							<ul class="collection with-header">
								<c:forEach items="${horarios}" var="hor">
									<li class="collection-item">
										<div align="left">
											<b>${hor.dia}</b> &nbsp;&nbsp;&nbsp; ${hor.horaDesde} -
											${hor.horaHasta}
										</div>
									</li>
								</c:forEach>
							</ul>
						</c:if>
						<br>
					</div>
					<div id="informacion" class="col s12">
						<form:form modelAttribute="comercio">
							<br>
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
						</form:form>
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
													<p style="color: red; font: bold;">No existen teléfonos
														cargados</p>
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
					</div>
					<!--
   					<div id="comentarios" class="col s12">Comentarios</div>
   					-->
				</div>
			</div>

			<div class="row center">
				<a class="waves-effect waves-light btn grey lighten-2 black-text"
					onClick="goBack()">Volver</a>
			</div>
			<br>
		</div>
		<br>
	</div>

	<!-- Modal Structure -->
	<div id="modal1" class="modal">
		<form:form method="POST" modelAttribute="usuario" action="usuario-new"
			id="frm">

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

		$('#modal1').modal('close');
		$('#modal1').modal('open');

		$('#modalLogin').modal('close');
		$('#modalLogin').modal('open');

		$(document).ready(function() {
			$('.modal').modal();
		});

		$(document).ready(function() {

			$('.ir-arriba').click(function() {
				$('body, html').animate({
					scrollTop : '0px'
				}, 300);
			});

			$(window).scroll(function() {
				if ($(this).scrollTop() > 0) {
					$('.ir-arriba').slideDown(300);
				} else {
					$('.ir-arriba').slideUp(300);
				}
			});

		});

		/*Mostrar SideNav (Menu lateral)*/
		function mostrarSideNav() {
			// Show sideNav
			$('.button-collapse').sideNav('show');
		}

		/*Ocultar SideNav (Menu lateral)*/
		function ocultarSideNav() {
			// Hide sideNav
			$('.button-collapse').sideNav('hide');
		}
	</script>
</body>
</html>




