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

<title>VerSabor</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">

		<div class="white">
			<br>

			<div class="container">
				<h5 class="flow-text">
					<i class="material-icons">remove_red_eye</i>&nbsp;&nbsp;Ver sabor
				</h5>
				<form:form class="col s12" id="frmc" name="frmc" method="POST"
					modelAttribute="sabor" role="form">
					<form:hidden path="idSabor" />
					<form:hidden path="comercio.idComercio" />
					<div class="row right">
						<div class="details">
							<a href="<c:url value='sabores-listado' />" id="listSabores"
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
									readOnly="readOnly" class="black-text" />
								<label for="descripcion">Descripción</label>
							</div>
						</div>
						<div class="row col s2">
							<p>
								<input type="checkbox" id="activo" readOnly="readOnly"
									disabled="disabled" /> <label for="activo">Activo</label>
								<form:hidden path="activo" id="hiddenActivo" />
							</p>
						</div>
					</div>


					<br>
					<div class="row center">
						<a class="waves-effect waves-light btn grey lighten-2 black-text"
							onClick="goBack()">Volver</a>
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
		window.onload = function() {
			checkActivo();
		};

		function checkActivo() {
			var activo = document.getElementById("hiddenActivo").value;
			if (activo == 'true') {
				document.getElementById("activo").checked = true;
			} else {
				document.getElementById("activo").checked = false;
			}
		}

		function goBack() {
			window.history.back();
		}
	</script>

</body>
</html>