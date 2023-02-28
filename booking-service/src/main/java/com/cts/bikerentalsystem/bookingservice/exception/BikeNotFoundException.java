package com.cts.bikerentalsystem.bookingservice.exception;

public class BikeNotFoundException extends RuntimeException{

	public BikeNotFoundException(String message) {
		super(message);
	}
}
