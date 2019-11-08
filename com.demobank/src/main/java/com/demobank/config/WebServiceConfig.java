package com.demobank.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demobank.ws.UserWebService;


@Configuration
public class WebServiceConfig {
	@Autowired
	private Bus bus;
	
	@Autowired
	UserWebService userWebService;
	
	@Bean
	public SpringBus cxf() {
		return new SpringBus();
		
	}
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new CXFServlet(), "/services/*");
	}
	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, userWebService);
		endpoint.publish("/userWebService");
		
		return endpoint;
		
	}
	
}
