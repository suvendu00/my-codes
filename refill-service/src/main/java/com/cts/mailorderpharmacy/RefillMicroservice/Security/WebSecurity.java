package com.cts.mailorderpharmacy.RefillMicroservice.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    	http.cors().and().csrf().disable();
    	http.authorizeRequests().antMatchers(
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
