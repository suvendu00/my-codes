package com.cts.bikerentalsystem.bikeservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bikerentalsystem.bikeservice.entity.Bike;
import com.cts.bikerentalsystem.bikeservice.entity.Stock;
import com.cts.bikerentalsystem.bikeservice.service.BikeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/bikeservice")
@Slf4j
public class Bikecontroller {

	@Autowired
	private BikeServiceImpl service;
	
	@GetMapping()
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/getAllBikes")
	public List<Bike> getAllBikes() {
		log.info("started bikecontroller getAllBikes");
		return service.getAllBikes();
	}
	
	@GetMapping("/getBikeById/{id}")
	public Optional<Bike> getBikeById(@PathVariable("id") String id) {
		log.info("started bikecontroller getBikeById");
		return service.getBikeById(id);
	}
	
	@GetMapping("getBikeByName/{name}")
	public Optional<Bike> getBikeByName(@PathVariable("name") String name) {
		log.info("started bikecontroller getBikeByName");
		return service.getBikeByName(name);
	}
	
	@GetMapping("/getBikeByType/{type}")
	public List<Bike> getBikeByType(@PathVariable("type") String type) {
		log.info("started bikecontroller getBikeByType");
		return service.getBikeByType(type);
	}
	
	@GetMapping("getBikeByNameAndLocation/{name}/{location}")
	public Stock getBikeByNameAndLocation(@PathVariable("name") String name, @PathVariable("location") String location) {
		log.info("started bikecontroller getBikeByNameAndLocation");
		return service.getBikeByNameAndLocation(name, location);
	}
	
	@GetMapping("/updateQuantity/{name}/{location}/{quantity}")
	public String updateQuantity(@PathVariable("name") String name,
			@PathVariable("location") String location, @PathVariable("quantity") int quantity) {
			log.info("started drugcontroller updateQuantity");
			return service.updateQuantity(name, location, quantity);
		
	}
	
	@GetMapping("types")
	public List<String> getTypes(){
		return service.getTypes();
	}
}
