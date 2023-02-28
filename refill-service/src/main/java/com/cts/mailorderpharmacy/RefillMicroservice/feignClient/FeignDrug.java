package com.cts.mailorderpharmacy.RefillMicroservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(url = "http://mail-order-load-balancer-1485013476.eu-west-3.elb.amazonaws.com:8087/drugservice",name = "Drug")
@Component
public interface FeignDrug {
	
	
	@GetMapping("/updateDispatchableDrugStock/{name}/{location}/{quantity}")
	public ResponseEntity<String> updateQuantity(@RequestHeader("Authorization") String token,@PathVariable("name") String name, @PathVariable("location") String location,
			@PathVariable("quantity") int quantity);
}

