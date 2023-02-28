package com.cts.mailorderpharmacy.RefillMicroservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.mailorderpharmacy.RefillMicroservice.entity.Subscription;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "Subscription",url = "http://mail-order-load-balancer-1485013476.eu-west-3.elb.amazonaws.com:8095/api")
public interface FeignSubscription {
	
	@GetMapping("/getdrugbysubscription/{id}")
	public ResponseEntity<String> getDrugBySubscription(@RequestHeader("Authorization") String token, @PathVariable("id") int sId) ;

	@GetMapping("/getAllSubscriptions/{mid}")
	public  List<Subscription> getAllSubscription(@RequestHeader("Authorization") String token,@PathVariable("mid") String memberId) ;
}
