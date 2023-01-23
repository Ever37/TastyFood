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

<title>EditarCategoria</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">
			<br>

			<div class="container">
				<h5 class="flow-text">
					<i class="material-icons">add</i>&nbsp;&nbsp;Editar categoría
				</h5>
				<form:form class="col s12" id="frmc" method="POST"
					modelAttribute="categoria" onsubmit="return finalizar()"
					role="form">
					<form:hidden path="idCategoria" />
					<div class="row right">
						<div class="details">
							<a href="<c:url value='categorias-listado' />" id="listCategoria"
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
									<form:input path="descripcion" id="descripcion" type="text"
										class="validate" maxlength="45"
										onkeypress="return soloLetras(event)" required="required" />
									<label for="nombre">Descripción</label>
								</div>
								<br> <br>
								<div class="col s6">
									<label id="descripcion-error" class="error"> </label>
								</div>
							</div>
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
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>


	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>

	<script type="text/javascript">
  
  function validarEdicion() {
		document.getElementById("descripcion-error").textContent = "";
		
		var rta = true;
		
		var msj = "";
		var descripcion = document.getElementById("descripcion").value;
		
		if(descripcion == ""){
			document.getElementById("descripcion-error").style.display = "block";
			msj = "Este campo es obligatorio";
			document.getElementById("descripcion-error").textContent = msj;
			rta = false;
		}
		
		if(rta) {
			document.getElementById("frmc").submit();
		} else {
			return rta;
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
  
  /*Select*/
  $(document).ready(function() {
	    $('select').material_select();
	  });  
  $('select').material_select('destroy');


  </script>

</body>
</html>