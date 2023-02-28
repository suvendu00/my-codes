package com.cts.mailorderpharmacy.drugservice.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.mailorderpharmacy.drugservice.constant.DrugConstant;
import com.cts.mailorderpharmacy.drugservice.dao.DrugLocationRepository;
import com.cts.mailorderpharmacy.drugservice.dao.DrugRepository;
import com.cts.mailorderpharmacy.drugservice.entity.Drug;
import com.cts.mailorderpharmacy.drugservice.entity.DrugLocation;
import com.cts.mailorderpharmacy.drugservice.entity.Stock;
import com.cts.mailorderpharmacy.drugservice.exception.DrugNotFoundException;
import com.cts.mailorderpharmacy.drugservice.exception.StockNotFoundException;

/**
 * 
 * @author Suvendu Gorain
 * date created - 27-july-2022
 *  this is a service class to retrieve data from database
 */

@Service
public class DrugserviceImpl implements Drugservice{

	@Autowired
	private DrugRepository drugRepository;
	
	@Autowired
	DrugLocationRepository drugLocationRepository;
	
	public List<Drug> getAllDrugs(){
		return drugRepository.findAll();
	}
	
	
	public Optional<Drug> getDrugById(String id){
		Optional<Drug> drugs = drugRepository.findById(id);
		if(drugs.isPresent())
			return drugs;
		else
			throw new DrugNotFoundException(DrugConstant.drugNotFound);
		
	}
	
	
	public Optional<Drug> getDrugByName(String name) {
		Optional<Drug> drugDetails = drugRepository.findByDrugName(name);
		if(drugDetails.isPresent()) {
			return drugDetails;
		}else {
			throw new DrugNotFoundException(DrugConstant.drugNotFound);
		}
	}
	
	

	public Stock getDrugByIdAndLocation(String id, String location) {
		Drug drugDetails = null;

		try {
			drugDetails = drugRepository.findById(id).get();
		}catch (Exception e) {
			throw new DrugNotFoundException(DrugConstant.drugNotFound);
		}
		Stock stock = null;
		for(DrugLocation dl : drugDetails.getLocationWiseQuantity()) {
			if(dl.getLocation().equals(location)) {
				stock = new Stock(id, drugDetails.getDrugName(), drugDetails.getExpiryDate(), dl.getQuantity());
				break;
			}
		}
		if (stock == null)
			throw new StockNotFoundException(DrugConstant.stockNotFound);
		else
		{
			return stock;
		}
	}
	
	public String updateQuantity(String drugName, String location, int quantity)
			throws  StockNotFoundException {	
			Drug details = new Drug();
			try {
				details = drugRepository.findByDrugName(drugName).get();
			} catch (Exception e) {

				throw new DrugNotFoundException(DrugConstant.drugNotFound);
			}
			List<DrugLocation> dummy = details.getLocationWiseQuantity().stream()
					.filter(x -> x.getLocation().equalsIgnoreCase(location)).collect(Collectors.toList());

			if (dummy.isEmpty()) {
				throw new StockNotFoundException(DrugConstant.stockNotFound);
			}

			else if (dummy.get(0).getQuantity() >= quantity && quantity >0) {

				DrugLocation allDetails = drugLocationRepository.findBySerialId(dummy.get(0).getSerialId()).get(0);
				int val = allDetails.getQuantity() - quantity;
				allDetails.setQuantity(val);
				drugLocationRepository.save(allDetails);
				return "Refill Done Successfully";
			} else
				throw new StockNotFoundException(DrugConstant.stockNotFound);
	}
	
	

}
