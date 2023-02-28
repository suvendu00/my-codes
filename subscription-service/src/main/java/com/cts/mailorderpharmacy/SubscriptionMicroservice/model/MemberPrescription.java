package com.cts.mailorderpharmacy.SubscriptionMicroservice.model;


import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberPrescription {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String memberId;
	private String memberLocation;
	private String policyNumber;
	private String insuranceProvider;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
	private String dosage;
	private int quantity;
	private String drugName;
	private String doctorDetails;
	private int courseDuration;
}

