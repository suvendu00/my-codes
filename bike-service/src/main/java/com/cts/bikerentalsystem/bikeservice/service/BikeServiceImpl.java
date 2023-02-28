package com.cts.bikerentalsystem.bikeservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.query.criteria.internal.expression.ConcatExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import com.cts.bikerentalsystem.bikeservice.constant.BikeConstant;
import com.cts.bikerentalsystem.bikeservice.dao.BikeDao;
import com.cts.bikerentalsystem.bikeservice.dao.BikeLocationDao;
import com.cts.bikerentalsystem.bikeservice.entity.Bike;
import com.cts.bikerentalsystem.bikeservice.entity.BikeLocation;
import com.cts.bikerentalsystem.bikeservice.entity.Stock;
import com.cts.bikerentalsystem.bikeservice.exception.BikeNotFoundException;
import com.cts.bikerentalsystem.bikeservice.exception.StockNotAvailableException;
import com.cts.bikerentalsystem.bikeservice.exception.TypeNotFoundException;



@Service
public class BikeServiceImpl {

	@Autowired
	private BikeDao bikeRepository;
	
	@Autowired
	private BikeLocationDao bikeLocationRepository;
	
	public List<Bike> getAllBikes(){
		return bikeRepository.findAll();
	}
	
	public Optional<Bike> getBikeById(String id){
		Optional<Bike> bike = bikeRepository.findById(id);
		if(bike.isPresent())
			return bike;
		else
			throw new BikeNotFoundException(BikeConstant.bikeNotFound);
	}
	
	public Optional<Bike> getBikeByName(String name){
		Optional<Bike> bike = bikeRepository.findByModelName(name);
		if(bike.isPresent())
			return bike;
		else
			throw new BikeNotFoundException(BikeConstant.bikeNotFound);
	}
	
	public Stock getBikeByNameAndLocation(String name, String location) {
		Bike bikeDetails = null;
		try {
			bikeDetails = bikeRepository.findByModelName(name).get();
		}catch(Exception e) {
			throw new BikeNotFoundException(BikeConstant.bikeNotFound);
		}
		
		Stock stock = null;
		for(BikeLocation bl: bikeDetails.getLocationWiseAvailability()) {
			if(bl.getLocation().equals(location)) {
				stock = new Stock(bikeDetails.getBikeId(),name, bikeDetails.getManufacturer(),
						bikeDetails.getRentPrice(),bikeDetails.getType(), bl.getQuantity());
				break;
			}
		}
		
		if (stock == null)
			throw new StockNotAvailableException(BikeConstant.stockNotAvailable);
		else
		{
			return stock;
		}
	}
	
	public List<Bike> getBikeByType(String type){
		List<Bike> types = bikeRepository.findByType(type);
		if(types.isEmpty())
			throw new TypeNotFoundException(BikeConstant.typeNotAvailable);
		return types;
	}
	
	public String updateQuantity(String bikeName, String location, int quantity) {	
			Bike details = new Bike();
			try {
				details = bikeRepository.findByModelName(bikeName).get();
			} catch (Exception e) {

				throw new BikeNotFoundException(BikeConstant.bikeNotFound);
			}
			List<BikeLocation> dummy = details.getLocationWiseAvailability().stream()
					.filter(x -> x.getLocation().equalsIgnoreCase(location)).collect(Collectors.toList());

			if (dummy.isEmpty()) {
				throw new StockNotAvailableException(BikeConstant.stockNotAvailable);
			}

			else if (dummy.get(0).getQuantity() >= quantity && quantity >0) {

				BikeLocation allDetails = bikeLocationRepository.findBySerialId(dummy.get(0).getSerialId()).get(0);
				int val = allDetails.getQuantity() - quantity;
				allDetails.setQuantity(val);
				bikeLocationRepository.save(allDetails);
				return "Refill Done Successfully";
			} else
				throw new StockNotAvailableException(BikeConstant.stockNotAvailable);
	}
	
	public List<String> getTypes(){
		return bikeRepository.getAllTypes();
	}
}
