package com.cts.mailorderpharmacy.SubscriptionMicroservice.model;



import java.util.ArrayList;

import java.util.Date;
import java.util.List;


import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DrugDetails {

	/**
	 * Id for the drug 
	 */
	private String drugId;
	/**
	 * Name of the drug
	 */
	private String drugName;
	/**
	 * name of the manufacturer
	 */
	private String manufacturer;
	/**
	 * Manufacture date
	 */
	private String manufactureDate;
	/**
	 * Expiry date
	 */
	private String expiryDate;

//	@OneToMany(mappedBy="drugDetails")
	private List<DrugLocationDetails> druglocationQuantities = new ArrayList<>();
}

