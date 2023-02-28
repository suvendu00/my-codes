package com.cts.bikerentalsystem.bikeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

	private String bikeId;
	private String bikeName;
	private String manufacturer;
	private int rentPrice;
	private String type;
	private int quantity;
}
