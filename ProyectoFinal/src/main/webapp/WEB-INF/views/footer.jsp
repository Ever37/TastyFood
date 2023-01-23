<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<footer class="page-footer orange">
	<div class="container">
		<div class="row">

			<!--  
			<a id="logo-container" href="${pageContext.request.contextPath}/"
				class="brand-logo"> <img style="width: 20%"
				class="option animated flipInY"
				src="<c:url  value="/resources/img/TastyFood.png" />" /></a>
			-->
			
			<div class="col l3 s8">
				<h5 class="white-text">Nosotros</h5>
				<ul>
				 <li><a class="white-text" href="${pageContext.request.contextPath}/nosotros">Quienes Somos</a></li>
				 <li><a class="white-text" href="${pageContext.request.contextPath}/faq">Preguntas Frecuentes</a></li>
				 <li><a class="white-text" href="${pageContext.request.contextPath}/contacto">Contactanos</a></li>
				 <sec:authorize access="hasAnyAuthority('ADMIN', 'MICOMER_SU', 'MICOMER_CU', 'MICOMER_RU')">
				 <li><a class="white-text" href="${pageContext.request.contextPath}/ayuda" target="_blank">Ayuda en línea</a></li>
				 </sec:authorize>
				</ul>
			</div>
			<div class="col l3 s8">
				<h5 class="white-text">Seguinos en</h5>
				<ul>
					<li>
					<img id="logo" name="logo" style="width: 60%"
						src="<c:url  value="/resources/img/redes_sociales.png" />" />
					</li>
				</ul>
			</div>		
			<div class="col l3 s8">
			</div>
			<div class="col l3 s8">
				<ul>
					<li>
					<img id="logo" name="logo" style="width: 100%"
						src="<c:url  value="/resources/img/Logo-horizontal.png" />" />
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="footer-copyright">
		<div class="container center">
			© Copyright 2017 . Powered by <a
				class="orange-text text-lighten-3 center" href="#">Ever Blua.</a>
		</div>
	</div>
</footer>