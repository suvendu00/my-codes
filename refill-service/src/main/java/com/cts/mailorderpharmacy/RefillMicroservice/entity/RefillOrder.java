package com.cts.mailorderpharmacy.RefillMicroservice.entity;

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


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefillOrder {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;

	int subscriptionId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate refilledDate;

	int quantity;
	private Boolean payStatus;
	String memberId;
			
	
}
