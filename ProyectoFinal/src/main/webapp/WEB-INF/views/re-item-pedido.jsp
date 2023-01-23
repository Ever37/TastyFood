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

<!-- Botn cerrar en mensajes de error -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
.error {
	color: red;
}
</style>

<title>ItemDePedido</title>
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
					<c:if test="${alert != null}">
						<div id="card-alert"
							class="card yellow lighten-5 alert alert-info alert-dismissable">
							<div class="card-content black-text">
								<p>${alert}</p>
							</div>
						</div>
					</c:if>
				</div>
				<form:form method="POST" id="frmitem" name="frmitem"
					modelAttribute="itemPedido"
					action="${pageContext.request.contextPath}/producto-pedido-add">
					<form:hidden id="producto" path="producto.idProducto"></form:hidden>
					<form:hidden id="productoDesc" path="producto.descripcion"></form:hidden>
					<form:hidden id="categoria" path="producto.categoria.idCategoria"></form:hidden>
					<form:hidden id="comercio" path="producto.comercio.idComercio"></form:hidden>

					<c:forEach items="${producto.precios}" var="precios" varStatus="i">
						<form:hidden id="precio" path="precios[${i.index}].idPrecio"></form:hidden>
					</c:forEach>
					<div class="col s20">
						<div class="material-table">
							<div class="table-header">
								<span class="table-title"><i class="material-icons">content_paste</i>
									Item de pedido</span>
							</div>
							<table id="tableData"
								class="dataTable no-footer bordered highlight"
								style="text-align: left;">
								<thead>
									<tr>
										<th>Categora</th>
										<th>Descripcin</th>
										<th>Observaciones</th>
										<th width="10%">Precio</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${itemPedido.producto.categoria.descripcion}</td>
										<td>${itemPedido.producto.descripcion}</td>
										<td>${itemPedido.producto.observaciones}</td>
										<td>${ultimoPrecio}</td>
									</tr>
								</tbody>
							</table>
						</div>

						<div>
							<c:choose>
								<c:when
									test="${itemPedido.producto.categoria.descripcion == 'Helados'}">

									<br>
									<label for="sabores">Sabores:</label>
									<input type="hidden" name="saboresSeleccionados"
										id="saboresSeleccionados" value="${0}">
									<input type="hidden" name="cantSabores" id="cantSabores"
										value="${itemPedido.producto.cantidadGustos}">
									<form:select path="sabores" id="sabores" class="selectpicker"
										data-width="850px" data-container="body" multiple="true"
										onChange="change('${itemPedido.producto.categoria.descripcion}');">
										<option disabled>Seleccione ${itemPedido.producto.cantidadGustos} sabores</option>
										<form:options items="${sabores}" itemValue="idSabor"
											itemLabel="descripcion" />
									</form:select>
									<label id="sabores-error" for="sabores" style="display: none;"
										class="error"> </label>
								</c:when>
								<c:when
									test="${itemPedido.producto.categoria.descripcion == 'Pastas'}">
									<br>

									<div class="row">

										<div class="input-field col s12">
											<form:select path="salsa" id="salsa" name="salsa"
												class="selectpicker"
												onChange="traerPrecio(this.value); change('${itemPedido.producto.categoria.descripcion}');"
												data-width="850px" data-container="body">
												<option value="" selected="selected" disabled>Seleccione una salsa</option>
												<form:options items="${salsas}" itemValue="idSalsa"
													itemLabel="descripcion" />
											</form:select>
										</div>
										<div class="input-field col s2">
											<input id="precioSalsa" type="number" maxlength="45"
												readOnly="readOnly" /> <label for="precioSalsa">Precio</label>
										</div>
										<div class="input-field col s6"></div>
									</div>
									<label id="salsa-error" for="salsa" style="display: none;"
										class="error"> </label>

								</c:when>
							</c:choose>
						</div>

						<div class="row center" id="aclaracionesItem">
							<div class="input-field col s6">
								<form:textarea path="aclaraciones" id="aclaraciones"
									name="aclaraciones" class="materialize-textarea" length="100"
									maxlength="100" autofocus="autofocus" />
								<label for="aclaraciones">Aclaraciones</label>
							</div>
						</div>

						<div class="row center">

							<div class="input-field col s1">
								<label for="cantidad">Cantidad:</label>
							</div>
							<div class="input-field col s5">
								<form:input type="number" value="${1}" path="cantidad" id="cantidad" min="1"
										onKeyPress="return bloqueo(event)" max="50" />
								
								
							</div>		
						</div>
						<div class="col s8">	
							<label id="cantidad-error" class="error"></label>
						</div>	

						<br>
						<div class="row right">
							<div class="details">
								<button
									onClick="return validar('${itemPedido.producto.categoria.descripcion}');"
									class="btn-floating btn-large waves-effect tooltipped waves-light red accent-4"
									data-position="left" data-delay="50"
									data-tooltip="Agregar al carrito">
									<i class="material-icons">add</i>
								</button>
							</div>
						</div>
						<br>

						<div class="row left">
							<div class="details">
								<a
									class="waves-effect waves-light btn grey lighten-2 black-text"
									onClick="goBack()">Volver</a>
							</div>
						</div>
					</div>
				</form:form>
				<br> <br> <br> <span class="details"> </span>
			</div>
		</div>
	</div>

	<!-- Modal Structure -->
	<div id="modal1" class="modal">
		<form:form method="POST" modelAttribute="usuario"
			action="usuarios-new" id="frm" name="frm">

			<div class="modal-content">
				<h4>Regístrate</h4>
				<p>Es totalmente gratis!</p>

				<div class="row col s12">
					<div class="row">
						<div class="input-field col s6">
							<form:input path="nombre" id="nombre" type="text"
								class="validate" maxlength="30"
								onkeypress="return soloLetras(event)" autofocus="autofocus" />
							<label for="nombre">Nombre</label>
						</div>
						<div class="input-field col s6">
							<form:input path="apellido" id="apellido" type="text"
								class="validate" maxlength="30"
								onkeypress="return soloLetras(event)" />
							<label for="apellido">Apellido</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s6">
							<form:input path="telefono" id="telefono" type="tel"
								class="validate" maxlength="15"
								onKeyPress="return soloNumeros(event)" />
							<label for="telefono">Telefono</label>
						</div>
						<div class="input-field col s6">
							<form:input path="celular" id="celular" type="tel"
								class="validate" maxlength="15"
								onKeyPress="return soloNumeros(event)" />
							<label for="celular">Celular</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<form:input path="email" id="email" type="email" class="validate"
								maxlength="30" />
							<label for="email" data-error="incorrecto"
								data-success="correcto">Email</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s6">
							<form:input path="nombreUsuario" id="username" type="text"
								class="validate" maxlength="30" />
							<label for="username">Nombre de usuario</label>
						</div>
						<div class="input-field col s6">
							<form:input path="contrasena" id="password" type="password"
								class="validate" maxlength="15" />
							<label for="password">Contraseña</label>
						</div>
					</div>
					<div class="row">
						<p>Recibir notificacion por email</p>
						<div class="input-field col s3">
							<p>
								<form:radiobutton path="notificaciones" value="S" name="grupoA"
									id="notificacionSi" class="with-gap" />
								<label for="notificacionSi">Si</label>
							</p>
						</div>
						<div class="input-field col s3">
							<p>
								<form:radiobutton path="notificaciones" value="N" name="grupoA"
									id="notificacionNo" class="with-gap" />
								<label for="notificacionNo">No</label>
							</p>
						</div>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<a class="modal-action modal-close waves-effect waves-red btn-flat"
					href="#!">Cancelar</a>
				<button
					class="modal-action modal-close waves-effect waves-green btn-flat"
					type="submit">Guardar</button>
			</div>
		</form:form>
	</div>

	<div id="modalLogin" class="modal" style="width: 35%; height: 60%;">
		<form method="POST" action="<%=request.getContextPath()%>/login"
			id="frmLogin" name="frmLogin">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="modal-content">
				<h4>Login</h4>
				<div class="row col s20">
					<div class="row">
						<div class="input-field col s12">
							<input id="username" name="username" type="text" class="validate"
								maxlength="30" autofocus="autofocus" /> <label for="username">Nombre
								de usuario</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<input id="password" name="password" type="password"
								class="validate" maxlength="15" /> <label for="password">Contraseña</label>
						</div>
					</div>
					<div class="modal-footer col s10">
						<button
							class="modal-action modal-close waves-effect waves-green btn-flat">Ingresar</button>
						<a
							class="modal-action modal-close waves-effect waves-red btn-flat"
							href="#!">Cancelar</a>
					</div>
				</div>
			</div>

		</form>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>
	<script src="<c:url value="resources/js/sweetalert.min.js" />"></script>
	<script src="<c:url value="resources/js/turn.js" />"></script>

	<script type="text/javascript">
  
	function bloqueo(e) {
		return false;
	}
	
  	function change(categoria) {
  		if(categoria == "Helados") {
  			document.getElementById("sabores-error").style.display = "none";
  			document.getElementById("sabores-error").textContent = ""; 			
  		} else if(categoria == "Pastas") {
  			document.getElementById("salsa-error").style.display = "none";
  			document.getElementById("salsa-error").textContent = "";  			
  		}
  	}
  
	function validar(categoria) {
		
		var rta = true;
		
		document.getElementById("cantidad-error").textContent = "";
		
		var msj = "";
		var msj2 = "";
		var cantidad = document.getElementById("cantidad").value;

		if (cantidad == "") {
			document.getElementById("cantidad-error").style.display = "block";
			msj2 = "Este campo es obligatorio";
			document.getElementById("cantidad-error").textContent = msj2;
			rta = false;
		}
		
		//Validación sabores de helado.
		if(categoria == "Helados") {
			var cant = document.getElementById("cantSabores").value;
			var cantSelect = 0;
			var obj = document.getElementById("sabores");
			for (var i=0; opt=obj.options[i];i++) 
				    if (opt.selected) {
				    	cantSelect++ ;
				    }			
			if(cantSelect == 0)
			{
				if(cant == 1) {
					msj = "Seleccione al menos " + cant + " sabor para agregar el item al carrito.";
				} else {
					msj = "Seleccione hasta " + cant + " sabores para agregar el item al carrito.";
				}

				rta = false;
			} else if (cantSelect > cant) {
				if(cant == 1) {
					msj = "Seleccione solo " + cant + " sabor para agregar el item al carrito.";	
					
				} else {
					msj = "Seleccione solo " + cant + " sabores para agregar el item al carrito.";			
				}
				//document.getElementById("error").textContent = msj;
				rta = false;
			}
				
			if(rta) {
				document.getElementById("frmitem").submit();
			} else {
				document.getElementById("sabores-error").style.display = "block";
				document.getElementById("sabores-error").textContent = msj;
				return rta;
			}	
			
		//Validación salsa de pastas.
		} else if(categoria == "Pastas") {
			
			var combo = document.getElementById("salsa");
			var idSalsa = combo.options[combo.selectedIndex].value;
			
			if(idSalsa == "")
			{
				msj = "Seleccione una salsa para agregar el item al carrito.";
				rta = false;
			} 
				
			if(rta) {
				document.getElementById("frmitem").submit();
			} else {
				document.getElementById("salsa-error").style.display = "block";
				document.getElementById("salsa-error").textContent = msj;
				return rta;
			}		
		} else {
			if(rta) {
				document.getElementById("frmitem").submit();				
			}

		}

 
	}
  
 function traerPrecio(salsa) {
	$.post('MostrarPrecioSalsaServlet', {
		idSalsa : salsa,
	}, function(responseJson) {
		$('#precioSalsa').focus();
		$('#precioSalsa').addClass("black-text");
		document.getElementById("precioSalsa").value = responseJson.precio;	
	});
 }
  
  $(document).ready(function() {
	    $('select').material_select();
	  });
	       
  $(document).ready(function(){
	    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
	    $('.modal').modal();
	});
  $('#addCart').modal('open');
  $('#addCart').modal('close');
 
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
  
  function eliminar(usuario, id) {
 
	  
	swal({
	  title: 'Ests seguro?',
	  text: "El usuario '" + usuario + "' ser eliminado",
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
				var pos = URLactual.indexOf("usuarios-listado");
				URLactual = URLactual.substring(pos, 0);
			  	window.location.href = URLactual + 'usuarios-' + id +'-delete';
		  } 
	});

  }
  
  $('#modal1').modal('close');
  $('#modal1').modal('open');
  
  $('#modalLogin').modal('close');
  $('#modalLogin').modal('open');  
  
  $(document).ready(function(){
	    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
	    $('.modal').modal();
  }); 
			
  </script>

</body>
</html>
