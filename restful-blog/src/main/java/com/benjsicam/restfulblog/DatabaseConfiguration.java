package com.benjsicam.restfulblog;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:META-INF/config/${SPRING_PROFILES_ACTIVE:local}/application.properties")
@EnableTransactionManagement
@EnableJpaRepositories("com.benjsicam.restfulblog")
public class DatabaseConfiguration {
	private @Value("${database.driverClassName}") String driverClassName;
	private @Value("${database.host}") String host;
	private @Value("${database.username}") String username;
	private @Value("${database.password}") String password;
	
	private @Value("${hibernate.dialect}") String hibernateDialect;
	private @Value("${hibernate.hbm2ddl.auto}") String hibernateHbm2ddlAuto;
	private @Value("${hibernate.ejb.naming.strategy}") String hibernateEjbNamingStrategy;
	private @Value("${hibernate.connection.charset}") String hibernateConnectionCharSet;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(destroyMethod = "close")
	public BasicDataSource dataSource() {
		
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driverClassName);
		basicDataSource.setUrl(host);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);

		return basicDataSource;
	}
	
	@Bean
	public Properties jpaProperties() {
		
		Properties jpaProperties = new Properties();
		
        jpaProperties.put("hibernate.dialect", hibernateDialect);
        jpaProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        jpaProperties.put("hibernate.ejb.naming_strategy", hibernateEjbNamingStrategy);
        jpaProperties.put("hibernate.connection.charSet", hibernateConnectionCharSet);
        
        return jpaProperties;
	}

	@Bean
	@Autowired
	public AbstractEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Properties jpaProperties) {
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		
		factory.setJpaProperties(jpaProperties);
		factory.setPackagesToScan("com.benjsicam.restfulblog");
		factory.setDataSource(dataSource);
		factory.setPersistenceProviderClass(HibernatePersistence.class);
		
		return factory;
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		
		return transactionManager;
	}

	@Bean 
	public HibernateExceptionTranslator hibernateExceptionTranslator() { 		
		return new HibernateExceptionTranslator(); 
	}
	
	@Bean
	@Autowired
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
		
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(new ClassPathResource("META-INF/init.sql"));
		resourceDatabasePopulator.setContinueOnError(true);
		
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
		dataSourceInitializer.setEnabled(true);
		
		return dataSourceInitializer;
	}



}
