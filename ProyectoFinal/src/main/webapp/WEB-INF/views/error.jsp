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
<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="<c:url value="/resources/css/materialize.css" />"
	type="text/css" rel="stylesheet" media="screen,projection" />
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css" />" />

<title>Index</title>
</head>
<body>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">

			<br> <br>
			<div id="card-alert"
				class="card red lighten-5 alert alert-info alert-dismissable">
				<div class="card-content red-text">
					<i class="material-icons center">error</i>
					<p>
						<b>Error de sistema:</b> ${error}
					</p>
				</div>
			</div>
			<div class="row center">
				<a class="waves-effect waves-light btn grey lighten-2 black-text"
					onClick="goBack()">Volver</a>
			</div>

		</div>
	</div>

	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>

	<script type="text/javascript">
  
  function goBack() {
	    window.history.back();
	}
  
  /*Modal Registrate*/
  $('#modal1').modal('close');
  $('#modal1').modal('open');
  
  /*Modal Login*/
  $('#modalLogin').modal('close');
  $('#modalLogin').modal('open');  
  
  $(document).ready(function(){
	    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
	    $('.modal').modal();
	  });  
  
  /*Mostrar SideNav (Menu lateral)*/
  function mostrarSideNav() {
	// Show sideNav
	$('.button-collapse').sideNav('show');
  }
  
  /*Ocultar SideNav (Menu lateral)*/
  function ocultarSideNav() {
	// Hide sideNav
	$('.button-collapse').sideNav('hide');		
   }
  
  /*Carousel*/
  $('.carousel.carousel-slider').carousel({full_width: true});
  </script>

</body>
</html>