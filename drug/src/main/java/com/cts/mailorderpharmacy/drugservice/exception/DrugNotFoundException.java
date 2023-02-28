package com.cts.mailorderpharmacy.drugservice.exception;

/**
 * 
 * @author Suvendu Gorain
 * date created - 27-july-2022
 *  this class is for custom drug not found exception
 */

public class DrugNotFoundException extends RuntimeException{
	
	public DrugNotFoundException(String message) {
		super(message);
	}

}
