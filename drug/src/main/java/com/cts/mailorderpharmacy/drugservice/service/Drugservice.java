package com.cts.mailorderpharmacy.drugservice.service;



import java.util.List;
import java.util.Optional;

import com.cts.mailorderpharmacy.drugservice.entity.Drug;
import com.cts.mailorderpharmacy.drugservice.entity.Stock;
public interface Drugservice {

	public List<Drug> getAllDrugs();
	
	public Optional<Drug> getDrugById(String id);
	
	public Optional<Drug> getDrugByName(String name);
	
	public Stock getDrugByIdAndLocation(String id, String location);
	
	public String updateQuantity(String drugName, String location, int quantity);
	
}
