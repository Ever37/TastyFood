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

<title>EditarConsulta</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">
			<br>

			<div class="container">
				<h5 class="flow-text">
					<i class="material-icons">edit</i>&nbsp;&nbsp;Editar consulta
				</h5>
				<form:form class="col s12" id="frmc" method="POST"
					modelAttribute="consulta" onsubmit="return finalizar()" role="form">
					<form:hidden path="idConsulta" />
					<div class="row right">
						<div class="details">
							<a href="<c:url value='consultas-listado' />" id="listConsulta"
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
									<form:input path="nombreRemitente" id="nombreRemitente"
										type="text" class="black-text" readOnly="readOnly" />
									<label for="nombreRemitente">Remitente</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="emailRemitente" id="emailRemitente"
										type="text" class="black-text" readOnly="readOnly" />
									<label for="emailRemitente">Email</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<input value="${fechaAlta}" id="fechaAlta" type="text"
										class="black-text" readOnly="readOnly" /> <label
										for="fechaAlta">Fecha</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="asunto" id="asunto" type="text"
										class="black-text" readOnly="readOnly" />
									<label for="asunto">Asunto</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:textarea path="mensaje" id="mensaje" name="mensaje"
										class="materialize-textarea black-text" readOnly="readOnly" />
									<label for="mensaje">Mensaje</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:textarea path="respuesta" id="respuesta" name="respuesta"
										class="materialize-textarea" length="500" maxlength="200"
										required="required" autofocus="autofocus" />
									<label for="mensaje">Respuesta</label>
								</div>
								<br> <br> <br>
								<div class="col s6">
									<label id="respuesta-error" class="error"> </label>
								</div>
							</div>
						</div>

					</div>
					<br>
					<div class="row center">
						<a class="waves-effect waves-light btn grey lighten-2 black-text"
							onClick="goBack()">Volver</a>
						<button onClick="return validarEdicion();"
							class="waves-effect waves-light btn orange accent-3">Enviar</button>
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
  
  function validarEdicion(){
	  
		document.getElementById("respuesta-error").textContent = "";
		
		var rta = true;
		
		var msj = "";
		var respuesta = document.getElementById("respuesta").value;
		
		if(respuesta == ""){
			document.getElementById("respuesta-error").style.display = "block";
			msj = "Este campo es obligatorio";
			document.getElementById("respuesta-error").textContent = msj;
			rta = false;
		}
		
		if(rta) {
			document.getElementById("frmc").submit();
		} else {
			return rta;
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


  </script>

</body>
</html>