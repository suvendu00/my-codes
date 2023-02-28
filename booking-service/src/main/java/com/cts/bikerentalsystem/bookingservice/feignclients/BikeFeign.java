package com.cts.bikerentalsystem.bookingservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.bikerentalsystem.bookingservice.exception.BikeNotFoundException;
import com.cts.bikerentalsystem.bookingservice.exception.StockNotAvailableException;
import com.cts.bikerentalsystem.bookingservice.model.Stock;

@FeignClient(value="bike", url="localhost:9091/bikeservice")
public interface BikeFeign {

	@GetMapping("getBikeByNameAndLocation/{name}/{location}")
	public Stock getBikeByNameAndLocation(@RequestHeader("Authorization") String token, @PathVariable("name") String name, @PathVariable("location") String location) 
			throws BikeNotFoundException, StockNotAvailableException;
	
	@GetMapping("/updateQuantity/{name}/{location}/{quantity}")
	public String updateQuantity(@RequestHeader("Authorization") String token, @PathVariable("name") String name, @PathVariable("location") String location,
			@PathVariable("quantity") int quantity);
}
