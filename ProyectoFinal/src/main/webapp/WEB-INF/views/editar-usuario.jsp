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
  
	function finalizar(flag) {
		
		var URLactual = window.location.toString();
		var pos = URLactual.indexOf("update");
		URLactual = URLactual.substring(pos, 0);
		document.getElementById("frmc").action = URLactual + "update";						

		document.getElementById("frmc").submit();				
		return val;
	}
  
  </script>

<title>EditarUsuario</title>
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
					<i class="material-icons">edit</i>&nbsp;&nbsp;Editar usuario
				</h5>
				<form:form class="col s12" id="frmc" method="POST"
					modelAttribute="usuario" onsubmit="return finalizar(${profile})"
					role="form">
					<form:hidden path="idUsuario" />



					<div class="row right">
						<c:if test="${profile == 'false' }">
							<div class="details">
								<a href="<c:url value='usuarios-listado' />" id="listUsuario"
									class="btn-floating btn-medium btn tooltipped waves-effect waves-light blue"
									data-position="left" data-delay="50" data-tooltip="Listado">
									<i class="material-icons">list</i>
								</a>
							</div>
						</c:if>
						<br>
						<div class="details">
							<a href="<c:url value='usuarios-${id}-password' />" id="enlace"
								class="btn-floating btn-medium btn tooltipped waves-effect waves-light yellow darken-2"
								data-position="left" data-delay="50"
								data-tooltip="Cambiar contraseña"> <i class="material-icons">vpn_key</i>
							</a>
						</div>
						<br>

						<sec:authorize 
						access="hasAnyAuthority('ADMIN', 'MICOMER_SU', 'MICOMER_CU', 'MICOMER_RU')">						
						<c:if test="${comercio == null && grupo == vendedor}">
							<div class="details">
								<a href="<c:url value='comercios-${id}-new' />" id="enlace"
									class="btn-floating btn-medium btn tooltipped waves-effect waves-light purple lighten-2"
									data-position="left" data-delay="50"
									data-tooltip="Nuevo comercio"> <i class="material-icons">home</i>
								</a>
							</div>
						</c:if>

						<c:if test="${comercio != null || grupo != vendedor}">
							<button type="button"
								class="btn-floating btn-medium btn tooltipped waves-effect waves-light purple lighten-2"
								disabled>
								<i class="material-icons">home</i>
							</button>
						</c:if>
						</sec:authorize>
					</div>
					<br>
					<div class="modal-content">
						<br> <br> <br>
						<div class="row col s12">
							<div class="row">
								<div class="input-field col s6">
									<form:input path="grupo.descripcion" id="tipo" type="text"
										class="black-text" maxlength="30" readOnly="readOnly" />
									<label for="tipo">Tipo</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="nombre" id="nombre" type="text"
										maxlength="30" onkeypress="return soloLetras(event)" />
									<label for="nombre">Nombre</label>
								</div>
								<div class="input-field col s6">
									<form:input path="apellido" id="apellido" type="text"
										maxlength="30" onkeypress="return soloLetras(event)" />
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
										maxlength="15" onKeyPress="return soloNumeros(event)" />
									<label for="telefono">Telefono</label>
								</div>
								<div class="input-field col s6">
									<form:input path="celular" id="celular" type="tel"
										maxlength="15" onKeyPress="return soloNumeros(event)" />
									<label for="celular">Celular</label>
								</div>

								<div class="col s6">
									<label id="numero-error" class="error"> </label>
								</div>

							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="email" id="email" type="email" maxlength="30" />
									<label for="email">Email</label>
								</div>

								<div class="col s12">
									<label id="email-error" class="error"> </label>
								</div>

							</div>
							<div class="row">
								<div class="input-field col s6">
									<form:input path="nombreUsuario" id="username" type="text"
										maxlength="30" readOnly="readOnly" class="black-text" />
									<label for="username">Nombre de usuario</label>
								</div>
							</div>
							<div class="row" id="observacionGeneral">
								<div class="input-field col s6">
									<form:textarea path="observaciones" id="observaciones"
										name="observaciones" class="materialize-textarea" length="150"
										maxlength="150" />
									<label for="observaciones">Observaciones</label>
								</div>
							</div>
							<div class="row">
								<p>Recibir notificaciones por email</p>
								<div class="input-field col s3">
									<p>
										<form:radiobutton path="notificaciones" value="S"
											name="grupoA" id="notificacionSi" class="with-gap" />
										<label for="notificacionSi">Si</label>
									</p>
								</div>
								<div class="input-field col s3">
									<p>
										<form:radiobutton path="notificaciones" value="N"
											name="grupoA" id="notificacionNo" class="with-gap" />
										<label for="notificacionNo">No</label>
									</p>
								</div>
							</div>
						</div>

					</div>
					<br>
					<div class="row center">
						<a class="waves-effect waves-light btn grey lighten-2 black-text"
							onClick="goBack()">Volver</a>
						<button onClick="return validarEdicion();"
							class="waves-effect waves-light btn orange accent-3">
							Guardar</button>
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
	<script src="<c:url value="resources/js/sweetalert.min.js" />"></script>

	<script type="text/javascript">
  
  function validarEmail(valor) {
	  var expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  if (expr.test(valor)){
	   return true;
	  } else {
	   return false;
	  }
	}
  
  function validarEdicion() {
		
		document.getElementById("nombre-error").textContent = "";
		document.getElementById("apellido-error").textContent = "";
		document.getElementById("numero-error").textContent = "";
		document.getElementById("email-error").textContent = "";
		
	  	var rta = true;
		
		var msj = "";
		var nombre = document.getElementById("nombre").value;
		var apellido = document.getElementById("apellido").value;
		var telefono = document.getElementById("telefono").value;
		var celular = document.getElementById("celular").value;
		var email = document.getElementById("email").value;
		
		if(nombre == ""){
			document.getElementById("nombre-error").style.display = "block";
			msj = "Este campo es obligatorio";
			document.getElementById("nombre-error").textContent = msj;
			rta = false;
		}
		
		if(apellido == ""){
			document.getElementById("apellido-error").style.display = "block";
			msj = "Este campo es obligatorio";
			document.getElementById("apellido-error").textContent = msj;
			rta = false;
		}
		
		if(telefono == "" && celular == ""){
			document.getElementById("numero-error").style.display = "block";
			msj = "Ingrese al menos un número";
			document.getElementById("numero-error").textContent = msj;
			rta = false;
		}		
		
		if(email != "") {
			if(!validarEmail(email)) {
				document.getElementById("email-error").style.display = "block";
				msj = "El email no tiene un formato correcto";
				document.getElementById("email-error").textContent = msj;
				rta = false;
			}			
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
  
  function comprobarContrasena() {
		var clave1 = document.getElementById("password").value;
	   	var clave2 = document.getElementById("repetirpassword").value;

	   	if (clave1 != clave2) {
	   		
	   	} 	 
  }

  </script>

</body>
</html>