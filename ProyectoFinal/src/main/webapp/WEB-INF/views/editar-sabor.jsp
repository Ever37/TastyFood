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

<title>EditarSabor</title>
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
					<i class="material-icons">add</i>&nbsp;&nbsp;Editar sabor
				</h5>
				<form:form class="col s12" id="frmc" method="POST"
					modelAttribute="sabor" onsubmit="return finalizar()" role="form">
					<form:hidden path="idSabor" />
					<form:hidden path="comercio.idComercio" />
					<div class="row right">
						<div class="details">
							<a href="<c:url value='sabores-listado' />" id="listSabor"
								class="btn-floating btn-medium btn tooltipped waves-effect waves-light blue"
								data-position="left" data-delay="50" data-tooltip="Listado">
								<i class="material-icons">list</i>
							</a>
						</div>
					</div>
					<br>
					<div class="modal-content">
						<div class="row col s5">
							<div class="input-field col s6">
								<form:input path="descripcion" id="descripcion" type="text"
									class="validate" maxlength="45"
									onkeypress="return soloLetras(event)" autofocus="autofocus"
									required="required" />
								<label for="descripcion">Descripción</label>
							</div>
						</div>
						<div class="row col s2">
							<p>
								<input type="checkbox" id="activo" onClick="activar();" /> <label
									for="activo" class="black-text">Activo</label>
								<form:hidden path="activo" id="hiddenActivo" />
							</p>
						</div>
					</div>

					<div class="row center">
						<a class="waves-effect waves-light btn grey lighten-2 black-text"
							onClick="goBack()">Volver</a>
						<button type="submit"
							class="waves-effect waves-light btn orange accent-3">Guardar</button>
					</div>
				</form:form>
				<br>
			</div>

		</div>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>


	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>

	<script type="text/javascript">
  
  window.onload = function() {
		checkActivo();
  };
		
  function checkActivo() {
		var activo = document.getElementById("hiddenActivo").value;
		if(activo == 'true') {
			  document.getElementById("activo").checked = true;	  
		} else {
			  document.getElementById("activo").checked = false;	  
		} 
  }
	
  function activar() {
		  if(document.getElementById("activo").checked) {
			  document.getElementById("hiddenActivo").value = true;
		  } else {
			  document.getElementById("hiddenActivo").value = false;
		  }  	
  }
  
  function soloNumeros(e){
		var key = window.Event ? e.which : e.keyCode
		return (key >= 48 && key <= 57)
  }

  function soloLetras(e){
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false
    for(var i in especiales){
         if(key == especiales[i]){
             tecla_especial = true;
             break;
         }
     }

     if(letras.indexOf(tecla)==-1 && !tecla_especial){
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

  </script>

</body>
</html>