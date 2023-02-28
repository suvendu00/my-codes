package com.cts.bikerentalsystem.bikeservice.exception;

public class BikeNotFoundException extends RuntimeException{

	public BikeNotFoundException(String message) {
		super(message);
	}
}
