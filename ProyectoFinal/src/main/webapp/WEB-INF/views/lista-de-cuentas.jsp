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
<link href="<c:url value="/resources/css/style.css" />" type="text/css"
	rel="stylesheet" media="screen,projection" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/usuarios.css" />" />
<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- Recurso externo para las fechas -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.99.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.99.0/js/materialize.min.js"></script>


<style type="text/css">
.modal {
	overflow: visible;
	width: 50% !important;
}

.error {
	color: red;
}
</style>

<title>ResumenCuenta</title>
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
					<c:if test="${success != null}">
						<div id="card-alert"
							class="card green lighten-5 alert alert-info alert-dismissable">
							<div class="card-content green-text">
								<p>${success}</p>
							</div>
						</div>
					</c:if>
				</div>
				<form:form modelAttribute="busqueda" class="col s12"
					id="formBusqueda" method="POST" role="form">
					<fieldset class="grey lighten-4">
						<legend>
							<b>Buscar cuentas por comercio</b>
						</legend>
						<div class="col s12">
							<div class="row">

								<div class="input-field col s1"></div>
								<div class="input-field col s6">
									<form:select name="comercio" id="comercio"
										path="comercio.idComercio"
										onChange="cambiaComercio(this.value);">
										<option value="" selected>Seleccione un comercio</option>
										<c:set var="c" value="0" />
										<c:forEach items="${comercios}" var="c">
											<option value="${c.idComercio}" id="comercio">${c.descripcion}</option>
										</c:forEach>
									</form:select>
								</div>
								<div class="input-field col s1"></div>
								<div class="input-field col s2">
									<button onClick="return buscarCuentas();"
										class="waves-effect waves-light btn orange accent-3">Buscar</button>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s1"></div>
								<div class="col s6">
									<label id="comercio-error" class="error"> </label>
								</div>
							</div>
						</div>
					</fieldset>
					<br>
					<br>
				</form:form>
				<c:if test="${cuentas.size() > 0}">
					<div class="col s12">
						<div class="material-table" id="imprimir">
							<div class="table-header">
								<span class="table-title">Cuentas -
									${comercio.descripcion} <a
									href="<c:url value='comercios-${comercio.idComercio}-view' />"
									id="verComercio"
									class="btn-floating btn-medium btn waves-effect waves-light purple lighten-2"
									target="_blank"> <i class="material-icons">home</i>
								</a>
								</span>
								<div class="actions">
									<a href="javascript:imprimir();"
										class="search-toggle waves-effect btn-flat nopadding"><i
										class="material-icons">print</i></a> <a href="#"
										class="search-toggle waves-effect btn-flat nopadding"><i
										class="material-icons">search</i></a>
								</div>
							</div>
							<table id="tableData"
								class="dataTable no-footer bordered highlight"
								style="text-align: left;">
								<thead>
									<tr>
										<th width="30%">Comercio</th>
										<th width="15%">Ventas concretadas</th>
										<th width="15%">Monto total ($)</th>
										<th width="15%">Comisión ($)</th>
										<th width="15%">Comisión (%)</th>
										<th class="center" width="10%">Detalle</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="c" value="0" />
									<c:forEach items="${cuentas}" var="c">
										<tr>
											<td>${c.comercio.descripcion}</td>
											<td>${c.pedidos.size()}</td>
											<td>${c.montoTotal}</td>
											<td>${c.comision}</td>
											<td>${c.comisionPorcentual}</td>
											<td class="center"><sec:authorize
													access="hasAnyAuthority('ADMIN', 'RE_CUENTA_SU', 'RE_CUENTA_CU', 'RE_CUENTA_RU')">

													<div style="display: none;">
														<form:form id="frm_${c.comercio.idComercio}"
															target="_blank" name="frm_${c.comercio.idComercio}"
															modelAttribute="busquedaPedidos">

															<form:hidden path="desde" value="${c.desde}"
																name="fechaDesde_${c.comercio.idComercio}"
																id="fechaDesde_${c.comercio.idComercio}" />
															<form:hidden path="hasta" value="${c.hasta}"
																name="fechaHasta_${c.comercio.idComercio}"
																id="fechaHasta_${c.comercio.idComercio}" />
															<form:hidden path="comercio.idComercio"
																value="${c.comercio.idComercio}"
																id="idComercio_${c.comercio.idComercio}" />
															<form:hidden path="comercio.descripcion"
																value="${c.comercio.descripcion}"
																id="descripcionComercio_${c.comercio.idComercio}" />
														</form:form>
													</div>

													<a id="verPedidos_${c.comercio.idComercio}"
														href="javascript:verListaPedidosComercio(${c.comercio.idComercio});"
														class="btn-floating btn-medium waves-effect waves-light light-blue">
														<i class="material-icons">remove_red_eye</i>
													</a>


												</sec:authorize></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

					<div class="row">
						<div class="row center">
							<a class="waves-effect waves-light btn grey lighten-2 black-text"
								onClick="goBack()">Volver</a>
						</div>
					</div>

				</c:if>
				<c:if test="${cuentas.size() == 0}">
					<div style="text-align: center">
						<p style="color: red; font: bold;">No existen cuentas
							generadas para el comercio</p>
					</div>
				</c:if>
				<span class="details"> </span> <br>
			</div>
		</div>
	</div>
	<br><br><br><br><br>
	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<!--  Scripts-->
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>
	<script src="<c:url value="resources/js/turn.js" />"></script>
	<script
		src="<c:url value="resources/js/material.sortable.datatable.js" />"></script>
	<script src="<c:url value="resources/js/dataTables.materialize.js" />"></script>

	<script type="text/javascript">
		function buscarCuentas() {

			document.getElementById("comercio-error").textContent = "";
			var rta = true;
			var combo = document.getElementById("comercio");
			var idComercio = combo.options[combo.selectedIndex].value;

			if (idComercio == "") {
				document.getElementById("comercio-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("comercio-error").textContent = msj;
				rta = false;
			}

			var URLactual = window.location.toString();
			var pos = URLactual.indexOf("cuentas");
			URLactual = URLactual.substring(pos, 0);

			document.getElementById('formBusqueda').method = "POST";
			document.getElementById('formBusqueda').action = URLactual
					+ "cuentas-" + idComercio + "-listado";

			if (rta) {
				document.getElementById('formBusqueda').submit();
			} else {
				return false;
			}
		}

		function verListaPedidosComercio(idComercio) {
			//POST A METODO /pedidos-listado
			var URLactual = window.location.toString();
			var pos = URLactual.indexOf("resumen");
			URLactual = URLactual.substring(pos, 0);

			document.getElementById('frm_' + idComercio).method = "POST";
			document.getElementById('frm_' + idComercio).action = URLactual
					+ "pedidos-listado";
			document.getElementById('frm_' + idComercio).submit();

		}

		function imprimir() {
			var objeto = document.getElementById('imprimir'); //obtenemos el objeto a imprimir
			var ventana = window.open('', '_blank'); //abrimos una ventana vacía nueva
			ventana.document.write(objeto.innerHTML); //imprimimos el HTML del objeto en la nueva ventana
			ventana.document.close(); //cerramos el documento
			ventana.print(); //imprimimos la ventana
			ventana.close(); //cerramos la ventana
		}

		function guardarIdPedido(idPedido) {
			//Recupero el idPedido del pedido que elegí para cambiarle el estado.
			document.getElementById("hiddenPedido").value = idPedido;
		}

		$('.datepicker').pickadate({
			selectMonths : true, // Creates a dropdown to control month
			selectYears : 15
		// Creates a dropdown of 15 years to control year
		});

		$(document)
				.ready(
						function() {
							$('#tableData')
									.dataTable(
											{
												"oLanguage" : {
													"sZeroRecords" : "No se encontraron resultados",
													"sStripClasses" : "",
													"sSearch" : "",
													"sSearchPlaceholder" : "Filtrar por alguna columna",
													"sInfo" : "_START_ -_END_ of _TOTAL_",
													"sLengthMenu" : '<span>Filas por página:</span><select class="browser-default">'
															+ '<option value="5">5</option>'
															+ '<option value="10">10</option>'
															+ '<option value="20">20</option>'
															+ '<option value="30">30</option>'
															+ '<option value="40">40</option>'
															+ '<option value="50">50</option>'
															+ '<option value="-1">All</option>'
															+ '</select></div>'
												},
												bAutoWidth : false
											});
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

		$(document).ready(function() {
			// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
			$('.modal').modal();
		});

		$(document).ready(function() {
			$('select').material_select();
		});
	</script>

</body>
</html>