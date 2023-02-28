package com.cts.bikerentalsystem.bookingservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bike {
	private String bikeId;
	private String modelName;
	private String manufacturer;
	private String type;
	private int rentPrice;
	private List<BikeLocation> locationWiseAvailability = new ArrayList<>();
}
