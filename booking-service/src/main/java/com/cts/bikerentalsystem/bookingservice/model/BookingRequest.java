package com.cts.bikerentalsystem.bookingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

	private String userName;
	private String modelName;
	private String licenseNo;
	private int duration;
	private String location;
	private int quantity;
}
