package com.cts.bikerentalsystem.bookingservice.exception;

public class StockNotAvailableException extends RuntimeException{

	public StockNotAvailableException(String message) {
		super(message);
	}
}
