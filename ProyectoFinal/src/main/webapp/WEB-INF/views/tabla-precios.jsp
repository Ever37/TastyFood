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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/tabla-precios.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css" />" />
<link href="<c:url value="/resources/css/animate.css" />"
	type="text/css" rel="stylesheet" media="screen,projection" />
<script src="http://code.jquery.com/jquery-latest.js">
	
</script>

<title>Tabla de precios</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">

			<!-- Contenedor -->
			<div class="pricing-wrapper clearfix">
				<!-- Titulo -->
				<h1 class="pricing-table-title">Elegí tu plan</h1>

				<div class="pricing-table">
					<h3 class="pricing-title deep-orange darken-2">BÁSICO</h3>
					<div class="price">
						$49<sup>99 / mes</sup>
					</div>
					<!-- Lista de Caracteristicas / Propiedades -->
					<ul class="table-list">
						<li>Publicidad <span>en Página Principal </span><span
							class="unlimited deep-orange darken-2">ilimitada</span></li>
						<li>LLEGÁ A TUS CLIENTES!</li>
					</ul>
					<!-- Contratar / Comprar -->
					<div class="table-buy">
						<p>
							$49<sup>99 / mes</sup>
						</p>
						<a href="${pageContext.request.contextPath}/contacto"
							class="pricing-action deep-orange darken-2">Solicitar</a>
					</div>
				</div>

				<div class="pricing-table recommended">
					<h3 class="pricing-title blue darken-2">PROFESIONAL</h3>
					<div class="price">
						$69<sup>99 / mes</sup>
					</div>
					<!-- Lista de Caracteristicas / Propiedades -->
					<ul class="table-list">
						<li>Gestor de pedidos</li>
						<li>Perfil propio</li>
						<li>Productos <span class="unlimited blue darken-2">ilimitados</span></li>
						<li>Reporte<span> de pedidos</span></li>
						<li>Publicidad <span>en catálogo de comercios</span></li>
						<li>POTENCÍA TUS VENTAS!</li>
					</ul>
					<!-- Contratar / Comprar -->
					<div class="table-buy">
						<p>
							$69<sup>99 / mes</sup>
						</p>
						<a href="${pageContext.request.contextPath}/contacto"
							class="pricing-action blue darken-2">Solicitar</a>
					</div>
				</div>

				<div class="pricing-table">
					<h3 class="pricing-title red accent-4">PREMIUM</h3>
					<div class="price">
						$99<sup> 99/ mes</sup>
					</div>
					<!-- Lista de Caracteristicas / Propiedades -->
					<ul class="table-list">
						<li>Gestor de pedidos</li>
						<li>Perfil propio</li>
						<li>Productos <span class="unlimited red accent-4">ilimitados</span></li>
						<li>Reporte<span> de pedidos</span></li>
						<li>Publicidad <span>en catálogo de comercios</span></li>
						<li>Publicidad <span>en Página Principal </span><span
							class="unlimited red accent-4">ilimitada</span></li>
						<li>POTENCÍA TUS VENTAS Y LLEGÁ A TUS CLIENTE!</li>
					</ul>
					<!-- Contratar / Comprar -->
					<div class="table-buy">
						<p>
							$99<sup> 99/ mes</sup>
						</p>
						<a href="${pageContext.request.contextPath}/contacto"
							class="pricing-action red accent-4">Solicitar</a>
					</div>
				</div>
			</div>
			<br>
		</div>
	</div>
	<br>
	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="<c:url value="resources/js/materialize.js" />"></script>
	<script src="<c:url value="resources/js/init.js" />"></script>
	<script type="text/javascript">
		$('#planes').addClass('active');

		$('#logo').addClass('animated bounceIn');

		function validar(size) {
			var rta = true
			if (size == 0) {
				rta = false;
			}
			return rta;
		}

		$(document).ready(function() {
			$('.collapsible').collapsible();
		});

		$(document).ready(function() {
			// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
			$('.modal').modal();
		});

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