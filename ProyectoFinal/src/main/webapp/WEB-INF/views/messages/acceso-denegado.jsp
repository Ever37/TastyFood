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

<title>Acceso Denegado</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
	<div class="section no-pad-bot" id="index-banner">
		<div class="white">
			<br>

			<div class="container">

				<div class="row center">
					<img alt="ACCESO DENEGADO"
						src="<c:url value="/resources/img/ad.png" />">
				</div>
				<br>

			</div>

			<div class="row center">
				<a class="waves-effect waves-light btn grey lighten-2 black-text"
					onClick="goBack()">Volver</a>
			</div>
			<br>

		</div>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>
	<script type="text/javascript">
 	 	
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