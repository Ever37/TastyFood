package com.proyectofinal.app.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = false)
@PropertySource(value = { "classpath:application.properties" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService customUserDetailsService;
	
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(
				this.passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	    http
	    .authorizeRequests()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/faq").permitAll()
		.antMatchers("/nosotros").permitAll()
		.antMatchers("/en-construccion").permitAll()
		.antMatchers("/pedidos-new").permitAll()
		.antMatchers("/registrate").permitAll()
		
		/*
		 * Permiso para MI USUARIO
		 */	
		.antMatchers("/usuario-profile")
		.hasAnyAuthority("ADMIN,  MI_USUARIO_SU, MI_USUARIO_CU, MI_USUARIO_RU")

		.antMatchers("/usuario-profile-update")
		.hasAnyAuthority("ADMIN,  MI_USUARIO_SU, MI_USUARIO_CU")
		
		.antMatchers("/usuarios-{\\d+}-password")
		.hasAnyAuthority("ADMIN,  MI_USUARIO_SU, MI_USUARIO_CU")
		
		/*
		 * Permiso para MI COMERCIO
		 */	
		.antMatchers("/comercio-profile")
		.hasAnyAuthority("ADMIN,  MICOMER_SU, MICOMER_CU, MICOMER_RU")

		.antMatchers("/comercio-profile-update")
		.hasAnyAuthority("ADMIN,  MICOMER_SU, MICOMER_CU")		
		
		/*
		 * Permiso para USUARIOS
		 */	
		//.antMatchers("/usuarios-new")
		//.hasAnyAuthority("ADMIN, USUARIOS_SU, USUARIOS_CU")

		.antMatchers("/usuarios-listado")
		.hasAnyAuthority("ADMIN, USUARIOS_SU, USUARIOS_CU, USUARIOS_RU")

		.antMatchers("/usuarios-{\\d+}-view")
		.hasAnyAuthority("ADMIN,  USUARIOS_SU, USUARIOS_CU, USUARIOS_RU")

		.antMatchers("/usuarios-{\\d+}-update")
		.hasAnyAuthority("ADMIN,  USUARIOS_SU, USUARIOS_CU")
	
		.antMatchers("/usuarios-{\\d+}-delete")
		.hasAnyAuthority("ADMIN,  USUARIOS_SU")
		

		
		/*
		 * Permiso para GRUPOS
		 */
		.antMatchers("/grupos-new")
		.hasAnyAuthority("ADMIN, GRUPOS_SU, GRUPOS_CU")

		.antMatchers("/grupos-listado")
		.hasAnyAuthority("ADMIN, GRUPOS_SU, GRUPOS_CU, GRUPOS_RU")

		.antMatchers("/grupos-{\\d+}-view")
		.hasAnyAuthority("ADMIN,  GRUPOS_SU, GRUPOS_CU, GRUPOS_RU")

		.antMatchers("/grupos-{\\d+}-update")
		.hasAnyAuthority("ADMIN,  GRUPOS_SU, GRUPOS_CU")
	
		.antMatchers("/grupos-{\\d+}-delete")
		.hasAnyAuthority("ADMIN,  GRUPOS_SU")
		
		/*
		 * Permiso para PRODUCTOS
		 */
		.antMatchers("/productos-new")
		.hasAnyAuthority("ADMIN, PRODUCTOS_SU, PRODUCTOS_CU")

		.antMatchers("/productos-listado")
		.hasAnyAuthority("ADMIN, PRODUCTOS_SU, PRODUCTOS_CU, PRODUCTOS_RU")

		.antMatchers("/productos-{\\d+}-view")
		.hasAnyAuthority("ADMIN,  PRODUCTOS_SU, PRODUCTOS_CU, PRODUCTOS_RU")

		.antMatchers("/productos-{\\d+}-update")
		.hasAnyAuthority("ADMIN,  PRODUCTOS_SU, PRODUCTOS_CU")
	
		.antMatchers("/productos-{\\d+}-delete")
		.hasAnyAuthority("ADMIN,  PRODUCTOS_SU")
		
		
		/*
		 * Permiso para PROMO
		 */
		.antMatchers("/promocionar-producto")
		.hasAnyAuthority("ADMIN, PROMO_SU, PROMO_CU")		
		
		/*
		 * Permiso para COMERCIOS
		 */
		.antMatchers("/comercios-new")
		.hasAnyAuthority("ADMIN, COMERCIOS_SU, COMERCIOS_CU, MICOMER_SU, MICOMER_CU")

		.antMatchers("/comercios-listado")
		.hasAnyAuthority("ADMIN, COMERCIOS_SU, COMERCIOS_CU, COMERCIOS_RU")

		.antMatchers("/comercios-{\\d+}-view")
		.hasAnyAuthority("ADMIN, COMERCIOS_SU, COMERCIOS_CU, COMERCIOS_RU")

		.antMatchers("/comercios-{\\d+}-update")
		.hasAnyAuthority("ADMIN, COMERCIOS_SU, COMERCIOS_CU, MICOMER_SU, MICOMER_CU")
	
		.antMatchers("/comercios-{\\d+}-delete")
		.hasAnyAuthority("ADMIN, COMERCIOS_SU")
		
		/*
		 * Permiso para CATEGORÍA DE PRODUCTOS
		 */
		.antMatchers("/categorias-new")
		.hasAnyAuthority("ADMIN, CATEGORIAS_SU, CATEGORIAS_CU")

		.antMatchers("/categorias-listado")
		.hasAnyAuthority("ADMIN, CATEGORIAS_SU, CATEGORIAS_CU, CATEGORIAS_RU")

		.antMatchers("/categorias-{\\d+}-view")
		.hasAnyAuthority("ADMIN, CATEGORIAS_SU, CATEGORIAS_CU, CATEGORIAS_RU")

		.antMatchers("/categorias-{\\d+}-update")
		.hasAnyAuthority("ADMIN, CATEGORIAS_SU, CATEGORIAS_CU")
	
		.antMatchers("/categorias-{\\d+}-delete")
		.hasAnyAuthority("ADMIN, CATEGORIAS_SU")		
		
		/*
		 * Permiso para CONSULTAS
		 */
		.antMatchers("/consultas-new")
		.hasAnyAuthority("ADMIN, CONSULTAS_SU, CONSULTAS_CU")

		.antMatchers("/consultas-listado")
		.hasAnyAuthority("ADMIN, CONSULTAS_SU, CONSULTAS_CU, CONSULTAS_RU")

		.antMatchers("/consultas-{\\d+}-view")
		.hasAnyAuthority("ADMIN, CONSULTAS_SU, CONSULTAS_CU, CONSULTAS_RU")

		.antMatchers("/consultas-{\\d+}-update")
		.hasAnyAuthority("ADMIN, CONSULTAS_SU, CONSULTAS_CU")
	
		.antMatchers("/consultas-{\\d+}-delete")
		.hasAnyAuthority("ADMIN, CONSULTAS_SU")		
		
		/*
		 * Permiso para MIS PEDIDOS
		 */		
		.antMatchers("/reporte-mis-pedidos")
		.hasAnyAuthority("ADMIN, MIS_PED_SU, MIS_PED_CU, MIS_PED_RU")
		
		.antMatchers("/pedidos-{\\d+}-view")
		.hasAnyAuthority("ADMIN, MIS_PED_SU, MIS_PED_CU, MIS_PED_RU"
						+ ", RE_VENTAS_SU, RE_VENTAS_CU, RE_VENTAS__RU")
		
		/*
		 * Permiso para REPORTE VENTAS
		 */
		.antMatchers("/reporte-ventas")		
		.hasAnyAuthority("ADMIN, RE_VENTAS_SU, RE_VENTAS_CU, RE_VENTAS__RU")	
		
		/*
		 * Permiso para RESUMEN DE CUENTAS
		 */
		.antMatchers("/resumen-de-cuentas")		
		.hasAnyAuthority("ADMIN, RE_CUENTA_SU, RE_CUENTA_CU, RE_CUENTA__RU")
		.antMatchers("/cuentas-listado")		
		.hasAnyAuthority("ADMIN, RE_CUENTA_SU, RE_CUENTA_CU, RE_CUENTA__RU")
		
		/*
		 * Permiso para PEDIDOS
		 */	
		.antMatchers(HttpMethod.POST,"/pedidos-new")		
		.hasAnyAuthority("ADMIN, PEDIDOS_RU, PEDIDOS_CU, PEDIDOS_SU")				
		
		.and()
		    .formLogin().loginPage("/").permitAll()
		    .failureUrl("/login?error").permitAll()
		    .usernameParameter("username").passwordParameter("password")
		    .loginProcessingUrl("/login")
		    .defaultSuccessUrl("/")
		.and()
		    .logout().permitAll()
		    .logoutUrl("/appLogout").logoutSuccessUrl("/")
		.and()
			.exceptionHandling().accessDeniedPage("/403")
		.and()
			.httpBasic()
		.and()
		    .csrf().disable();

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
