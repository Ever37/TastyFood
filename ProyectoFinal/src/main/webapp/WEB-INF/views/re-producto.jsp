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

<title>AltaProducto</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">
			<br>

			<div class="container">
				<h5 class="flow-text">
					<i class="material-icons">add</i>&nbsp;&nbsp;Nuevo producto
				</h5>
				<form:form class="col s12" id="frmc" name="frmc" method="POST"
					modelAttribute="producto"
					action="${pageContext.request.contextPath}/productos-new"
					role="form">
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
									<form:select id="categorias" name="categorias"
										path="categoria.descripcion"
										onChange="cambiaProducto(this.value);">
										<option value="" selected>Seleccione una categoría</option>
										<c:set var="c" value="0" />
										<c:forEach items="${categorias}" var="ca" varStatus="c">
											<option value="${ca.descripcion}" id="categoria">${ca.descripcion}</option>
										</c:forEach>
									</form:select>
								</div>

								<div class="col s12">
									<label id="categoria-error" class="error"> </label>
								</div>

							</div>

							<div class="row" id="descripcionGeneral" style="display: none;">
								<div class="input-field col s6">
									<form:input path="descripcion" id="descripcion" type="text"
										maxlength="100"
										placeholder="Si corresponde, especifique el tamaño."
										autofocus="autofocus" />
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
							<div class="row" id="observacionGeneral" style="display: none;">
								<div class="input-field col s6">
									<form:textarea path="observaciones" id="observaciones"
										name="observaciones" class="materialize-textarea" length="150"
										maxlength="150" />
									<label for="observaciones">Observaciones</label>
								</div>
							</div>

							<!-- Producto Simple -->
							<div class="row" id="precioUnico" style="display: none;">
								<div class="input-field col s6">
									<form:input path="precios[0].valor" id="precio" type="text"
										maxlength="6" onKeyPress="return soloNumeros(event)"
										placeholder="$" />
									<label for="precio">Precio</label>
								</div>
								<div class="input-field col s6"></div>
								<div class="col s12">
									<label id="precio-error" class="error"> </label>
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

							<!-- Salsas Pastas -->
							<div class="row" id="pastas" style="display: none;">
								<fieldset class="grey lighten-5">
									<legend>
										<b>Salsas</b>
									</legend>
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
								</fieldset>
							</div>

							<!-- Tamaños Pizza -->
							<div class="row" id="tamaños-pizza" style="display: none;">
								<fieldset class="grey lighten-5">
									<legend>
										<b>Tamaños</b>
									</legend>
									<div class="row">
										<div class="input-field col s1"></div>
										<div class="input-field col s3">
											<input id="valor" type="text" class="validate" maxlength="20" />
											<label for="valor">Tamaño</label>
										</div>
										<div class="input-field col s1">
											<div class="details">
												<a onClick="agregarTamañoPizza();" id="addTamañoPizza"
													class="btn-floating btn-small btn waves-effect waves-light green">
													<i class="small material-icons">add</i>
												</a>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s3"></div>
										<div class="input-field col s6">
											<table id="tablaTamañosPizza" class="table bordered">
												<thead>
													<tr>
														<th><h6>Tamaño</h6></th>
														<th><h6>Eliminar</h6></th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
									</div>
								</fieldset>
							</div>

							<!-- Sabor Pizzas -->
							<div class="row" id="saboresPizza" style="display: none;">
								<fieldset class="grey lighten-5">
									<legend>
										<b>Sabores</b>
									</legend>
									<div class="row">
										<div class="input-field col s1"></div>
										<div class="input-field col s6">
											<input id="saborPizza" type="text" class="validate"
												maxlength="20" onKeyPress="return soloLetras(event)" /> <label
												for="saborPizza">Sabor</label>
										</div>
										<div class="input-field col s1">
											<div class="details">
												<a onClick="agregarSaborPizza();" id="addSaborPizza"
													class="btn-floating btn-small btn waves-effect waves-light green">
													<i class="small material-icons">add</i>
												</a>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s3"></div>
										<div class="input-field col s6">
											<table id="tablaSaboresPizza" class="table bordered">
												<thead>
													<tr>
														<th><h6>Sabor</h6></th>
														<th><h6>Eliminar</h6></th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
									</div>
								</fieldset>
							</div>
							<div class="row" id="definirPrecios" style="display: none;">
								<a class="waves-effect waves-light btn blue darken-2" onClick="">Definir
									precios</a>
							</div>
						</div>
					</div>
					<br>
					<div class="row center">
						<a class="waves-effect waves-light btn grey lighten-2 black-text"
							onClick="goBack()">Volver</a>
						<button onClick="return validarAlta();"
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
		function validarAlta() {

			document.getElementById("categoria-error").textContent = "";
			document.getElementById("descripcion-error").textContent = "";
			document.getElementById("precio-error").textContent = "";

			var rta = true;

			var msj = "";
			var descripcion = document.getElementById("descripcion").value;
			var precio = document.getElementById("precio").value;
			var combo = document.getElementById("categorias");
			var idCategoria = combo.options[combo.selectedIndex].value;

			if (idCategoria == "") {
				document.getElementById("categoria-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("categoria-error").textContent = msj;
				rta = false;
			}

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
			if (activo) {
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

		function cambiaProducto(valor) {
			if (valor == 'Milanesas' || valor == 'Pollo'
					|| valor == 'Papas Fritas' || valor == 'Viandas'
					|| valor == 'Bebidas' || valor == 'Sandwiches'
					|| valor == 'Combos' || valor == 'Postres') {
				document.getElementById("precioUnico").style.display = "block";
				document.getElementById("observacionGeneral").style.display = "block";
				document.getElementById("descripcionGeneral").style.display = "block";
				document.getElementById("pastas").style.display = "none";
				document.getElementById("saboresPizza").style.display = "none";
				document.getElementById("tamaños-pizza").style.display = "none";
				document.getElementById("definirPrecios").style.display = "none";
				document.getElementById("cantGustos").style.display = "none";
			} else if (valor == 'Pizza') {
				document.getElementById("tamaños-pizza").style.display = "block";
				document.getElementById("saboresPizza").style.display = "block";
				document.getElementById("definirPrecios").style.display = "block";
				document.getElementById("observacionGeneral").style.display = "block";
				document.getElementById("descripcionGeneral").style.display = "block";
				document.getElementById("precioUnico").style.display = "none";
				document.getElementById("pastas").style.display = "none";
				document.getElementById("cantGustos").style.display = "none";
			} else if (valor == 'Helados') {
				document.getElementById("cantGustos").style.display = "block";
				document.getElementById("observacionGeneral").style.display = "block";
				document.getElementById("descripcionGeneral").style.display = "block";
				document.getElementById("precioUnico").style.display = "block";
				document.getElementById("pastas").style.display = "none";
				document.getElementById("saboresPizza").style.display = "none";
				document.getElementById("tamaños-pizza").style.display = "none";
				document.getElementById("definirPrecios").style.display = "none";
			} else if (valor == 'Pastas') {
				document.getElementById("pastas").style.display = "block";
				document.getElementById("precioUnico").style.display = "block";
				document.getElementById("observacionGeneral").style.display = "block";
				document.getElementById("descripcionGeneral").style.display = "block";
				document.getElementById("saboresPizza").style.display = "none";
				document.getElementById("tamaños-pizza").style.display = "none";
				document.getElementById("definirPrecios").style.display = "none";
				document.getElementById("cantGustos").style.display = "none";
			} else if (valor == '') {
				document.getElementById("pastas").style.display = "none";
				document.getElementById("saboresPizza").style.display = "none";
				document.getElementById("tamaños-pizza").style.display = "none";
				document.getElementById("definirPrecios").style.display = "none";
				document.getElementById("precioUnico").style.display = "none";
				document.getElementById("observacionGeneral").style.display = "none";
				document.getElementById("descripcionGeneral").style.display = "none";
				document.getElementById("cantGustos").style.display = "none";
			} else {
				document.getElementById("precioUnico").style.display = "block";
				document.getElementById("observacionGeneral").style.display = "block";
				document.getElementById("descripcionGeneral").style.display = "block";
				document.getElementById("pastas").style.display = "none";
				document.getElementById("saboresPizza").style.display = "none";
				document.getElementById("tamaños-pizza").style.display = "none";
				document.getElementById("definirPrecios").style.display = "none";
				document.getElementById("cantGustos").style.display = "none";
			}
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

		/*Select*/
		$(document).ready(function() {
			$('select').material_select();
		});
		$('select').material_select('destroy');

		function comprobarContrasena() {
			var clave1 = document.getElementById("password").value;
			var clave2 = document.getElementById("repetirpassword").value;

			if (clave1 != clave2) {

			}
		}
	</script>

</body>
</html>