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

<title>AltaGrupo</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
<br>
	<div class="white">
	<br>
		<br> <span class="ir-arriba icon-arrow-up2"><i
			class="material-icons small">keyboard_arrow_up</i></span>
		<div class="container">
			<h5 class="flow-text">
				<i class="material-icons">add</i>&nbsp;&nbsp;Nuevo grupo
			</h5>
			<form:form class="col s12" id="frmc" method="POST"
				modelAttribute="grupo"
				action="${pageContext.request.contextPath}/grupos-new" role="form">
				<form:hidden path="idGrupo" />
				<div class="row">
					<div class="row right">
						<div class="details">
							<a href="<c:url value='grupos-listado' />" id="listGrupo"
								class="btn-floating btn-medium btn tooltipped waves-effect waves-light blue"
								data-position="left" data-delay="50" data-tooltip="Listado">
								<i class="material-icons">list</i>
							</a>
						</div>
					</div>
					<br>
					<div class="input-field col s6">
						<form:input path="descripcion" id="grupo" name="grupo" type="text"
							maxlength="30" required="required" autofocus="autofocus" />
						<label for="last_name">Descripción</label>
					</div>
					<br> <br>
					<div class="col s6">
						<label id="descripcion-error" class="error"> </label>
					</div>
				</div>
				<h5 class="flow-text">Permisos a conceder</h5>

				<div style="width: 100%;">
					<c:choose>
						<c:when test="${empty grupo.rComunes}">
							<p class="error">No hay requerimientos comunes</p>
						</c:when>
						<c:otherwise>
							<div>
								<table class="bordered">
									<thead>
										<tr>
											<th><div align="center">
													<h5>Requerimientos Comunes</h5>
												</div></th>
											<c:forEach items="${permisos}" var="p" varStatus="i">
												<th class="no-sort"><div align="center">
														<h6>
															<b>${p.descripcionCompleta}</b>
														</h6>
													</div></th>
											</c:forEach>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${grupo.rComunes}" var="r" varStatus="i">
											<tr>
												<td>
													<div align="center">
														<b>${r.descripcion} <form:hidden
																path="rComunes[${i.index}].idRequerimiento" /> <form:hidden
																path="rComunes[${i.index}].descripcion" /> <form:hidden
																path="rComunes[${i.index}].alias" /> <form:hidden
																path="rComunes[${i.index}].workflow" /> <form:hidden
																path="rComunes[${i.index}].idRequerimientoPermisoGrupo" />
														</b>
													</div>
												</td>
												<c:forEach items="${permisos}" var="p">
													<c:choose>
														<c:when test="${p.idPermiso == 1}">
															<td>
																<div align="center">
																	<form:radiobutton path="rComunes[${i.index}].permiso"
																		name="grupo${i.index}" class="with-gap"
																		id="permiso${p.idPermiso}-${i.index}"
																		value="${p.idPermiso}" checked="checked" />
																	<label for="permiso${p.idPermiso}-${i.index}"></label>
																</div>
															</td>
														</c:when>
														<c:otherwise>
															<td>
																<div align="center">
																	<form:radiobutton path="rComunes[${i.index}].permiso"
																		name="grupo${i.index}" class="with-gap"
																		id="permiso${p.idPermiso}-${i.index}"
																		value="${p.idPermiso}" />
																	<label for="permiso${p.idPermiso}-${i.index}"></label>
																</div>
															</td>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<br>

				<!-- ACÁ VAN LOS REQUERIMIENTOS ESPECIALES -->
				<div style="width: 100%;">
					<c:choose>
						<c:when test="${empty grupo.rEspeciales}">
							<p class="error">No hay requerimientos especiales</p>
						</c:when>
						<c:otherwise>
							<div>
								<table class="bordered">
									<thead>
										<tr>
											<th><div align="center">
													<h5>Requerimientos Especiales</h5>
												</div></th>
											<c:forEach items="${permisos}" var="p" varStatus="i">
												<th class="no-sort"><div align="center">
														<h6>
															<b>${p.descripcionCompleta}</b>
														</h6>
													</div></th>
											</c:forEach>
										</tr>
									</thead>
									<tbody>
										<c:set var="k" value="0"></c:set>
										<c:forEach items="${grupo.rEspeciales}" var="r" varStatus="i">

											<tr>
												<td>
													<div align="center">
														<b>${r.descripcion} <form:hidden
																path="rEspeciales[${i.index}].idRequerimiento" /> <form:hidden
																path="rEspeciales[${i.index}].descripcion" /> <form:hidden
																path="rEspeciales[${i.index}].alias" /> <form:hidden
																path="rEspeciales[${i.index}].workflow" /> <form:hidden
																path="rEspeciales[${i.index}].idRequerimientoPermisoGrupo" />
														</b>
													</div>
												</td>
												<c:forEach items="${permisos}" var="p">
													<c:choose>

														<c:when
															test="${(p.idPermiso == 1 || p.idPermiso == 2) && r.descripcion == administrador}">
															<td>
																<div align="center">
																	<form:radiobutton
																		path="rEspeciales[${i.index}].permiso"
																		name="grupo${i.index}" class="with-gap"
																		id="permisoE${p.idPermiso}-${k}"
																		value="${p.idPermiso}" disabled="true" />
																	<label for="permisoE${p.idPermiso}-${k}"></label>
																</div>
															</td>
														</c:when>
														<c:when
															test="${(p.idPermiso == 4) && r.descripcion == administrador}">
															<td>
																<div align="center">
																	<form:radiobutton
																		path="rEspeciales[${i.index}].permiso"
																		name="grupo${i.index}" class="with-gap"
																		id="permisoE${p.idPermiso}-${k}"
																		value="${p.idPermiso}" checked="checked" />
																	<label for="permisoE${p.idPermiso}-${k}"></label>
																</div>
															</td>
														</c:when>
														<c:when
															test="${(p.idPermiso == 3) && r.descripcion == administrador}">
															<td>
																<div align="center">
																	<form:radiobutton
																		path="rEspeciales[${i.index}].permiso"
																		name="grupo${i.index}" class="with-gap"
																		id="permisoE${p.idPermiso}-${k}"
																		value="${p.idPermiso}" />
																	<label for="permisoE${p.idPermiso}-${k}"></label>
																</div>
															</td>
														</c:when>

														<c:otherwise>
															<c:choose>
																<c:when test="${p.idPermiso == 1}">
																	<td>
																		<div align="center">
																			<form:radiobutton
																				path="rEspeciales[${i.index}].permiso"
																				name="grupo${i.index}" class="with-gap"
																				id="permisoE${p.idPermiso}-${k}"
																				value="${p.idPermiso}" checked="checked" />
																			<label for="permisoE${p.idPermiso}-${k}"></label>
																		</div>
																	</td>
																</c:when>
																<c:otherwise>
																	<td>
																		<div align="center">
																			<form:radiobutton
																				path="rEspeciales[${i.index}].permiso"
																				name="grupo${i.index}" class="with-gap"
																				id="permisoE${p.idPermiso}-${k}"
																				value="${p.idPermiso}" />
																			<label for="permisoE${p.idPermiso}-${k}"></label>
																		</div>
																	</td>
																</c:otherwise>
															</c:choose>
														</c:otherwise>

													</c:choose>
												</c:forEach>
												<c:set var="k" value="${k+1}"></c:set>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:otherwise>
					</c:choose>
				</div>

				<br>
				<div class="row center">
					<a class="waves-effect waves-light btn grey lighten-2 black-text"
						onClick="goBack()">Volver</a>
					<button onClick="return validarAlta();"
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
	<script src="<c:url value="resources/js/sweetalert.min.js" />"></script>

	<script type="text/javascript">
  
  function validarAlta() {
		document.getElementById("descripcion-error").textContent = "";
		
		var rta = true;
		
		var msj = "";
		var descripcion = document.getElementById("grupo").value;
		
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
  
  $(document).ready(function(){
	  
		$('.ir-arriba').click(function(){
			$('body, html').animate({
				scrollTop: '0px'
			}, 300);
		});
	 
		$(window).scroll(function(){
			if( $(this).scrollTop() > 0 ){
				$('.ir-arriba').slideDown(300);
			} else {
				$('.ir-arriba').slideUp(300);
			}
		});
	 
	});
  
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