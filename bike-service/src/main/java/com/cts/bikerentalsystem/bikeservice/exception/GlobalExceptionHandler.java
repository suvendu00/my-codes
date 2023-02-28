package com.cts.bikerentalsystem.bikeservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 
 * @author Suvendu Gorain
 * date created - 27-july-2022
 *  this is a global exception handler class
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(BikeNotFoundException.class)
	public ResponseEntity bikeNotFoundException(BikeNotFoundException bikeNotFoundException){
		return new ResponseEntity(bikeNotFoundException.getMessage(),  HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(StockNotAvailableException.class)
	public ResponseEntity stockNotFoundException(StockNotAvailableException stockNotAvailableException){
		return new ResponseEntity(stockNotAvailableException.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TypeNotFoundException.class)
	public ResponseEntity typeNotFoundException(TypeNotFoundException typeNotFoundException){
		return new ResponseEntity(typeNotFoundException.getMessage(),  HttpStatus.NOT_FOUND);
	}
	
}
