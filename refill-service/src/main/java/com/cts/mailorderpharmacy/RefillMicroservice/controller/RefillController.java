package com.cts.mailorderpharmacy.RefillMicroservice.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.cts.mailorderpharmacy.RefillMicroservice.entity.RefillOrder;
import com.cts.mailorderpharmacy.RefillMicroservice.exception.SubscriptionIdnotFoundException;
import com.cts.mailorderpharmacy.RefillMicroservice.service.RefillOrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
public class RefillController {


	@Autowired
	public RefillOrderService refillService;	
	
	
	//fetches details of refill status
	@GetMapping(path = "/viewRefillStatus/{subsciptionid}")
	public ResponseEntity<List<RefillOrder>> viewRefillStatus(@PathVariable("subsciptionid") int subsciptionid) throws SubscriptionIdnotFoundException 
	{
		log.info("Inside Refill Controller viewRefillStatus method");
		return new ResponseEntity(refillService.viewRefillStatus(subsciptionid), HttpStatus.OK);
	}
	
	
	
	//used in making a new refill request
	@PostMapping(path = "/requestAdhocRefill/{subscriptionId}/{quantity}/{location}/{memberid}/{payStatus}")
	public ResponseEntity<RefillOrder> requestAdhocRefill(@RequestHeader("Authorization") String token,
			@PathVariable("subscriptionId") int subscriptionId, @PathVariable("quantity") int quantity, @PathVariable Boolean payStatus, @PathVariable("location") String location,
			@PathVariable("memberid") String memberId) 
			 {
		
		log.info("Inside Refill Controller requestAdhocRefill method");
		return new ResponseEntity(refillService.requestAdhocRefill(payStatus,token,subscriptionId,  quantity, location,  memberId), HttpStatus.OK);
		
		
	}
	
	
	//get all refill dues according to mentioned date
	@GetMapping(path = "/getRefillDuesAsOfDate/{memberId}/{date}")
	public ResponseEntity<List<RefillOrder>> getRefillDuesAsOfDate(@RequestHeader("Authorization") String token,@PathVariable("memberId") String memberId, @PathVariable("date") String date) {
		log.info("Inside Refill Controller getRefillDuesAsOfDate method");
		return new ResponseEntity(refillService.getRefillDuesAsOfDate(token,memberId, date), HttpStatus.OK);
			
	
	}
	
	//check if all payments are clear
	@GetMapping(path = "/getRefillPaymentDues/{subscriptionId}")
	public ResponseEntity<Boolean> getRefillPaymentDues(@PathVariable("subscriptionId") int subscriptionId)  {
		log.info("Inside Refill Controller getRefillPaymentDues method");
		return ResponseEntity.ok().body(refillService.getRefillPaymentDues(subscriptionId));
	
	}
}
