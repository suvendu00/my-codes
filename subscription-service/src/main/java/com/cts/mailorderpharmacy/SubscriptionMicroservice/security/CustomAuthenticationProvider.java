package com.cts.mailorderpharmacy.SubscriptionMicroservice.security;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	

	
	  @Override
	    public boolean supports(Class<?> authentication) {
	        return true;
	    }

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return null;
	}

}
