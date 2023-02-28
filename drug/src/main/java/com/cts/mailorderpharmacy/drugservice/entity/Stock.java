package com.cts.mailorderpharmacy.drugservice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Suvendu Gorain
 * date created - 26-july-2022
 *  this a pojo class to return response of stock available
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

	private String drugId;
	private String drugName;
	private String expiryDate;
	private int quantity;

}
