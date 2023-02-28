package com.cts.bikerentalsystem.bikeservice.exception;

public class StockNotAvailableException extends RuntimeException{

	public StockNotAvailableException(String message) {
		super(message);
	}
}
