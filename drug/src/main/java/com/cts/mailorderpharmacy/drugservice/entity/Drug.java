package com.cts.mailorderpharmacy.drugservice.entity;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Suvendu Gorain
 * date created - 26-july-2022
 *  this a model and entity class for drug details
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drug {
	@Id
	private String drugId;
	private String drugName;
	private String manufacturer;
	private String manufactureDate;
	private String expiryDate;
	private int unitsInAPackage;
	private int costPerPackage;
	@OneToMany(mappedBy = "drugDetails")
	private List<DrugLocation> locationWiseQuantity = new ArrayList<>();
}
