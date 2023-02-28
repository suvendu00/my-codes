package com.cts.bikerentalsystem.bookingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bikerentalsystem.bookingservice.model.BookingDetails;
import com.cts.bikerentalsystem.bookingservice.model.BookingRequest;
import com.cts.bikerentalsystem.bookingservice.service.BookingServiceImpl;

@RestController
@RequestMapping("/bookingservice")
@CrossOrigin(origins =  "http://localhost:4200")
public class BookingController {

	@Autowired	
	private BookingServiceImpl service;
	
	@GetMapping
	public String hello() {
		return "Hello from booking service";
	}
	
	@PostMapping("/book")
	public String book(@RequestBody BookingRequest request, @RequestHeader("Authorization") String token) {
		return service.book(request, token);
	}
	
	@GetMapping("/getBookingDetails/{userName}")
	public List<BookingDetails> getBookingDetails(@PathVariable("userName") String userName) {
		return service.getBookingDetails(userName);
	}
	
	@GetMapping("/cancelBooking/{bookingId}")
	public String cancelBooking(@PathVariable("bookingId") int bookingId) {
		return service.cancelBooking(bookingId);
	}
}
