package com.cts.mailorderpharmacy.RefillMicroservice.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Subscription {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subscriptionId;
	private int prescriptionId;
	private String memberId;
	private LocalDate date;
	private int quantity;
	private String drugName;
	private int refillOccurrence; //it is used to tell occurrence i.e, the cycle of refill
	private String memberLocation;
	private String status;
}





