package com.cts.mailorderpharmacy.SubscriptionMicroservice.exception;

@SuppressWarnings("serial")
public class StockNotFoundException extends Exception {
	public StockNotFoundException()
	{
		
	}
	public StockNotFoundException(String message) {
		super(message);
	}
}
