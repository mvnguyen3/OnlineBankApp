package com.demobank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import com.demobank.domain.User;
import com.demobank.service.UserService;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // provides method level security
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	Pbkdf2PasswordEncoder pbkdf2;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication().withUser("minh").password(pbkdf2.encode("minh")).roles("Programmer", "Admin");
//		auth.inMemoryAuthentication().withUser("hailey").password(pbkdf2.encode("hailey")).roles("Programmer", "Admin");
//		auth.inMemoryAuthentication().withUser("goku").password(pbkdf2.encode("goku")).roles("User", "Programmer");
		auth.userDetailsService(userDetailsService).passwordEncoder(pbkdf2);  
		
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/userForm", "/saveUserForm").permitAll() // Authentication is not required for first time customer.
		.anyRequest()
		.authenticated()
		.and().formLogin()
		.loginPage("/login") // If have custom login page
		.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/login?logout")
		.and().exceptionHandling().accessDeniedPage("/accessDeniedPage");
		http.csrf().disable();
		http.httpBasic();
	}
	
	public void configure(WebSecurity web) {
		// Have to be inside the resource/static folder
		web.ignoring().antMatchers("/css/*");
		web.ignoring().antMatchers("/images/*");
	}
	
	
	@Bean
	@Primary
	Pbkdf2PasswordEncoder pbkdf2() {
		Pbkdf2PasswordEncoder pbkdf2 = new Pbkdf2PasswordEncoder();		
		
//		System.out.println("Username: minh encoded password: " + pbkdf2.encode("minh")
//		+ " length: " + pbkdf2.encode("minh").length());
//		System.out.println("Username: hailey encoded password: " + pbkdf2.encode("hailey")
//		+ " length: " + pbkdf2.encode("hailey").length());
//		System.out.println("Username: goku encoded password: " + pbkdf2.encode("goku")
//		+ " length: " + pbkdf2.encode("goku").length());
		
		return pbkdf2;
	}
	
}	



































