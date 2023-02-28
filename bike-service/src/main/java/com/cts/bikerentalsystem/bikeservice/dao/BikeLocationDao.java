package com.cts.bikerentalsystem.bikeservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.bikerentalsystem.bikeservice.entity.BikeLocation;

@Repository
public interface BikeLocationDao extends JpaRepository<BikeLocation, String> {

	List<BikeLocation> findBySerialId(String serialId);

}
