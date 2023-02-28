package com.cts.bikerentalsystem.bikeservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.bikerentalsystem.bikeservice.entity.Bike;


@Repository
public interface BikeDao extends JpaRepository<Bike, String> {

	Optional<Bike> findByModelName(String name);
	
	List<Bike> findByType(String type);
	
	@Modifying
	@Query(value = "select distinct(type) from bike", nativeQuery = true)
	List<String> getAllTypes();
}
