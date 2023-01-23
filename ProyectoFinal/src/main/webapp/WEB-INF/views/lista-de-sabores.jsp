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
<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- Botón cerrar en mensajes de error -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Sweet Alert -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/sweetalert.css" />">

<style type="text/css">
.activo {
	color: green;
}

.inactivo {
	color: red;
}
</style>

<title>ListadoSabores</title>
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

				<c:if test="${sabores.size()!= 0}">

					<div class="col s12">
						<div class="material-table">
							<div class="table-header">
								<span class="table-title">Listado de sabores</span>

								<div class="actions">
									<a href="#"
										class="search-toggle waves-effect btn-flat nopadding"><i
										class="material-icons">search</i></a> <a
										href="<c:url value='sabores-new' />" id="addSabor"
										class="btn-floating btn-medium btn tooltipped waves-effect waves-light green"
										data-position="top" data-delay="50" data-tooltip="Agregar">
										<i id="add" class="material-icons">add</i>
									</a>
								</div>
							</div>
							<table id="tableData"
								class="dataTable no-footer bordered highlight"
								style="text-align: left;">
								<thead>
									<tr>
										<th>Descripción</th>
										<th>Estado</th>
										<th>Comercio</th>
										<th class="center">Opciones</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="pro" value="0" />
									<c:forEach items="${sabores}" var="sab">
										<tr>
											<td>${sab.descripcion}</td>
											<td><c:choose>
													<c:when test="${sab.activo}">
														<p class="activo">ACTIVO</p>
													</c:when>
													<c:otherwise>
														<p class="inactivo">INACTIVO</p>
													</c:otherwise>
												</c:choose></td>
											<td>${sab.comercio.descripcion}</td>
											<td class="center"><a id="enlace"
												href="<c:url value='/sabores-${sab.idSabor}-view' />"
												class="btn-floating btn-medium waves-effect waves-light light-blue"
												class=""><i class="material-icons">remove_red_eye</i></a>
												&nbsp; <a
												href="<c:url value='/sabores-${sab.idSabor}-update'/>"
												class="btn-floating btn-medium waves-effect waves-light indigo"
												class=""><i class="material-icons">edit</i></a> &nbsp; <a
												id="enlace"
												class="btn-floating btn-medium waves-effect waves-light  red"
												onClick="return eliminar('${sab.descripcion}','${sab.idSabor}');"
												class=""><i class="material-icons">delete</i></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<span class="details"> </span>

				</c:if>
				<c:if test="${sabores.size()==0}">
					<div style="text-align: center">
						<p style="color: red; font: bold;">No existen sabores cargados</p>
					</div>
					<div class="actions" style="text-align: right">
						<a href="<c:url value='sabores-new' />" id="addSabor"
							class="btn-floating btn-medium btn tooltipped waves-effect waves-light green"
							data-position="top" data-delay="50" data-tooltip="Agregar"> <i
							id="add" class="material-icons">add</i>
						</a>
					</div>
				</c:if>
				<br>
			</div>

		</div>
	</div>
	<br><br><br>
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
  
  $(document).ready(function() {
	  $('#tableData').dataTable({
	    "oLanguage": {
	      "sZeroRecords": "No se encontraron resultados",
	      "sStripClasses": "",
	      "sSearch": "",
	      "sSearchPlaceholder": "Filtrar por alguna columna",
	      "sInfo": "_START_ -_END_ of _TOTAL_",
	      "sLengthMenu": '<span>Filas por página:</span><select class="browser-default">' +
	      	'<option value="5">5</option>' +
	        '<option value="10">10</option>' +
	        '<option value="20">20</option>' +
	        '<option value="30">30</option>' +
	        '<option value="40">40</option>' +
	        '<option value="50">50</option>' +
	        '<option value="-1">All</option>' +
	        '</select></div>'
	    },
	    bAutoWidth: false
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
  
  function eliminar(sabor, id) {
 
	  
	swal({
	  title: 'Estás seguro?',
	  text: "El sabor '" + sabor + "' será eliminado",
	  type: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#F44336',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Si, eliminarlo',
	  closeOnConfirm: false
	},
  

  function(isConfirm){
		  if (isConfirm) {
				var URLactual = window.location.toString();
				var pos = URLactual.indexOf("sabores-listado");
				URLactual = URLactual.substring(pos, 0);
			  	window.location.href = URLactual + 'sabores-' + id +'-delete';
		  } 
	});

  }
			
  </script>

</body>
</html>