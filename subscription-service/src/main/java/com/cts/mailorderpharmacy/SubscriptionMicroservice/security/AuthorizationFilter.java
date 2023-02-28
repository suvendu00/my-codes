package com.cts.mailorderpharmacy.SubscriptionMicroservice.security;


import java.io.IOException;import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		
		if (header == null) {
			chain.doFilter(request, response);

		} else {
			UsernamePasswordAuthenticationToken authentication = authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
		}
	}

	private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		token = token.substring(7);

		if (token != null) {
            
            DecodedJWT decodedJWT=JWT.require(Algorithm.HMAC512("mailorderpharmacy"))
            .build()
            .verify(token);
            
            String username=decodedJWT.getSubject();
            
			return new UsernamePasswordAuthenticationToken(username, null, new ArrayList());

		}
		return null;
	}
}
