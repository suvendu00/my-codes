package com.cts.mailorderpharmacy.RefillMicroservice.service;


import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.mailorderpharmacy.RefillMicroservice.controller.RefillController;
import  com.cts.mailorderpharmacy.RefillMicroservice.dao.RefillOrderDao;
import  com.cts.mailorderpharmacy.RefillMicroservice.entity.RefillOrder;
import com.cts.mailorderpharmacy.RefillMicroservice.entity.Subscription;
import com.cts.mailorderpharmacy.RefillMicroservice.exception.SubscriptionIdnotFoundException;
import com.cts.mailorderpharmacy.RefillMicroservice.feignClient.FeignDrug;
import com.cts.mailorderpharmacy.RefillMicroservice.feignClient.FeignSubscription;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class  RefillOrderService
{

	@Autowired
	public RefillOrderDao refillOrderDao;

	@Autowired
	public FeignSubscription feignSubscription;
	
	@Autowired
	public FeignDrug feignDrug;
	
//	checking refill status against provided sub id
	public List<RefillOrder> viewRefillStatus(int subscriptionId) 
	 {
			ArrayList<RefillOrder> refillList = new ArrayList<>();
			List<RefillOrder> reqList=new ArrayList<>();

			   refillList = (ArrayList<RefillOrder>) refillOrderDao.findAll();
				
				int flag=0;
					for (RefillOrder refillOrder:  refillList) {
					    if(refillOrder.getSubscriptionId()== subscriptionId) {
					    	reqList.add(refillOrder);
					    	flag=1;
					    }
					}
				
					if(flag==0) {

						throw new SubscriptionIdnotFoundException("SubscriptionId provided not found in our records");
					}
				
				
			return reqList;
	}

	
// checks if all payments are made
	public boolean getRefillPaymentDues(int subscriptionId)   {
		
		// check if there are any payment dues for a subscription
			List<RefillOrder> list = refillOrderDao.findAll();

			List<RefillOrder> paymentDueList = new ArrayList<>();

			for(RefillOrder refill : list ) {
				if(refill.getSubscriptionId()==subscriptionId && refill.getPayStatus()==false) {
					paymentDueList.add(refill);
				}
			}
			if (paymentDueList.isEmpty()) {
				return false;
			} else {
				return true;
			}

	}

	
	public RefillOrder requestAdhocRefill( Boolean payStatus,String token,int subscriptionId, int quantity, String location, String memberId) 
	{
		
// cal subscription here and get name of medicine and replace with mockMedinceName
	
		      ResponseEntity<String> entityname;
		      String medecineName=null;
		      RefillOrder refillOrder = new RefillOrder();
		        try {
		        	entityname = feignSubscription.getDrugBySubscription(token,subscriptionId);	
		        }
		        catch(Exception e) {
		        	throw new  SubscriptionIdnotFoundException("SubscriptionId provided not found in our records");
		        	
		        }
		        	
		             try {
		            	 medecineName=entityname.getBody();
		            	 
		            	 ResponseEntity<String> responseEntity =feignDrug.updateQuantity(token,medecineName,location,quantity);

			 				refillOrder.setSubscriptionId(subscriptionId);
			 				refillOrder.setRefilledDate(LocalDate.now());
			 				refillOrder.setQuantity(quantity);
			 				refillOrder.setMemberId(memberId);
			 				refillOrder.setPayStatus(true);
			 				refillOrderDao.save(refillOrder);
		             }
		             catch(Exception e) {
		            	throw new SubscriptionIdnotFoundException("Drug not Found");
		             }
		        		
		 				return refillOrder;
				
}

	
	public List<RefillOrder> getRefillDuesAsOfDate( String token,String memberId, String date)
			 {
			List<RefillOrder> listOfAllDues = new ArrayList<RefillOrder>();
			
			List<Subscription> members=null;
			try {
				members= feignSubscription.getAllSubscription(token,memberId);
				for (Subscription sub : members) {

					
					LocalDate dueDate = sub.getDate().plusDays( sub.getRefillOccurrence());

						if (LocalDate.parse(date).isBefore(dueDate)) {

							listOfAllDues.addAll(refillOrderDao.findBySubscriptionId(sub.getSubscriptionId()));
							break;
						}
				}
			}
			catch(Exception e) {
				throw new SubscriptionIdnotFoundException("SubscriptionId provided not found in our records");
			}	
				return listOfAllDues;	
		}
}
