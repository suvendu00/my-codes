package com.cts.bikerentalsystem.bookingservice.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.bikerentalsystem.bookingservice.dao.BookingDao;
import com.cts.bikerentalsystem.bookingservice.exception.BikeNotFoundException;
import com.cts.bikerentalsystem.bookingservice.exception.BookingNotFoundException;
import com.cts.bikerentalsystem.bookingservice.exception.StockNotAvailableException;
import com.cts.bikerentalsystem.bookingservice.feignclients.BikeFeign;
import com.cts.bikerentalsystem.bookingservice.model.BookingConstant;
import com.cts.bikerentalsystem.bookingservice.model.BookingDetails;
import com.cts.bikerentalsystem.bookingservice.model.BookingRequest;
import com.cts.bikerentalsystem.bookingservice.model.Stock;

@Service
public class BookingServiceImpl {

	@Autowired
	private BookingDao dao;
	
	@Autowired
	private BikeFeign bikeFeign;
	
	public String book(BookingRequest request, String token) {
		
		BookingDetails book = new BookingDetails();
		
		Stock bikeDetails = null;
		
		try {
			bikeDetails = bikeFeign.getBikeByNameAndLocation(token, request.getModelName(), request.getLocation());
		}catch (Exception e) {
			if(e.getMessage().contains(BookingConstant.bikeNotFound)) 
				throw new BikeNotFoundException(BookingConstant.bikeNotFound);
			else 
				throw new StockNotAvailableException(BookingConstant.stockNotAvailable);
		}
		
		bikeFeign.updateQuantity(token, request.getModelName(), request.getLocation(), request.getQuantity());
		
		book.setUserName(request.getUserName());
		book.setModelName(request.getModelName());
		book.setManufacturer(bikeDetails.getManufacturer());
		book.setBikeId(bikeDetails.getBikeId());
		book.setLocation(request.getLocation());
		book.setLicenseNo(request.getLicenseNo());
		book.setDuration(request.getDuration());
		book.setCost(bikeDetails.getRentPrice() * request.getDuration());
		
		BookingDetails temp = dao.save(book);
		
		return "\"Booking Done Successfully \"";
		
	}
	
	public List<BookingDetails> getBookingDetails(String userName) {
		
		List<BookingDetails> bookings = dao.findByUserName(userName);
		if(!bookings.isEmpty())
			return bookings;
		else 
			throw new BookingNotFoundException("You Have Not Made Any Bookings Yet");
	}
	
	public String cancelBooking(int bookingId) {
		
		Optional<BookingDetails> booking = dao.findById(bookingId);
		
		if(booking.isEmpty()) {
			return "No Booking Details Found";
		}else {
			dao.deleteById(bookingId);
			return "\"Booking Cancelled Successfully\"";
		}
		
	}
}
