package com.cts.mailorderpharmacy.SubscriptionMicroservice.sevice;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.mailorderpharmacy.SubscriptionMicroservice.exception.DrugNotFoundException;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.exception.SubscriptionListEmptyException;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.exception.SubscriptionNotFoundException;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.model.MemberPrescription;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.model.MemberSubscription;

public interface SubscriptionService {

	public ResponseEntity<String> subscribe(MemberPrescription prescription,@RequestHeader("Authorization") String token) throws DrugNotFoundException;
	
	public ResponseEntity<MemberSubscription> getSubscription( int id)
			throws SubscriptionNotFoundException;
	
	public ResponseEntity<String> getDrugBySubscription( int id)
			throws SubscriptionNotFoundException;
	
	public ResponseEntity<String> unsubscribe(String token,String memberId, int subscriptionId) ;
	
	
	public List<MemberSubscription> getAllSubscription(String memberId) throws SubscriptionListEmptyException;
}
