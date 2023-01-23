package com.proyectofinal.app.core.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.proyectofinal.app.*") 
@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter{

    @Bean
    public ViewResolver viewResolverForClasses() {
    	ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
    	viewResolver.setOrder(1);
    	viewResolver.setBasename("views");
    	return viewResolver;
    }

    @Bean
    public ViewResolver viewResolver() {
    	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    	viewResolver.setOrder(2);
    	viewResolver.setViewClass(JstlView.class);
    	viewResolver.setPrefix("/WEB-INF/views/");
    	viewResolver.setSuffix(".jsp");
    	viewResolver.setContentType("text/html; charset=ISO-8859-1");
    	return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    	messageSource.setBasename("messages");
    	return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}
