package com.cts.mailorderpharmacy.drugservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 
 * @author Suvendu Gorain
 * date created - 26-july-2022
 *  this a model and entity class for drug location details
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DrugLocation {
	
	@Id
	private String serialId;
	private String location;
	private int quantity;
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "drugId")
	private Drug drugDetails;
}
