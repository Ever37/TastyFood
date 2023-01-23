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
		var pos = URLactual.indexOf("update");
		URLactual = URLactual.substring(pos, 0);
		document.getElementById("frmc").action = URLactual + "update";
		document.getElementById("frmc").submit();
		return val;
	}
</script>

<title>EditarProducto</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">
			<br>

			<div class="container">
				<h5 class="flow-text">
					<i class="material-icons">edit</i>&nbsp;&nbsp;Editar producto
				</h5>
				<form:form class="col s12" id="frmc" name="frmc" method="POST"
					modelAttribute="producto" onsubmit="return finalizar()" role="form">
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
						<br>
						<!--  
            	<div class="details">
					<a href="<c:url value='precios-listado' />" id="editPrecio" class="btn-floating btn-small btn tooltipped waves-effect waves-light yellow darken-2"
						data-position="left" data-delay="50" data-tooltip="Cambiar precio">
						<i class="material-icons">attach_money</i>
					</a>
                </div>
                -->
					</div>
					<br>
					<div class="modal-content">
						<div class="row col s12">
							<div class="row">
								<div class="input-field col s6">
									<input value="${categoria.descripcion}" id="categoria"
										type="text" class="black-text" readOnly="readOnly" /> <label
										for="descripcion">Categoría</label>
									<form:hidden path="categoria.idCategoria"
										value="${categoria.idCategoria}" />
								</div>
							</div>

							<div class="row">
								<div class="input-field col s6">
									<form:input path="descripcion" id="descripcion" type="text"
										maxlength="100" />
									<label for="descripcion">Descripción</label>
								</div>
								<div class="input-field col s2"></div>
								<div class="input-field col s2">
									<p>
										<input type="checkbox" id="activo" onClick="activar();" /> <label
											for="activo" class="black-text">Activo</label>
										<form:hidden path="activo" id="hiddenActivo" />
									</p>
								</div>
								<div class="input-field col s2"></div>
								<div class="col s12">
									<label id="descripcion-error" class="error"> </label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:textarea path="observaciones" id="observaciones"
										name="observaciones" class="materialize-textarea" length="150"
										maxlength="150" />
									<label for="observaciones">Observaciones</label>
								</div>
							</div>

							<!-- Producto Simple -->
							<div class="row" id="precioUnico">
								<div class="input-field col s6">
									<c:choose>
										<c:when test="${abierto}">
											<input value="${ultimoPrecio}" id="precio" type="text"
												readOnly="readOnly" class="black-text" />
											<label for="precio">Precio</label>
										</c:when>
										<c:otherwise>
											<form:input path="precios[0].valor" id="precio" type="text"
												value="${ultimoPrecio}" maxlength="6"
												onKeyPress="return soloNumeros(event)" placeholder="$" />
											<label for="precio">Precio</label>
										</c:otherwise>
									</c:choose>

								</div>
								<div class="input-field col s6"></div>
								<div class="col s12">
									<label id="precio-error" class="error"></label>
								</div>
							</div>

							<div id="cantGustos" class="row" style="display: none;">
								<p>Cantidad de gustos:</p>
								<div class="input-field col s1">
									<p>
										<input type="radio" value="1" name="grupoA" id="cant1"
											class="with-gap"> <label for="cant1">1</label>
									</p>
								</div>
								<div class="input-field col s1">
									<p>
										<form:radiobutton path="cantidadGustos" value="2"
											name="grupoA" id="cant2" class="with-gap" checked="checked" />
										<label for="cant2">2</label>
									</p>
								</div>
								<div class="input-field col s1">
									<p>
										<form:radiobutton path="cantidadGustos" value="3"
											name="grupoA" id="cant3" class="with-gap" />
										<label for="cant3">3</label>
									</p>
								</div>
								<div class="input-field col s1">
									<p>
										<form:radiobutton path="cantidadGustos" value="4"
											name="grupoA" id="cant4" class="with-gap" />
										<label for="cant4">4</label>
									</p>
								</div>
								<div class="input-field col s1">
									<p>
										<form:radiobutton path="cantidadGustos" value="5"
											name="grupoA" id="cant5" class="with-gap" />
										<label for="cant5">5</label>
									</p>
								</div>
							</div>

							<!-- Salsas de pastas -->
							<div class="row" id="salsasPastas">
								<fieldset class="grey lighten-5">
									<legend>
										<b>Salsas</b>
									</legend>
									<div class="row" id="pastas" style="display: none;">
										<div class="row">
											<div class="input-field col s1"></div>
											<div class="input-field col s4">
												<input id="saborSalsa" type="text" maxlength="20"
													onKeyPress="return soloLetras(event)" /> <label
													for="saborSalsa">Sabor</label>
											</div>
											<div class="input-field col s4">
												<input id="precioSalsa" type="text" maxlength="20"
													onKeyPress="return soloNumeros(event)" /> <label
													for="precioSalsa">Precio</label>
											</div>
											<div class="input-field col s1">
												<div class="details">
													<a onClick="agregarSalsa();" id="addSalsa"
														class="btn-floating btn-small btn waves-effect waves-light green">
														<i class="small material-icons">add</i>
													</a>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s3"></div>
											<div class="input-field col s6">
												<h6 class="flow-text">Salsas agregadas en la edición</h6>
												<table id="tablaSalsas" class="table bordered">
													<thead>
														<tr>
															<th><h6>Sabor</h6></th>
															<th><h6>Precio</h6></th>
															<th><h6>Eliminar</h6></th>
														</tr>
													</thead>
													<tbody>

													</tbody>
												</table>
											</div>
										</div>
									</div>
									<br> <br>
									<hr />
									<br> <br>
									<div class="row">
										<div class="input-field col s3"></div>
										<div class="input-field col s6">
											<h6 class="flow-text">Salsas agregadas anteriormente</h6>
											<div id="divSalsas">
												<table id="tablaSalsasAnteriores" class="table bordered">
													<thead>
														<tr>
															<th><div align="center">
																	<h6>Sabor</h6>
																</div></th>
															<th><div align="center">
																	<h6>Precio</h6>
																</div></th>
															<th><div align="center">
																	<h6>Eliminar</h6>
																</div></th>
														</tr>
													</thead>
													<tbody>
														<c:if test="${salsas.size()!= 0}">
															<c:set var="s" value="0" />
															<c:forEach items="${salsas}" var="s">
																<tr id="filaSalsa_${s.idSalsa}">
																	<td><div align="center">${s.descripcion}</div></td>
																	<td><div align="center">${s.precio}</div></td>
																	<td><div align="center">
																			<a id="deleteS"
																				class="btn-floating btn-medium waves-effect waves-light  red"
																				onClick="return eliminarSalsa('${s.descripcion}','${producto.idProducto}','${s.idSalsa}');"
																				class=""><i class="material-icons">delete</i> </a>
																		</div></td>
																</tr>
															</c:forEach>
														</c:if>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</fieldset>
							</div>
							<br>

						</div>

					</div>
					<br>
					<div class="row center">
						<a class="waves-effect waves-light btn grey lighten-2 black-text"
							onClick="goBack()">Volver</a>
						<button onClick="return validarEdicion();"
							class="waves-effect waves-light btn orange accent-3">Guardar</button>
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
		function validarEdicion() {

			document.getElementById("descripcion-error").textContent = "";
			document.getElementById("precio-error").textContent = "";

			var rta = true;

			var msj = "";
			var descripcion = document.getElementById("descripcion").value;
			var precio = document.getElementById("precio").value;

			if (descripcion == "") {
				document.getElementById("descripcion-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("descripcion-error").textContent = msj;
				rta = false;
			}

			if (precio == "") {
				document.getElementById("precio-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("precio-error").textContent = msj;
				rta = false;
			}

			if (rta) {
				document.getElementById("frmc").submit();
			} else {
				return rta;
			}
		}

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

		function activar() {
			if (document.getElementById("activo").checked) {
				document.getElementById("hiddenActivo").value = true;
			} else {
				document.getElementById("hiddenActivo").value = false;
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
								document.getElementById("salsasPastas").style.display = "none";
							} else if (valor == 'Pizza') {
								document.getElementById("tamaños-pizza").style.display = "block";
								document.getElementById("saboresPizza").style.display = "block";
								document.getElementById("definirPrecios").style.display = "block";
							} else if (valor == 'Helados') {
								document.getElementById("cantGustos").style.display = "block";
								document.getElementById("observaciones").style.display = "block";
								document.getElementById("precioUnico").style.display = "block";
								document.getElementById("salsasPastas").style.display = "none";
								document.getElementById("pastas").style.display = "none";
							} else if (valor == 'Pastas') {
								document.getElementById("pastas").style.display = "block";
								document.getElementById("precioUnico").style.display = "block";
							} else {
								document.getElementById("precioUnico").style.display = "block";
								document.getElementById("salsasPastas").style.display = "none";
							}
						});

		function eliminarSalsa(descripcion, producto, salsa) {

			swal({
				title : 'Estás seguro?',
				text : "La salsa '" + descripcion + "' será eliminada",
				type : 'warning',
				showCancelButton : true,
				confirmButtonColor : '#F44336',
				cancelButtonColor : '#d33',
				confirmButtonText : 'Si, eliminarlo',
				closeOnConfirm : true
			},

			function(isConfirm) {
				if (isConfirm) {
					/*
					Acá se usa el servlet BorrarSalsa
					 */
					// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
					$.post('BorrarSalsaServlet', {
						descripcion : descripcion,
						idProducto : producto,
						idSalsa : salsa,
					}, function(responseJson) {
						$('#filaSalsa_' + salsa).remove();
					});
				}
			});
		}

		function agregarSalsa() {

			var table = document.getElementById("tablaSalsas");
			var salsa = document.getElementById("saborSalsa").value;
			var precio = document.getElementById("precioSalsa").value;

			if (salsa != "" && precio != "") {

				var rowCount = table.rows.length;
				var row = table.insertRow(rowCount);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);

				cell1.innerHTML = salsa;
				cell2.innerHTML = precio;

				var element1 = document.createElement('input');
				element1.id = "salsas" + (rowCount - 1) + ".descripcion";
				element1.name = "salsas[" + (rowCount - 1) + "].descripcion";
				element1.type = 'hidden';
				element1.value = salsa;
				cell1.appendChild(element1);

				var element4 = document.createElement('input');
				element4.id = "salsas" + (rowCount - 1) + ".precio";
				element4.name = "salsas[" + (rowCount - 1) + "].precio";
				element4.type = 'hidden';
				element4.value = precio;
				cell2.appendChild(element4);

				/*Botón borrar en tabla*/
				var element2 = document.createElement('button');
				element2.setAttribute("id", "deleteRow" + (rowCount - 1));
				element2.setAttribute("name", "deleteRow" + (rowCount - 1));
				element2
						.setAttribute("class",
								"btn-floating btn-small btn waves-effect waves-light red");
				element2.setAttribute("type", "button");
				element2.setAttribute("onclick",
						"borrarSalsa(this.parentNode.parentNode.rowIndex);");

				var element3 = document.createElement('i');
				element3.setAttribute("class", "small material-icons");
				element3.innerText = "remove";
				element2.appendChild(element3);
				cell3.appendChild(element2);
				/*End Botón borrar en tabla*/

				document.getElementById("saborSalsa").value = "";
				document.getElementById("precioSalsa").value = "";
				document.getElementById("saborSalsa").focus();
			}
		}

		function borrarSalsa(i) {
			document.getElementById('tablaSalsas').deleteRow(i);
			document.getElementById("saborSalsa").focus();
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

		$("#frmc").validate({
			rules : {
				username : {
					required : true,
					minlength : 5
				},
			},
			//For custom messages
			messages : {
				uname : {
					required : "Ingrese un nombre de usuario",
					minlength : "Mínimo 5 caracteres"
				},
				curl : "Enter your website",
			},
			errorElement : 'div',
			errorPlacement : function(error, element) {
				var placement = $(element).data('error');
				if (placement) {
					$(placement).append(error)
				} else {
					error.insertAfter(element);
				}
			}
		});
	</script>

</body>
</html>