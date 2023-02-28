package com.cts.MailOrderPharmacy.Authorizations;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		  http.csrf().disable().cors().disable(); http.authorizeRequests().antMatchers("/authenticate",
		  "/register",
		  "/v2/api-docs",
		  
		  "/configuration/ui", "**/swagger-resources/**",
		  
		  "/configuration/security",
		  
		  "/swagger-ui.html",
		  
		  "/webjars/**",
		  
		  "/v2/api-docs/**",
		  
		  "/swagger-ui/**", "/h2-console/**",
		  "/actuator/**").permitAll()
		  .and().authorizeRequests().anyRequest()
		  .authenticated().and().sessionManagement().sessionCreationPolicy(
		  SessionCreationPolicy.STATELESS);
		 
		
		/*
		 * http.csrf().disable().authorizeRequests() .antMatchers("/**") .permitAll()
		 * .anyRequest() .authenticated() .and() .exceptionHandling().and()
		 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 */
	}

	
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
