package com.cts.mailorderpharmacy.drugservice.exception;


/**
 * 
 * @author Suvendu Gorain
 * date created - 27-july-2022
 *  this class is for stock not found exception
 */

public class StockNotFoundException extends RuntimeException{

	public StockNotFoundException(String message) {
		super(message);
	}
}
