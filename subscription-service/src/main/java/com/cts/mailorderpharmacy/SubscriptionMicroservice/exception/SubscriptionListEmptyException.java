package com.cts.mailorderpharmacy.SubscriptionMicroservice.exception;

@SuppressWarnings("serial")
public class SubscriptionListEmptyException extends Exception {

	public SubscriptionListEmptyException()
	{
		
	}
	
	public SubscriptionListEmptyException(String message)
	{
		super(message);
	}
}
