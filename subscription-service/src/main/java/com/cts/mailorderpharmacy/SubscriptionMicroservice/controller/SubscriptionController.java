package com.cts.mailorderpharmacy.SubscriptionMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mailorderpharmacy.SubscriptionMicroservice.exception.DrugNotFoundException;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.exception.SubscriptionListEmptyException;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.exception.SubscriptionNotFoundException;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.model.MemberPrescription;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.model.MemberSubscription;

import com.cts.mailorderpharmacy.SubscriptionMicroservice.sevice.SubscriptionService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class SubscriptionController {

	@Autowired
	SubscriptionService subscriptionService;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	
	@PostMapping("/subscribe")
	public ResponseEntity<String> subscription(@RequestBody MemberPrescription prescription,@RequestHeader("Authorization") String token) throws DrugNotFoundException{
			return subscriptionService.subscribe(prescription,token);
	}

	
	@PostMapping("/unsubscribe/{mid}/{id}")
	public ResponseEntity<String> unsubscribe(@RequestHeader("Authorization") String token,@PathVariable("mid") String memberId, @PathVariable("id") int subscriptionId) throws SubscriptionNotFoundException
			{
		try {
			return subscriptionService.unsubscribe(token,memberId, subscriptionId);
		} 
		catch(EmptyResultDataAccessException e)
		{
			throw new SubscriptionNotFoundException("No subscription found to unsubscribe");
		}

	}


	@GetMapping("/getdrugbysubscription/{id}")
	public ResponseEntity<String> getDrugBySubscription(@PathVariable int id) throws SubscriptionNotFoundException {
		try {
			return subscriptionService.getDrugBySubscription(id);
		} catch (SubscriptionNotFoundException e) {
			throw new SubscriptionNotFoundException("Subscription Not found");
		} 
		
	}

	@GetMapping("/getAllSubscriptions/{mid}")
	public List<MemberSubscription> getAllSubscription(@PathVariable("mid") String memberId) throws SubscriptionListEmptyException {
		try {
			
			return subscriptionService.getAllSubscription(memberId);
		} 
	    catch (SubscriptionListEmptyException e) {
			throw new SubscriptionListEmptyException("No subscription Found.Please subscribe");
		}

	}
	
	

}
