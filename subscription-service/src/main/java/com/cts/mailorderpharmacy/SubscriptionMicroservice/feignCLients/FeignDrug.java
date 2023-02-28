package com.cts.mailorderpharmacy.SubscriptionMicroservice.feignCLients;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.mailorderpharmacy.SubscriptionMicroservice.exception.DrugNotFoundException;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.model.DrugDetails;

@FeignClient(value="Drug", url = "localhost:8087/drugservice")
public interface FeignDrug {

	
	@GetMapping("/searchDrugsByName/{name}")
	public Optional<DrugDetails> getDrugByName(@RequestHeader("Authorization") String token, @PathVariable("name") String name)
			throws DrugNotFoundException;
	
	@GetMapping("/updateDispatchableDrugStock/{name}/{location}/{quantity}")
	public String updateQuantity(@RequestHeader("Authorization") String token,@PathVariable("name") String name, @PathVariable("location") String location,
			@PathVariable("quantity") int quantity);


}
