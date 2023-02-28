package com.cts.mailorderpharmacy.drugservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
    

    @Override
  		@Bean
  		public AuthenticationManager authenticationManagerBean() throws Exception {
  		    return super.authenticationManagerBean();
  		}
	
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.headers().frameOptions().disable();
    	http.csrf().disable().cors().and().authorizeRequests().antMatchers(
    			"/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/v3/api-docs/**",
                "/swagger-ui/**","/h2-console/**","/actuator/**")
    	.permitAll().and()
    	.authorizeRequests()
    	.anyRequest().authenticated().and()
    	.addFilter(new AuthorizationFilter(authenticationManager()))
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    	http.authorizeRequests().antMatchers("/").permitAll();
    	
    }
    
	
	@Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
	
	
}
