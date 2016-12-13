package com.task.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.task.doa.TaskDAO;
import com.task.doa.TaskDAOImpl;
import com.task.doa.UserDAO;
import com.task.doa.UserDAOImpl;
import com.task.model.Task;
import com.task.model.User;




@Configuration
@ComponentScan("com.task")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Bean(name="dataSource")
	public DataSource getDataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("org.h2.Driver");
    	dataSource.setUrl("jdbc:h2:tcp://localhost/~/BankerTask");
    	dataSource.setUsername("sa");
    	dataSource.setPassword("");
    	System.out.println("one");
    	
    	return dataSource;
    }
    
    
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	System.out.println("Inside Application Context--Two");
    	properties.put("hibernate.hbm2ddl.auto", "update");
    
    	properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
    	return properties;
    }
    
    @Autowired // automatically bean is created n injected
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource); 
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Task.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory); 																		// factory
		return transactionManager;
	}

	@Autowired
	@Bean(name = "user")
	public User getUser() {
		return new User();
	}

	@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}


	@Autowired
	@Bean(name = "task")
	public Task getTask() {
		return new Task();
	}

	@Autowired
	@Bean(name = "taskDAO")
	public TaskDAO getTaskDAO(SessionFactory sessionFactory) {
		return new TaskDAOImpl(sessionFactory);
	}

	}

	

