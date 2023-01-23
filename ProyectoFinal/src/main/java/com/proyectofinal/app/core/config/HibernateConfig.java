package com.proyectofinal.app.core.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"core.config"})
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfig {

    @Autowired
    private Environment environment;

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(
		dataSource);
	sessionBuilder.scanPackages("com.proyectofinal.app.model");
	sessionBuilder.addProperties(this.hibernateProperties());
	return sessionBuilder.buildSessionFactory();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
	// DriverManagerDataSource dataSource = new DriverManagerDataSource();
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName(this.environment.getRequiredProperty("jdbc.driverClassName"));
	dataSource.setUrl(this.environment.getRequiredProperty("jdbc.url"));
	dataSource.setUsername(this.environment.getRequiredProperty("jdbc.username"));
	dataSource.setPassword(this.environment.getRequiredProperty("jdbc.password"));
	return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
	HibernateTransactionManager txManager = new HibernateTransactionManager();
	txManager.setSessionFactory(s);
	return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties() {
	Properties properties = new Properties();
	properties.put("hibernate.dialect",
		this.environment.getRequiredProperty("hibernate.dialect"));
	properties.put("hibernate.show_sql",
		this.environment.getRequiredProperty("hibernate.show_sql"));
	properties.put("hibernate.format_sql",
		this.environment.getRequiredProperty("hibernate.format_sql"));
	properties.put("hibernate.c3p0.min_size",
		this.environment.getRequiredProperty("hibernate.c3p0.max_size"));
	properties.put("hibernate.c3p0.timeout",
		this.environment.getRequiredProperty("hibernate.c3p0.timeout"));
	properties.put("hibernate.c3p0.max_statements", this.environment
		.getRequiredProperty("hibernate.c3p0.max_statements"));
	return properties;
    }
}
