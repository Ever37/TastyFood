package com.proyectofinal.app.core.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.proyectofinal.app.core.config.AppConfig;


@Configuration
@EnableWebMvc
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
implements WebApplicationInitializer {

    //private static Class<?>[] configurationClasses = new Class<?>[] { AppConfig.class, SecurityConfig.class };
    //private static Class<?>[] configurationClasses = new Class<?>[] {AppConfig.class};
    
    @Override
    public void onStartup(ServletContext container) throws ServletException {
    	

	AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	ctx.register(AppConfig.class);
	ctx.setServletContext(container);

	/*
    container.addListener(new MyRequestContextListener());
	container.addListener(new HttpSessionEventPublisher());
	container.addListener(new SessionListener());
	*/
	
	container.addListener(new ContextLoaderListener(ctx));

	ctx.setServletContext(container);

	ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

	//servlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	servlet.setLoadOnStartup(1);
	servlet.addMapping("/");

    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
	return new Class[] { AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
	return null;
    }

    @Override
    protected String[] getServletMappings() {
	return new String[] { "/" };
    }

}
