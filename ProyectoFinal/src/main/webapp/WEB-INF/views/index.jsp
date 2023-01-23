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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/usuarios.css" />" />
<link href="<c:url value="/resources/css/animate.css" />"
	type="text/css" rel="stylesheet" media="screen,projection" />
<style type="text/css">
.error {
	color: red;
}
.abierto {
	color: green;
}
.cerrado {
	color: red;
}
</style>
<title>Index</title>
</head>
<body>

	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<span class="ir-arriba icon-arrow-up2"><i
			class="material-icons small">keyboard_arrow_up</i></span>

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
		</div>
		
		<div class="white">
			<br>

			<div class="container">


				<h5>Comercios Adheridos</h5>
				<div class="row">
					<form class="col s12" name="frm" id="frm">
						<div class="row">
							<div class="input-field col s6">
								<input id="descripcion" name="descripcion" type="text"
									class="validate" maxlength="45" autofocus="autofocus" /> <label
									for="last_name">Nombre del comercio</label>
							</div>
							<div class="input-field col s6">
								<select name="categorias" id="categorias">
									<option value="" selected>Seleccione una categoría</option>

									<c:set var="c" value="0" />
									<c:forEach items="${categorias}" var="cat" varStatus="c">
										<option value="${cat.idCategoria}" id="categoria">${cat.descripcion}</option>
									</c:forEach>
								</select>
							</div>

							<div class="col s6">
								<label id="busqueda-error" class="error"> </label>
							</div>

						</div>
						<div class="row">
							<input type="button" id="submit"
								class="waves-effect waves-light btn orange accent-3"
								onclick="return validarBusqueda();" value="Buscar" />
						</div>
					</form>
				</div>
			</div>
		</div>

		<!-- Las tarjetas son dinámicas, vienen por el ABMC de Comercios -->

		<div class="container">
			<c:set var="i" value="0" />
			<div class="row" id="catalogo">
				<div id="tarjetas">
					<c:forEach items="${comercios}" var="comercio" varStatus="i">
						<div class="col s10 m4" id="${comercio.idComercio}">
							<div class="card small white darken-1 hoverable">
								<div class="card-content black-text"
									id="contenedor_${comercio.idComercio}">
									<span class="card-title"><h5>${comercio.descripcion}</h5></span>
									<hr>
									<h6>${comercio.direccion} ${comercio.nro}</h6>
									<hr>
									<!--  
              <c:if test="${comercio.piso != '' && comercio.depto != ''}">
              	Piso: ${comercio.piso} &nbsp;&nbsp;&nbsp;&nbsp;
              	Depto: ${comercio.depto} <br>
              </c:if> 
              -->					
									<div id="condicionales_${comercio.idComercio}">
										<c:if test="${comercio.realizaEnvios == 'S' }">
											<p>
												Tiempo de envio:
												${comercio.tiempoEnvioMin}-${comercio.tiempoEnvioMax} min <br>
												Costo de envio: $${comercio.costoEnvio}
											</p>
											<c:if test="${comercio.compraMinima != null }">
				Compra mínima: $${comercio.compraMinima}
				</c:if>
										</c:if>
										<c:if test="${comercio.realizaEnvios == 'N' }">
				No realiza envios
              </c:if>
									</div>
								</div>
								<div class="card-action">
								<div class="row">
									<div class="col s8" id="hotSale_${item.idComercio}">
									<a
										class="btn-floating halfway-fab waves-effect waves-light orange accent-2"
										href="<c:url value='comercio-${comercio.idComercio}-menu' />">
										<i class="material-icons">restaurant_menu</i>
									</a>
									
									<c:if test="${comercio.tienePromo}">
									<a
										class="btn-floating halfway-fab waves-effect waves-light red accent-2
										tooltipped" data-position="right" data-delay="50" data-activates="slide-out" data-tooltip="HOT SALE">
										<i class="material-icons">whatshot</i>
									</a>
									</c:if>
									
									</div>
									<br>
									<div class="col s2" id="abiertoCerrado_${comercio.idComercio}">
										<c:choose>
											<c:when test="${comercio.abierto}">
												<i class="abierto">ABIERTO</i>
											</c:when>
											<c:otherwise>
												<i class="cerrado">CERRADO</i>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<br><br><br>
		<%@ include file="/WEB-INF/views/footer.jsp"%>
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
					onClick="return validar(); return false;">Guardar</button>
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

	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>

	<script type="text/javascript">
		 
		function validarBusqueda() {
			var rta = true;
			var msj = "";
			var descripcion = document.getElementById("descripcion").value;

			var combo = document.getElementById("categorias");
			var idCategoria = combo.options[combo.selectedIndex].value;

			if (descripcion == "" && idCategoria == "") {
				document.getElementById("busqueda-error").style.display = "block";
				msj = "Debe completar al menos un campo";
				//rta = false;
			}

			document.getElementById("busqueda-error").textContent = msj;
			if (true) {
				Materialize.fadeInImage('#catalogo');
			} else {
				return rta;
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

		$(document).ready(function() {

			$('#logo').addClass('animated bounceIn');
			$('#submit').addClass('animated pulse');

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

		$(document)
				.ready(
						function() {

							$('#submit')
									.click(
											function(event) {

												var rta = true;

												var d = document
														.getElementById("descripcion").value;
												var combo = document
														.getElementById("categorias");
												var c = combo.options[combo.selectedIndex].value;
														
												/*
												if (d == "" && c == "") {
													rta = false;
												}
												*/

												if (rta) {
													var descripcionVar = $(
															'#descripcion')
															.val();
													var categoriaVar = $(
															'select[name=categorias]')
															.val();
													// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
													$
															.get(
																	'BuscarComerciosServlet',
																	{
																		descripcion : descripcionVar,
																		categoria : categoriaVar,
																	},
																	function(
																			responseJson) {
																		
																		$('#catalogo').empty();
																		
																		var cont = 0;

																		$
																				.each(
																						responseJson,
																						function(
																								index,
																								item) { // Iterate over the JSON array.  
																							cont++;
																							$(
																									"#catalogo")
																									.append(
																											$(
																													'<div>',
																													{
																														id : 'tarjetas'
																													})
																													.append(
																															$(
																																	'<div>',
																																	{
																																		class : 'col s10 m4'
																																	})
																																	.append(
																																			$(
																																					'<div>',
																																					{
																																						class : 'card small white darken-1 hoverable'
																																					})
																																					.append(
																																							$(
																																									'<div>',
																																									{
																																										id : 'contenedor_'
																																												+ item.idComercio,
																																										class : 'card-content black-text'
																																									})
																																									.append(
																																											$(
																																													'<span>',
																																													{
																																														class : 'card-title'
																																													})
																																													.append(
																																															$(
																																																	'<h5>',
																																																	{
																																																		text : item.descripcion
																																																	})))
																																									.append(
																																											$('<hr>'))
																																									.append(
																																											$(
																																													'<p>',
																																													{
																																														text : 
																																																item.direccion
																																																+ ' '
																																																+ item.nro
																																													}))
																																									.append(
																																											$('<hr>')))
																																					.append(
																																							$(
																																									'<div>',
																																									{
																																										class : 'card-action'
																																									})
																																									.append($('<div>', { class : 'row'})
																																										.append($('<div>', { class : 'col s8', id : 'hotSale_' + item.idComercio}).append(
																																												$(
																																														'<a>',
																																														{
																																															class : 'btn-floating halfway-fab waves-effect waves-light orange accent-2',
																																															href : "<c:url value='comercio-" + item.idComercio + "-menu' />"
																																														})
																																														.append(
																																																$(
																																																		'<i>',
																																																		{
																																																			class : 'material-icons',
																																																			text : 'restaurant_menu'
																																																		}))
																																														)
																																														)
																																										
																																										.append($('<b>'))
																																										.append($('<div>', { id : 'abiertoCerrado_' + item.idComercio, class : 'col s2'})))
			
																																					))));

																							var idContenedor = "contenedor_"
																									+ item.idComercio;
																							var idCondicional = "condicionales_"
																									+ item.idComercio;

																							if (item.realizaEnvios == 'S') {
																								$(
																										"#"
																												+ idContenedor)
																										.append(
																												$(
																														'<div>',
																														{
																															id : "condicionales_"
																																	+ item.idComercio
																														})
																														.append(
																																$(
																																		'<p>',
																																		{
																																			text : 'Tiempo de envio: '
																																					+ item.tiempoEnvioMin
																																					+ "-"
																																					+ item.tiempoEnvioMax
																																					+ " min"
																																		})
																																		.append(
																																				$('<b>'))
																																		.append(
																																				$(
																																						'<p>',
																																						{
																																							text : 'Costo de envio: $'
																																									+ item.costoEnvio
																																						}))));

																								if (item.compraMinima != null
																										&& item.compraMinima > 0) {
																									$(
																											"#condicionales_"
																													+ item.idComercio)
																											.append(
																													$(
																															'<p>',
																															{
																																text : 'Compra mínima: $'
																																		+ item.compraMinima
																															}));
																								}

																							} else {
																								$(
																										"#contenedor"
																												+ item.idComercio)
																										.append(
																												$(
																														'<div>',
																														{
																															id : "condicionales_"
																																	+ item.idComercio
																														})
																														.append(
																																$(
																																		'<p>',
																																		{
																																			text : 'No realiza envios'
																																		})))
																							}

																							if(item.tienePromo) {
																								$("#hotSale_" + item.idComercio)
																									
																									.append($('<a>', {class : 'btn-floating halfway-fab waves-effect waves-light red accent-2', style : "margin-left: 2%;"})
																									.append($('<i>', {class : 'material-icons', text: 'whatshot'})))
																							}
																							/*
																							
																							Miro cuantos elementos tiene adentro: .append(E1,E2,E3, ...)
																							Si algún elemento intermedio posee contenido entonces:
																								.append(E1, E2.append(E2a, E2b), E3, ...)		        		  
																							 */
																							 if(item.abierto) {
																								 $("#abiertoCerrado_" + item.idComercio)
																								 	.append($('<br>'))
																								 	.append($('<i>', {class : "abierto", text: "ABIERTO"}))
																							 } else {
																								 $("#abiertoCerrado_" + item.idComercio)
																								 	.append($('<br>'))
																								 	.append($('<i>', {class : "cerrado", text: "CERRADO"}))
																							 }

																						});
																		if(cont==0) {
																			
																			$("#catalogo")
																				.append($('<div>',{class : 'container'})
																					.append($('<div>',{class : 'col s12'})
																						.append($('<div>',{id : 'card-alert', class : 'card yellow lighten-5 alert alert-info alert-dismissable'})
																							.append($('<div>',{class : 'card-content black-text'})
																								.append($('<p>',  {class :'center', text : 'No se han encontrado resultados'}))))))			
																		}
																		
																		/*
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
																		</div>
																		*/

																	});
												}
											});

						});

		/*Solo numeros*/
		function soloNumeros(e) {
			var key = window.Event ? e.which : e.keyCode
			return (key >= 48 && key <= 57)
		}

		/*Solo letras*/
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

		/*Select*/
		$(document).ready(function() {
			$('select').material_select();
		});
		$('select').material_select('destroy');

		/*Modal Registrate*/
		$('#modal1').modal('close');
		$('#modal1').modal('open');

		/*Modal Login*/
		$('#modalLogin').modal('close');
		$('#modalLogin').modal('open');

		$(document).ready(function() {
			// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
			$('.modal').modal();
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

		/*Carousel*/
		$('.carousel.carousel-slider').carousel({
			full_width : true
		});

	</script>

</body>
</html>