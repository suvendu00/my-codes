package com.cts.mailorderpharmacy.SubscriptionMicroservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(DrugNotFoundException.class)
	public ResponseEntity drugNotFoundException(DrugNotFoundException drugNotFoundException){
		return new ResponseEntity(drugNotFoundException.getMessage(),  HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(StockNotFoundException.class)
	public ResponseEntity stockNotFoundException(StockNotFoundException stockNotFoundException){
		return new ResponseEntity(stockNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
}
