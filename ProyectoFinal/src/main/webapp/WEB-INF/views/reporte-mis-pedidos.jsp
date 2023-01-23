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
<link href="<c:url value="/resources/css/style.css" />" type="text/css"
	rel="stylesheet" media="screen,projection" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/usuarios.css" />" />
<script src="http://code.jquery.com/jquery-latest.js"></script>
<!-- Botón cerrar en mensajes de error -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Sweet Alert -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/sweetalert.css" />">

<title>ReporteMisPedidos</title>
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

				<c:if test="${misVentas.size()!= 0}">

					<div class="col s12">
						<div class="material-table">
							<div class="table-header">
								<span class="table-title">Mis Pedidos</span>

								<div class="actions">
									<a href="#"
										class="search-toggle waves-effect btn-flat nopadding"><i
										class="material-icons">search</i></a>
								</div>
							</div>
							<table id="tableData"
								class="dataTable no-footer bordered highlight"
								style="text-align: left;">
								<thead>
									<tr>
										<th width="5%">Nro.</th>
										<th width="18%">Fecha y hora</th>
										<th width="15%">Comercio</th>
										<th width="15%">Dirección</th>
										<th width="24%">Estado</th>
										<th width="10%">Total($)</th>
										<th class="center" width="15%">Opciones</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="ped" value="0" />
									<c:forEach items="${misPedidos}" var="ped">
										<tr>
											<td>${ped.nroPedido}</td>
											<td><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
													value="${ped.fechaHora}" /></td>
											<td>${ped.comercio.descripcion}</td>
											<td>${ped.direccion}${ped.nro}${ped.piso} ${ped.depto}</td>
											<td>
												<div class="chip ${ped.ultimoEstado.color}">
													<b>${ped.ultimoEstado.descripcion}</b>
												</div>
											</td>
											<td>${ped.total}</td>
											<td class="center"><sec:authorize
													access="hasAnyAuthority('ADMIN', 'MIS_PED_SU', 'MIS_PED_CU', 'MIS_PED_RU')">
													<a id="enlace"
														href="<c:url value='/pedidos-${ped.idPedido}-view' />"
														class="btn-floating btn-medium waves-effect waves-light light-blue"><i
														class="material-icons">remove_red_eye</i></a>
												</sec:authorize> 
												<!--  
												&nbsp; <sec:authorize
													access="hasAnyAuthority('ADMIN', 'MIS_PED_SU', 'MIS_PED_CU')">
													<a href="<c:url value='/pedidos-${ped.idPedido}-repeat'/>"
														class="btn-floating btn-medium waves-effect waves-light deep-orange accent-3"><i
														class="material-icons">repeat</i></a>
												</sec:authorize>
												-->
												</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<span class="details"> </span>

				</c:if>
				<c:if test="${misVentas.size()==0}">
					<div style="text-align: center">
						<p style="color: red; font: bold;">No existen pedidos
							realizados</p>
					</div>
				</c:if>
				<br>
			</div>

		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>
	<script src="<c:url value="resources/js/sweetalert.min.js" />"></script>
	<script src="<c:url value="resources/js/turn.js" />"></script>
	<script
		src="<c:url value="resources/js/material.sortable.datatable.js" />"></script>
	<script src="<c:url value="resources/js/dataTables.materialize.js" />"></script>

	<script type="text/javascript">
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
	</script>

</body>
</html>