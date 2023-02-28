package com.cts.bikerentalsystem.bookingservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.bikerentalsystem.bookingservice.model.BookingDetails;

public interface BookingDao extends JpaRepository<BookingDetails, Integer>{

	public List<BookingDetails> findByUserName(String userName);
}
