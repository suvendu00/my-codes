package com.cts.mailorderpharmacy.RefillMicroservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.TokenExpiredException;

@RestControllerAdvice
public class GlobalExceptionHandler {


	
	@ExceptionHandler(SubscriptionIdnotFoundException.class)
	public ResponseEntity drugNotFoundException(SubscriptionIdnotFoundException subscriptionIdnotFoundException){
		return new ResponseEntity(subscriptionIdnotFoundException.getMessage(),  HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(StockNotFoundException.class)
//	public ResponseEntity stockNotFoundException(StockNotFoundException stockNotFoundException){
//		return new ResponseEntity(stockNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
//	}
	
//	@ExceptionHandler(TokenExpiredException.class)
//	public ResponseEntity tokenExpiredException(TokenExpiredException exception) {
//		return new ResponseEntity(exception.getMessage(),HttpStatus.REQUEST_TIMEOUT);
//	}
}
