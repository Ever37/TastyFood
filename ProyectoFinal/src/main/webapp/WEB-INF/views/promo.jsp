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

<title>PromocionarProducto</title>
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
				<form:form modelAttribute="promo" class="col s12"
					id="formPromo" method="POST" role="form">
					<fieldset class="grey lighten-4">
						<legend>
							<b>Promocionar un producto</b>
						</legend>
						<div class="col s12">
							<div class="row">

								<div class="input-field col s1"></div>
								<div class="input-field col s6">
									<form:select name="producto" id="producto"
										path="producto.idProducto"
										onChange="cambiaProducto(this.value);">
										<option value="" selected>Seleccione un producto</option>
										<c:set var="p" value="0" />
										<c:forEach items="${productos}" var="p">
											<option value="${p.idProducto}" id="producto">${p.descripcion}</option>
										</c:forEach>
									</form:select>
								</div>
								<div class="input-field col s1"></div>
								<div class="input-field col s2">
								<c:choose>
									<c:when test="${error == null}">
									<button onClick="return promocionar();"
										class="waves-effect waves-light btn orange accent-3">PROMOCIONAR</button>
									</c:when>
									<c:otherwise>
									<button onClick="return false;"
										class="waves-effect waves-light btn orange accent-3"disabled>PROMOCIONAR</button>
									</c:otherwise>
								</c:choose>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s1"></div>
								<div class="col s6">
									<label id="producto-error" class="error"> </label>
								</div>
							</div>
						</div>
					</fieldset>
					<br>
					<br>
				</form:form>
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

		function imprimir() {
			var objeto = document.getElementById('imprimir'); //obtenemos el objeto a imprimir
			var ventana = window.open('', '_blank'); //abrimos una ventana vacía nueva
			ventana.document.write(objeto.innerHTML); //imprimimos el HTML del objeto en la nueva ventana
			ventana.document.close(); //cerramos el documento
			ventana.print(); //imprimimos la ventana
			ventana.close(); //cerramos la ventana
		}
	
		function promocionar() {

			document.getElementById("producto-error").textContent = "";
			var rta = true;
			var combo = document.getElementById("producto");
			var idProducto = combo.options[combo.selectedIndex].value;

			if (idProducto == "") {
				document.getElementById("producto-error").style.display = "block";
				msj = "Este campo es obligatorio";
				document.getElementById("producto-error").textContent = msj;
				rta = false;
			}

			var URLactual = window.location.toString();
			var pos = URLactual.indexOf("promocionar");
			URLactual = URLactual.substring(pos, 0);

			document.getElementById('formPromo').method = "POST";
			document.getElementById('formPromo').action = URLactual
				+ "promocionar-" + idProducto + "-producto";

			if (rta) {
				document.getElementById('formPromo').submit();
			} else {
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