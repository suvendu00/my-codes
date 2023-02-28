package com.cts.bikerentalsystem.bikeservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bike {
	@Id
	private String bikeId;
	private String modelName;
	private String manufacturer;
	private String type;
	private int rentPrice;
	@OneToMany(mappedBy = "bikeDetails")
	private List<BikeLocation> locationWiseAvailability = new ArrayList<>();
}
