package com.cts.MailOrderPharmacy.Authorizations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity bikeNotFoundException(Exception exception){
		return new ResponseEntity(exception.getMessage(),  HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity bikeNotFoundException(UsernameNotFoundException usernameNotFoundException){
		return new ResponseEntity(usernameNotFoundException.getMessage(),  HttpStatus.NOT_FOUND);
	}
}
