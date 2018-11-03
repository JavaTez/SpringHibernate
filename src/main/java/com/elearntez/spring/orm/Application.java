package com.elearntez.spring.orm;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.elearntez.spring.orm.repository.CityRepository;
import com.elearntez.spring.orm.repository.CityRepositoryImpl;

@Configuration
@PropertySource("classpath:jdbc-config.properties")
@EnableTransactionManagement
public class Application {
	
	@Value("${database.url}")
	private String URL; 
	
	@Value("${database.username}")
	private String USER_NAME;
	
	@Value("${database.password}")
	private String PASSWORD;
	
	@Value("${database.drivername}")
	private String driverName;
	
	@Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
	
	 @Bean
	    public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(mysqlDataSource());
	        sessionFactory.setPackagesToScan("com.elearntez.spring.orm.bean");
	        sessionFactory.setHibernateProperties(hibernateProperties());
	        return sessionFactory;
	    }

	 
	  private final Properties hibernateProperties() {
	        Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty(
	          "hibernate.hbm2ddl.auto", "update");
	        hibernateProperties.setProperty(
	          "hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	        hibernateProperties.setProperty("hibernate.show_sql","true");
	        hibernateProperties.setProperty("hibernate.format_sql","true");
	        return hibernateProperties;
	    }
	  
	  @Bean
	    public HibernateTransactionManager getTransactionManager() {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(sessionFactory().getObject());
	        return transactionManager;
	    }
	  
	  @Bean
	  public CityRepository cityRepository(){
		  return new CityRepositoryImpl();
	  }
	
}
