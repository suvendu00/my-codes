package com.cts.mailorderpharmacy.SubscriptionMicroservice.feignCLients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "feignRefill",url = "localhost:8094/")
public interface FeignRefill {

	@GetMapping("/getRefillPaymentDues/{subscriptionId}")
	public boolean  getRefillPaymentDues(@RequestHeader("Authorization")
	  String token, @PathVariable("subscriptionId") long subscriptionId) ;
	
	
}
