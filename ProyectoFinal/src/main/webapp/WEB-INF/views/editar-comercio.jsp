<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<title>EditarComercio</title>
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
				</div>
				<h5 class="flow-text">
					<i class="material-icons">edit</i>&nbsp;&nbsp;Editar comercio
				</h5>
				<form:form class="col s12" id="frmc" method="POST"
					modelAttribute="comercio" onsubmit="return finalizar()" role="form">
					<form:hidden path="idComercio" />
					<sec:authorize 
					access="hasAnyAuthority('ADMIN', 'COMERCIOS_SU', 'COMERCIOS_CU', 'COMERCIOS_RU')">
					<div class="row right">
						<div class="details">
							<a href="<c:url value='comercios-listado' />" id="listComercio"
								class="btn-floating btn-medium btn tooltipped waves-effect waves-light blue"
								data-position="left" data-delay="50" data-tooltip="Listado">
								<i class="material-icons">list</i>
							</a>
						</div>
					</div>
					</sec:authorize>
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
										maxlength="45" autofocus="autofocus" />
									<label for="descripcion">Descripción</label>
								</div>

								<br> <br>
								<div class="col s6">
									<label id="descripcion-error" class="error"> </label>
								</div>

							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="direccion" id="direccion" type="text"
										maxlength="30" onKeyPress="return soloLetras(event)" />
									<label for="direccion">Dirección</label>
								</div>
								<div class="input-field col s2">
									<form:input path="nro" id="nro" type="text" maxlength="6"
										onKeyPress="return soloNumeros(event)" />
									<label for="nro">Nro</label>
								</div>
								<div class="input-field col s2">
									<form:input path="piso" id="piso" type="text" maxlength="1"
										onKeyPress="return soloNumeros(event)" />
									<label for="piso">Piso</label>
								</div>
								<div class="input-field col s2">
									<form:input path="depto" id="depto" type="text" maxlength="1"
										onKeyPress="return soloLetras(event)" />
									<label for="depto">Depto</label>
								</div>

								<div class="col s8">
									<label id="direccion-error" class="error"> </label>
								</div>

							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="cuit" id="cuit" type="text" maxlength="12"
										onKeyPress="return soloNumeros(event)" />
									<label for="cuit">Cuit</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s3">
									<p>
										<input type="checkbox" id="realizaEnvios" onClick="envios();" />
										<label for="realizaEnvios">Realiza envios</label>
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
										<form:select id="tiemposMin" path="tiempoEnvioMin">
											<form:option value="" disabled="disabled" selected="selected">Tiempo de envío mínimo</form:option>
											<form:option value="10">10 min</form:option>
											<form:option value="15">15 min</form:option>
											<form:option value="20">20 min</form:option>
											<form:option value="25">25 min</form:option>
											<form:option value="30">30 min</form:option>
											<form:option value="35">35 min</form:option>
											<form:option value="40">40 min</form:option>
											<form:option value="45">45 min</form:option>
											<form:option value="50">50 min</form:option>
										</form:select>
									</div>
									<div class="input-field col s3">
										<form:select id="tiemposMax" path="tiempoEnvioMax">
											<form:option value="" disabled="disabled" selected="selected">Tiempo de envío máximo</form:option>
											<form:option value="15">15 min</form:option>
											<form:option value="20">20 min</form:option>
											<form:option value="25">25 min</form:option>
											<form:option value="30">30 min</form:option>
											<form:option value="35">35 min</form:option>
											<form:option value="40">40 min</form:option>
											<form:option value="45">45 min</form:option>
											<form:option value="50">50 min</form:option>
											<form:option value="55">50 min</form:option>
											<form:option value="60">50 min</form:option>
										</form:select>
									</div>
									<div class="input-field col s3">
										<form:input path="compraMinima" id="compraMinima" type="text"
											class="validate" maxlength="6"
											onKeyPress="return soloNumeros(event)" placeholder="$" />
										<label for="nro">Compra mínima</label>
									</div>
									<div class="input-field col s3">
										<form:input path="costoEnvio" id="costoEnvio" type="text"
											class="validate" maxlength="6"
											onKeyPress="return soloNumeros(event)" placeholder="$" />
										<label for="nro">Costo de envio</label>
									</div>

									<div class="col s8">
										<label id="envios-error" class="error"></label>
									</div>

								</fieldset>
							</div>
							<div class="row">
								<fieldset class="grey lighten-5">
									<legend>
										<b>Teléfonos</b>
									</legend>
									<div class="row">
										<div class="input-field col s1"></div>
										<div class="input-field col s6">
											<input id="telefono" type="text" class="validate"
												maxlength="12" onKeyPress="return soloNumeros(event)" /> <label
												for="telefono">Telefono/Celular del comercio</label>
										</div>
										<div class="input-field col s1">
											<div class="details">
												<a onClick="agregarTelefono();" id="addTelefono"
													class="btn-floating btn-small btn waves-effect waves-light green">
													<i class="small material-icons">add</i>
												</a>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s3"></div>
										<div class="input-field col s6">
											<h6 class="flow-text">Teléfonos agregados en la edición</h6>
											<table id="tablaTelefonos" class="table bordered">
												<thead>
													<tr>
														<th><h6>Número</h6></th>
														<th><h6>Eliminar</h6></th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
									</div>
									<br> <br>
									<hr />
									<br> <br>
									<div class="row">
										<div class="input-field col s3"></div>
										<div class="input-field col s6">
											<h6 class="flow-text">Teléfonos agregados anteriormente</h6>
											<div id="divTelefonos">
												<table id="tablaTelefonosAnterioresHead"
													class="table bordered">
													<thead>
														<tr>
															<th><div align="center">
																	<h6>Número</h6>
																</div></th>
															<th><div align="center">
																	<h6>Eliminar</h6>
																</div></th>
														</tr>
													</thead>
												</table>
												<table id="tablaTelefonosAnterioresBody"
													class="table bordered">
													<tbody>
														<c:if test="${telefonos.size()!= 0}">
															<c:set var="t" value="0" />
															<c:forEach items="${telefonos}" var="t">
																<tr id="filaTel_${t.idTelefono}">
																	<td><div align="center">${t.numero}</div></td>
																	<td><div align="center">
																			<!-- 
								 <input type="button" id="deleteT" class="btn-floating btn-medium waves-effect waves-light red" 
  	 							  onClick="return eliminarTelefono('${t.numero}', '${t.idTelefono}', '${comercio.idComercio}');"  />
							 -->
																			<a
																				onClick="return eliminarTelefono('${t.numero}', '${t.idTelefono}', '${comercio.idComercio}');"
																				id="deleteT"
																				class="btn-floating btn-small btn waves-effect waves-light red">
																				<i class="small material-icons">delete</i>
																			</a>
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

							<div class="row">
								<fieldset class="grey lighten-5">
									<legend>
										<b>Horarios de atención</b>
									</legend>
									<div class="row">
										<div class="input-field col s1"></div>
										<div class="input-field col s3">
											<select name="dias" id="dias">
												<option value="" disabled selected>Seleccione un
													día</option>
												<option value="Domingo">Domingo</option>
												<option value="Lunes">Lunes</option>
												<option value="Martes">Martes</option>
												<option value="Miercoles">Miercoles</option>
												<option value="Jueves">Jueves</option>
												<option value="Viernes">Viernes</option>
												<option value="Sábado">Sábado</option>
											</select>

										</div>
										<div class="input-field col s3">
											<input id="horaDesde" type="time" class="timepicker" /> <label
												for="horaDesde"></label>
										</div>
										<div class="input-field col s3">
											<input id="horaHasta" type="time" class="timepicker" /> <label
												for="horaHasta"></label>
										</div>
										<div class="input-field col s1">
											<div class="details">
												<a onClick="agregarHorario();" id="addHorario"
													class="btn-floating btn-small btn waves-effect waves-light green">
													<i class="small material-icons">add</i>
												</a>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s3"></div>
										<div class="input-field col s6">
											<h6 class="flow-text">Horarios agregados en la edición</h6>
											<table id="tablaHorarios" class="table bordered">
												<thead>
													<tr>
														<th><h6>Día</h6></th>
														<th><h6>Franja Horaria</h6></th>
														<th><h6>Eliminar</h6></th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
									</div>
									<br> <br>
									<hr />
									<br> <br>
									<div class="row">
										<div class="input-field col s3"></div>
										<div class="input-field col s6">
											<h6 class="flow-text">Horarios agregados anteriormente</h6>
											<div id="divHorarios">
												<table id="tablaHorariosAnteriores" class="table bordered">
													<thead>
														<tr>
															<th><div align="center">
																	<h6>Día</h6>
																</div></th>
															<th><div align="center">
																	<h6>Franja Horaria</h6>
																</div></th>
															<th><div align="center">
																	<h6>Eliminar</h6>
																</div></th>
														</tr>
													</thead>
													<tbody>
														<c:if test="${horarios.size()!= 0}">
															<c:set var="h" value="0" />
															<c:forEach items="${horarios}" var="h">
																<tr id="filaHor_${h.idHorario}">
																	<td><div align="center">${h.dia}</div></td>
																	<td><div align="center">
																			<fmt:formatDate pattern="HH:mm" type="time"
																				value="${h.horaDesde}" />
																			&nbsp; - &nbsp;
																			<fmt:formatDate pattern="HH:mm" type="time"
																				value="${h.horaHasta}" />
																		</div></td>
																	<td><div align="center">
																			<a id="deleteH"
																				class="btn-floating btn-medium waves-effect waves-light  red"
																				onClick="return eliminarHorario('${h.dia}', '<fmt:formatDate pattern="HH:mm" type="time" value="${h.horaDesde}" />', '<fmt:formatDate pattern="HH:mm" type="time" value="${h.horaHasta}" />', '${h.idHorario}', '${comercio.idComercio}');"
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
			</div>

			<br>
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

			document.getElementById("envios-error").textContent = "";
			document.getElementById("descripcion-error").textContent = "";
			document.getElementById("direccion-error").textContent = "";

			var rta = true;

			var msj = "";
			var descripcion = document.getElementById("descripcion").value;
			var direccion = document.getElementById("direccion").value;
			var nro = document.getElementById("nro").value;
			var realizaEnvios = document.getElementById("realizaEnvios").checked;

			if (descripcion == "") {
				document.getElementById("descripcion-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("descripcion-error").textContent = msj;
				rta = false;
			}

			if (direccion == "") {
				document.getElementById("direccion-error").style.display = "block";
				msj = "El campo dirección y nro son obligatorios";
				document.getElementById("direccion-error").textContent = msj;
				rta = false;
			}

			if (nro == "") {
				document.getElementById("direccion-error").style.display = "block";
				msj = "El campo dirección y nro son obligatorios";
				document.getElementById("direccion-error").textContent = msj;
				rta = false;
			}

			if (realizaEnvios) {

				var tiemposMin = document.getElementById("tiemposMin").value;
				var tiemposMax = document.getElementById("tiemposMax").value;
				var compraMinima = document.getElementById("compraMinima").value;
				var costoEnvio = document.getElementById("costoEnvio").value;
				if (tiemposMin == "" || tiemposMax == "" || costoEnvio == "") {
					document.getElementById("envios-error").style.display = "block";
					msj = "Los campos tiempo mínima, tiempo máximo y costo de envio son obligatorios";
					document.getElementById("envios-error").textContent = msj;
					rta = false;
				}

			}

			if (rta) {
				document.getElementById("frmc").submit();
			} else {
				return rta;
			}
		}

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

		function agregarTelefono() {

			var table = document.getElementById("tablaTelefonos");
			var tableAnterior = document
					.getElementById("tablaTelefonosAnteriores");
			var telefono = document.getElementById("telefono").value;

			if (telefono != "") {

				/*
				 *	Contar telefonos agregados anteriormente + agregados en la edición
				 *	y sumar uno al nuevo nro agregado.
				 */
				var rowCount = table.rows.length;
				var row = table.insertRow(rowCount);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);

				cell1.innerHTML = telefono;

				var element1 = document.createElement('input');
				element1.id = "telefonos" + (rowCount - 1) + ".numero";
				element1.name = "telefonos[" + (rowCount - 1) + "].numero";
				element1.type = 'hidden';
				element1.value = telefono;
				cell1.appendChild(element1);

				var element2 = document.createElement('button');
				element2.setAttribute("id", "deleteRow" + (rowCount - 1));
				element2.setAttribute("name", "deleteRow" + (rowCount - 1));
				element2
						.setAttribute("class",
								"btn-floating btn-small btn waves-effect waves-light red");
				element2.setAttribute("type", "button");
				element2.setAttribute("onclick",
						"borrarTelefono(this.parentNode.parentNode.rowIndex);");

				var element3 = document.createElement('i');
				element3.setAttribute("class", "small material-icons");
				element3.innerText = "remove";
				element2.appendChild(element3);
				cell2.appendChild(element2);

				document.getElementById("telefono").value = "";
				document.getElementById("telefono").focus();
			}
		}

		function borrarTelefono(i) {
			document.getElementById('tablaTelefonos').deleteRow(i)
		}

		function agregarHorario() {

			var table2 = document.getElementById("tablaHorarios");
			var posicion = document.getElementById("dias").options.selectedIndex; //posicion
			var dia = document.getElementById("dias").options[posicion].value; //valor
			var horaDesde = document.getElementById("horaDesde").value;
			var horaHasta = document.getElementById("horaHasta").value;

			if (dia != "" && horaDesde != "" && horaHasta != "") {

				var rowCount = table2.rows.length;
				var row = table2.insertRow(rowCount);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);

				cell1.innerHTML = dia;
				cell2.innerHTML = horaDesde + " - " + horaHasta;

				var element4 = document.createElement('input');
				element4.id = "horarios" + (rowCount - 1) + ".dia";
				element4.name = "horarios[" + (rowCount - 1) + "].dia";
				element4.type = 'hidden';
				element4.value = dia;
				cell1.appendChild(element4);

				var element4 = document.createElement('input');
				element4.id = "horarios" + (rowCount - 1) + ".horaDesde";
				element4.name = "horarios[" + (rowCount - 1) + "].horaDesde";
				element4.type = 'hidden';
				element4.value = horaDesde;
				cell2.appendChild(element4);

				var element5 = document.createElement('input');
				element5.id = "horarios" + (rowCount - 1) + ".horaHasta";
				element5.name = "horarios[" + (rowCount - 1) + "].horaHasta";
				element5.type = 'hidden';
				element5.value = horaHasta;
				cell2.appendChild(element5);

				/*Botón borrar en tabla*/
				var element2 = document.createElement('button');
				element2.setAttribute("id", "deleteRow" + (rowCount - 1));
				element2.setAttribute("name", "deleteRow" + (rowCount - 1));
				element2
						.setAttribute("class",
								"btn-floating btn-small btn waves-effect waves-light red");
				element2.setAttribute("type", "button");
				element2.setAttribute("onclick",
						"borrarHorario(this.parentNode.parentNode.rowIndex);");

				var element3 = document.createElement('i');
				element3.setAttribute("class", "small material-icons");
				element3.innerText = "remove";
				element2.appendChild(element3);
				cell3.appendChild(element2);
				/*End Botón borrar en tabla*/

				document.getElementById("horaDesde").value = "";
				document.getElementById("horaHasta").value = "";
				document.getElementById("dias").focus();
			}
		}

		function borrarHorario(i) {
			document.getElementById('tablaHorarios').deleteRow(i)
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

		function eliminarTelefono(telefono, id, comercio) {

			swal({
				title : 'Estás seguro?',
				text : "El telefono '" + telefono + "' será eliminado",
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
					Acá se usa el servlet BorrarTelefonoComercio
					 */
					// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
					$.post('BorrarTelefonoComercioServlet', {
						idComercio : comercio,
						idTelefono : id,
					}, function(responseJson) {
						$('#filaTel_' + id).remove();
					});
				}
			});
		}

		function eliminarHorario(dia, desde, hasta, id, comercio) {

			swal({
				title : 'Estás seguro?',
				text : "El horario '" + dia + " de " + desde + " a " + hasta
						+ "' será eliminado",
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
					Acá se usa el servlet BorrarHorarioComercio
					 */
					// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
					$.post('BorrarHorarioComercioServlet', {
						idComercio : comercio,
						idHorario : id,
					}, function(responseJson) {
						$('#filaHor_' + id).remove();
					});
				}
			});
		}
	</script>

</body>
</html>