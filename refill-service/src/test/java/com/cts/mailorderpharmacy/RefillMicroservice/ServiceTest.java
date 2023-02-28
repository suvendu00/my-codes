package com.cts.mailorderpharmacy.RefillMicroservice;



import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.mailorderpharmacy.RefillMicroservice.dao.RefillOrderDao;
import com.cts.mailorderpharmacy.RefillMicroservice.entity.RefillOrder;
import com.cts.mailorderpharmacy.RefillMicroservice.entity.Subscription;
import com.cts.mailorderpharmacy.RefillMicroservice.exception.SubscriptionIdnotFoundException;
import com.cts.mailorderpharmacy.RefillMicroservice.feignClient.FeignDrug;
import com.cts.mailorderpharmacy.RefillMicroservice.feignClient.FeignSubscription;
import com.cts.mailorderpharmacy.RefillMicroservice.service.RefillOrderService;

@SpringBootTest
public class ServiceTest {

	@InjectMocks
	RefillOrderService refillOrderService;


	@Mock
	FeignDrug feignDrug;

	@Mock
	FeignSubscription feignSubscription;

	@Mock
	RefillOrderDao refillOrderdao;


	@Test
	void viewRefillStatus() throws SubscriptionIdnotFoundException {

		ArrayList<RefillOrder> list = new ArrayList<>();
		RefillOrder refillOrder = new RefillOrder(1, 1, LocalDate.now(),1, true, "1");
		list.add(refillOrder);

	
		when(refillOrderdao.findAll()).thenReturn(list);
		List<RefillOrder> actual = (refillOrderService.viewRefillStatus(1));
		System.out.print(actual);
		assertEquals(list, actual);

	}
	
	 @Test 
	 void requestAdhocRefill() { 
		 RefillOrder refillOrder = new RefillOrder(1, 1, LocalDate.now(),1, true, "1");
		  
		  ResponseEntity<String> entityname = new ResponseEntity<String>("Paracetamol", HttpStatus.OK); 
			
		  
		  when(feignSubscription.getDrugBySubscription("token", 1)).thenReturn(entityname); 
		  ResponseEntity<String> responseValue = new  ResponseEntity("{responseMessage=Refill Done Successfully}",HttpStatus.OK); 
		
		  when(feignDrug.updateQuantity("token", entityname.getBody(), "kolkata", 1)).thenReturn( responseValue); 
		  
		  when(refillOrderService.requestAdhocRefill(true,"token",1, 1, "kolkata", "1")).thenReturn(refillOrder);
		  
		   when(refillOrderdao.save(refillOrder)).thenReturn(refillOrder);
		   RefillOrder requestAdhocRefill = refillOrderService.requestAdhocRefill(true,"token",1, 1, "kolkata", "1");
		 
		  
		  assertEquals(refillOrder.getSubscriptionId()+refillOrder.getMemberId()+refillOrder.getQuantity()
		  , requestAdhocRefill.getSubscriptionId()+requestAdhocRefill.getMemberId()+requestAdhocRefill.getQuantity()); 
	 
	 

	  
	 }
	


		@Test
		void getRefillDuesAsOfDate() {
			
			
			ArrayList<Subscription> memberList = new ArrayList<>();
			memberList.add(new Subscription(1, 1, "naved", LocalDate.now(), 10, "Paracetamol", 3, "kolkata", "true"));
			memberList.add(new Subscription(2, 2, "naved", LocalDate.now(), 20, "Paracitamol2", 3, "kolkata", "true"));
			ArrayList<RefillOrder> list = new ArrayList<>();
			RefillOrder refillOrder = new RefillOrder(1, 1,LocalDate.now(),1, false, "1");
			RefillOrder refillOrder2 = new RefillOrder(2,2, LocalDate.now(),2, false, "2");
			list.add(refillOrder);
			list.add(refillOrder2);

			
			when(feignSubscription.getAllSubscription("token", "naved")).thenReturn(memberList);
			when(refillOrderdao.findAll()).thenReturn((ArrayList<RefillOrder>) list);
			when(refillOrderdao.findBySubscriptionId(1)).thenReturn(list);
			List<RefillOrder> refillDuesAsOfDate = refillOrderService.getRefillDuesAsOfDate("token","naved", "2022-08-02");
			
			assertNotEquals(list, refillOrder);
			
		}

}
