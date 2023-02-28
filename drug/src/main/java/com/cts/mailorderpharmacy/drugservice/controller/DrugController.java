package com.cts.mailorderpharmacy.drugservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mailorderpharmacy.drugservice.entity.Drug;
import com.cts.mailorderpharmacy.drugservice.entity.Stock;
import com.cts.mailorderpharmacy.drugservice.exception.StockNotFoundException;
import com.cts.mailorderpharmacy.drugservice.service.Drugservice;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Suvendu Gorain
 * date created - 27-july-2022
 *  this is controller class to access rest end points
 */

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/drugservice")
public class DrugController {
	
	@Autowired
	Drugservice service;
	
	
	
	@GetMapping("/getAllDrugs")
	public List<Drug> getAllDrugs(){
		log.info("started drugcontroller getAllDrugs");
		return service.getAllDrugs();
	}
	
	
	@GetMapping("/searchDrugsById/{id}")
	public Optional<Drug> getDrugsById(@PathVariable("id") String id) {	
		log.info("started drugcontroller getDrugsById");
		return service.getDrugById(id);
	}
	
	
	@GetMapping("/searchDrugsByName/{name}")
	public Optional<Drug> getDrugsByName(@PathVariable("name") String name){
		log.info("started drugcontroller getDrugsByName");
		return service.getDrugByName(name);
	}
	
	@GetMapping("/searchDrugsByIdAndLocation/{id}/{location}")
	public Stock getDrugsByIdAndLocation(@PathVariable("id") String id, @PathVariable("location") String location) {
		log.info("started drugcontroller getDrugsByIdAndLocation");
		return service.getDrugByIdAndLocation(id, location);
	}
	
	@GetMapping("/updateDispatchableDrugStock/{name}/{location}/{quantity}")
	public String updateQuantity(@PathVariable("name") String name,
			@PathVariable("location") String location, @PathVariable("quantity") int quantity)
			throws StockNotFoundException {
			log.info("started drugcontroller updateQuantity");
			return service.updateQuantity(name, location, quantity);
		
	}

}
