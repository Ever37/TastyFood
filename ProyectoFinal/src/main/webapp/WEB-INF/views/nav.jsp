<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="orange lighten-1" role="navigation">
	<div class="nav-wrapper container">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/"><img
			id="logo" class="responsive-img" name="logo" style="width: 20%"
			src="<c:url  value="/resources/img/Logo-horizontal.png" />" /></a>
		<ul class="right hide-on-med-and-down">
			<sec:authorize access="!isAuthenticated()">
			<li><a class="waves-effect waves-light" href="${pageContext.request.contextPath}/registrate">Registrate</a></li>
			</sec:authorize>
			<li id="quienesSomos"><a class="waves-effect waves-light"
				href="${pageContext.request.contextPath}/nosotros">Quienes somos</a></li>
			<li id="faq"><a class="waves-effect waves-light"
				href="${pageContext.request.contextPath}/faq">FAQ</a></li>
			<sec:authorize
				access="hasAnyAuthority('ADMIN', 'TA_PRECIOS_SU', 'TA_PRECIOS_CU', 'TA_PRECIOS_RU')">
				<li id="planes"><a class="waves-effect waves-light"
					href="${pageContext.request.contextPath}/tabla-precios">Planes</a></li>
			</sec:authorize>
			<li id="contacto"><a class="waves-effect waves-light"
				href="${pageContext.request.contextPath}/contacto">Contactanos</a></li>

			<sec:authorize access="isAuthenticated()">
				<li id="menu"><a class="waves-effect waves-light tooltipped"
					data-position="bottom" data-delay="50" data-activates="slide-out"
					data-tooltip="Menú principal" onclick="mostrarSideNav();"><i
						class="material-icons">menu</i></a></li>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<li><a href="${pageContext.request.contextPath}/login" onclick="foco();"
					class="waves-effect waves-light tooltipped" data-position="bottom"
					data-delay="50" data-activates="slide-out" data-tooltip="Ingresar"><i
						class="material-icons">person</i></a></li>
			</sec:authorize>
			
			<sec:authorize access="isAuthenticated()">
			<sec:authorize
			access="hasAnyAuthority('ADMIN', 'PEDIDOS_SU', 'PEDIDOS_CU')">
						<li id="carrito"><a class="waves-effect waves-light tooltipped"
				href="${pageContext.request.contextPath}/pedidos-new"
				data-position="bottom" data-delay="50" data-activates="slide-out"
				data-tooltip="Mi carrito"><i class="material-icons">shopping_cart
				</i>					
				</a>
			</li>
			
			<li id="contador">
				<%= session.getAttribute("contador") %>	
			</li>
			</sec:authorize>
			</sec:authorize>
			
			<sec:authorize access="!isAuthenticated()">
			<li id="carrito"><a class="waves-effect waves-light tooltipped"
				href="${pageContext.request.contextPath}/pedidos-new"
				data-position="bottom" data-delay="50" data-activates="slide-out"
				data-tooltip="Mi carrito"><i class="material-icons">shopping_cart
				</i>					
				</a>
			</li>		
			<li id="contador">
				<%= session.getAttribute("contador") %>	
			</li>
			</sec:authorize>

			<sec:authentication var="principal" property="principal" />
			<li>
					<a href="${pageContext.request.contextPath}/usuario-profile"> <span
						class="white-text"> <sec:authorize
								access="isAuthenticated()"> 
      				${principal.apellidoNombre}
      				</sec:authorize>
					</span>
					</a>
			</li>
				
			<sec:authorize access="isAuthenticated()">
				<li>
					<form action="<%=request.getContextPath()%>/appLogout"
						method="POST" id="logoutForm" name="logoutForm">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <a
							class="waves-effect waves-light tooltipped"
							data-position="bottom" data-delay="50" data-activates="slide-out"
							data-tooltip="Salir"><i class="material-icons"
							onClick="document.getElementById('logoutForm').submit();">exit_to_app</i></a>
					</form>
				</li>
			</sec:authorize>
					
		</ul>		
		
		
		<!-- Begin SideNav -->
		<ul id="slide-out" class="side-nav">
			<li><div class="userView">
					<div class="background">
						<img src="images/office.jpg">
					</div>
					<a href="#!user"><img class="circle" src="images/yuna.jpg"></a>
					<a href="#!name"><span class="white-text name">John Doe</span></a>
					<a href="#!email"><span class="white-text email">jdandturk@gmail.com</span></a>
				</div></li>
			<li><a href="#!"><i class="material-icons">cloud</i>First
					Link With Icon</a></li>
			<li><a href="#!">Second Link</a></li>
			<li><div class="divider"></div></li>
			<li><a class="subheader">Subheader</a></li>
			<li><a class="waves-effect" href="#!">Third Link With Waves</a></li>
		</ul>
		<!-- End SideNav -->


		<sec:authentication var="principal" property="principal" />
		<ul id="nav-mobile" class="side-nav">
			<li>
				<div class="userView">
					<div class="background">
						<img src="<c:url  value="/resources/img/fondo2.jpg" />">
					</div>
					<a href="${pageContext.request.contextPath}/usuario-profile"> <span
						class="white-text"> <sec:authorize
								access="isAuthenticated()"> 
      			${principal.apellidoNombre} - ${principal.nombreUsuario}
      		</sec:authorize>
					</span>
					</a>
				</div>
			</li>

			<!--  
    	<sec:authorize access="hasAnyAuthority('ADMIN', 'OPINIONES_SU', 'OPINIONES_CU', 'OPINIONES_RU')">
    	<li class="no-padding">
    	<ul class="collapsible collapsible-accordion">
          <li>
            <a class="collapsible-header">Opiniones<i class="material-icons">arrow_drop_down</i></a>
            <div class="collapsible-body">
              <ul>
                <li><a href="${pageContext.request.contextPath}/en-construccion">Listado</a></li>
              </ul>
            </div>
          </li>
        </ul>
        </li>
        </sec:authorize>
		-->
			<sec:authorize
				access="hasAnyAuthority('ADMIN', 'MIS_PED_SU', 'MIS_PED_CU', 'MIS_PED_RU')">
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Mis Pedidos<i
								class="material-icons">arrow_drop_down</i></a>
							<div class="collapsible-body">
								<ul>
									<sec:authorize
										access="hasAnyAuthority('ADMIN', 'MIS_PED_SU', 'MIS_PED_CU', 'MIS_PED_RU')">
										<li><a
											href="${pageContext.request.contextPath}/reporte-mis-pedidos">Listado</a></li>
									</sec:authorize>
								</ul>
							</div></li>
					</ul>
				</li>
			</sec:authorize>

			<sec:authorize
				access="hasAnyAuthority('ADMIN', 'COMERCIOS_SU', 'COMERCIOS_CU', 'COMERCIOS_RU')">
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Comercios<i
								class="material-icons">arrow_drop_down</i></a>
							<div class="collapsible-body">
								<ul>
									<sec:authorize
										access="hasAnyAuthority('ADMIN', 'COMERCIOS_SU', 'COMERCIOS_CU', 'COMERCIOS_RU')">
										<li><a
											href="${pageContext.request.contextPath}/comercios-listado">Listado</a></li>
									</sec:authorize>
								</ul>
							</div></li>
					</ul>
				</li>
			</sec:authorize>

			<sec:authorize
				access="hasAnyAuthority('PRODUCTOS_SU', 'PRODUCTOS_CU', 'PRODUCTOS_RU')">
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Productos<i
								class="material-icons">arrow_drop_down</i></a>
							<div class="collapsible-body">
								<ul>
									<sec:authorize
										access="hasAnyAuthority('PRODUCTOS_SU', 'PRODUCTOS_CU')">
										<li><a
											href="${pageContext.request.contextPath}/productos-new">Nuevo</a></li>
									</sec:authorize>
									<sec:authorize
										access="hasAnyAuthority('PRODUCTOS_SU', 'PRODUCTOS_CU', 'PRODUCTOS_RU')">
										<li><a
											href="${pageContext.request.contextPath}/productos-listado">Listado</a></li>
									</sec:authorize>
								</ul>
							</div></li>
					</ul>
				</li>
			</sec:authorize>
			
			<sec:authorize
				access="hasAnyAuthority('PROMO_SU', 'PROMO_CU', 'PROMO_RU')">
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Promo del día<i
								class="material-icons">arrow_drop_down</i></a>
							<div class="collapsible-body">
								<ul>
									<sec:authorize
										access="hasAnyAuthority('PROMO_SU', 'PROMO_CU')">
										<li><a
											href="${pageContext.request.contextPath}/promocionar-producto">Nueva</a></li>
									</sec:authorize>
								</ul>
							</div></li>
					</ul>
				</li>
			</sec:authorize>

			<sec:authorize
				access="hasAnyAuthority('ADMIN', 'USUARIOS_SU', 'USUARIOS_CU', 'USUARIOS_RU')">
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Usuarios<i
								class="material-icons">arrow_drop_down</i></a>
							<div class="collapsible-body">
								<ul>
									<sec:authorize
										access="hasAnyAuthority('ADMIN', 'USUARIOS_SU', 'USUARIOS_CU')">
										<li><a
											href="${pageContext.request.contextPath}/usuarios-new">Nuevo</a></li>
									</sec:authorize>
									<sec:authorize
										access="hasAnyAuthority('ADMIN', 'USUARIOS_SU', 'USUARIOS_CU', 'USUARIOS_RU')">
										<li><a
											href="${pageContext.request.contextPath}/usuarios-listado">Listado</a></li>
									</sec:authorize>
								</ul>
							</div></li>
					</ul>
				</li>
			</sec:authorize>

			<sec:authorize
				access="hasAnyAuthority('ADMIN', 'GRUPOS_SU', 'GRUPOS_CU', 'GRUPOS_RU')">
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Grupos<i
								class="material-icons">arrow_drop_down</i></a>
							<div class="collapsible-body">
								<ul>
									<sec:authorize
										access="hasAnyAuthority('ADMIN', 'GRUPOS_SU', 'GRUPOS_CU')">
										<li><a
											href="${pageContext.request.contextPath}/grupos-new">Nuevo</a></li>
									</sec:authorize>
									<sec:authorize
										access="hasAnyAuthority('ADMIN', 'GRUPOS_SU', 'GRUPOS_CU', 'GRUPOS_RU')">
										<li><a
											href="${pageContext.request.contextPath}/grupos-listado">Listado</a></li>
									</sec:authorize>
								</ul>
							</div></li>
					</ul>
				</li>
			</sec:authorize>

			<sec:authorize
				access="hasAnyAuthority('ADMIN', 'CATEGORIAS_SU', 'CATEGORIAS_CU', 'CATEGORIAS_RU')">
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Categorías de Productos<i
								class="material-icons">arrow_drop_down</i></a>
							<div class="collapsible-body">
								<ul>
									<sec:authorize
										access="hasAnyAuthority('ADMIN', 'CATEGORIAS_SU', 'CATEGORIAS_CU')">
										<li><a
											href="${pageContext.request.contextPath}/categorias-new">Nueva</a></li>
									</sec:authorize>
									<sec:authorize
										access="hasAnyAuthority('ADMIN', 'CATEGORIAS_SU', 'CATEGORIAS_CU', 'CATEGORIAS_RU')">
										<li><a
											href="${pageContext.request.contextPath}/categorias-listado">Listado</a></li>
									</sec:authorize>
								</ul>
							</div></li>
					</ul>
				</li>
			</sec:authorize>

			<sec:authorize
				access="hasAnyAuthority('ADMIN', 'CONSULTAS_SU', 'CONSULTAS_CU', 'CONSULTAS_RU')">
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Consultas<i
								class="material-icons">arrow_drop_down</i></a>
							<div class="collapsible-body">
								<ul>
									<sec:authorize
										access="hasAnyAuthority('ADMIN', 'CONSULTAS_SU', 'CONSULTAS_CU', 'CONSULTAS_RU')">
										<li><a
											href="${pageContext.request.contextPath}/consultas-listado">Listado</a></li>
									</sec:authorize>
								</ul>
							</div></li>
					</ul>
				</li>
			</sec:authorize>

			<!--
		<sec:authorize access="hasAnyAuthority('ADMIN', 'PUBLICIDAD_SU', 'PUBLICIDAD_CU', 'PUBLICIDAD_RU')">  
        <li class="no-padding">
    	<ul class="collapsible collapsible-accordion">
          <li>
            <a class="collapsible-header">Publicidad<i class="material-icons">arrow_drop_down</i></a>
            <div class="collapsible-body">
              <ul>
              	<sec:authorize access="hasAnyAuthority('ADMIN', 'PUBLICIDAD_SU', 'PUBLICIDAD_CU')">
                <li><a href="${pageContext.request.contextPath}/en-construccion">Nueva</a></li>
                </sec:authorize>
                <sec:authorize access="hasAnyAuthority('ADMIN', 'PUBLICIDAD_SU', 'PUBLICIDAD_CU', 'PUBLICIDAD_RU')">
                <li><a href="${pageContext.request.contextPath}/en-construccion">Listado</a></li>
                </sec:authorize>
              </ul>
            </div>
          </li>
        </ul>
        </li>
        </sec:authorize>
		-->

			<sec:authorize
				access="hasAnyAuthority('ADMIN', 'RE_VENTAS_SU', 'RE_VENTAS_CU', 'RE_VENTAS_RU')">
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li><a class="collapsible-header">Reporte<i
								class="material-icons">arrow_drop_down</i></a>
							<div class="collapsible-body">
								<ul>
									<sec:authorize
										access="hasAnyAuthority('RE_VENTAS_SU', 'RE_VENTAS_CU', 'RE_VENTAS_RU')">
										<li><a
											href="${pageContext.request.contextPath}/reporte-ventas">Ventas</a></li>
									</sec:authorize>
									<sec:authorize
										access="hasAnyAuthority('ADMIN', 'RE_CUENTAS_SU', 'RE_CUENTAS_CU', 'RE_CUENTAS_RU')">
										<li><a
											href="${pageContext.request.contextPath}/resumen-de-cuentas">Resumen
												de cuentas</a></li>
									</sec:authorize>
									<sec:authorize
										access="hasAnyAuthority('ADMIN', 'RE_CUENTAS_SU', 'RE_CUENTAS_CU', 'RE_CUENTAS_RU')">
										<li><a
											href="${pageContext.request.contextPath}/cuentas-listado">Listado
												de cuentas</a></li>
									</sec:authorize>
								</ul>
							</div></li>
					</ul>
				</li>
			</sec:authorize>

		</ul>
		<a href="#" data-activates="nav-mobile" class="button-collapse"><i
			class="material-icons">menu</i></a>
	</div>
</nav>
