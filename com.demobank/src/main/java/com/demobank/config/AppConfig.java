package com.demobank.config;

import java.util.List;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.demobank.domain.Account;
import com.demobank.domain.Branch;
import com.demobank.domain.Customer;
import com.demobank.domain.Transaction;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
	
	
	// Open a connection
	@Bean
	DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/demobankdb?useSSL=false&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("Cps38102991");
		
		return dataSource;		
	}
	
	// LocalSessionFactoryBean
	@Bean 
	LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.demobank.domain");
		
		// If had more class on domain, must add here
		sessionFactory.setAnnotatedClasses(Account.class, Branch.class, Transaction.class,  Customer.class); 
		sessionFactory.setHibernateProperties(hibernateProperties());
		
				
		return sessionFactory;
	}
	
	// LocalContainerEntityManagerFactoryBean
	// The Primary method
	@Bean
	@Primary
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());;
		
		// If had more class on domain, must add here
		entityManagerFactory.setPackagesToScan("com.demobank.domain");
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setJpaProperties(hibernateProperties());
		
		
		return entityManagerFactory;
	}
	
	@Bean
	Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "false");		
		
		return hibernateProperties;
		
	}
	
	// PlatformTransactionManager
	@Bean
	PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		
		return transactionManager;		
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		converters.add(new Jaxb2RootElementHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		converters.add(new MappingJackson2XmlHttpMessageConverter());
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		
		return viewResolver;		
	}
	
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("synergyit171@gmail.com");
		mailSender.setPassword("fremont123!");
		
		Properties javaMailProperties = new Properties();
		//transport protocol
		javaMailProperties.setProperty("mail.transport.protocol", "smtp"); // smtp -> Simple Mail Transport Protocol, othe email protocols are POP and IMAP
		
		// authorization
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		
		// enable the transport layer security
	   javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
		
		// print the authorization and email sending activity
		javaMailProperties.setProperty("mail.debug", "true");
		
		mailSender.setJavaMailProperties(javaMailProperties);
		
		return mailSender;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
		.addResourceLocations("classpath:/static/");
	}
	
}



















