package com.cts.mailorderpharmacy.SubscriptionMicroservice.sevice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.mailorderpharmacy.SubscriptionMicroservice.exception.DrugNotFoundException;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.exception.SubscriptionListEmptyException;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.exception.SubscriptionNotFoundException;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.feignCLients.FeignDrug;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.feignCLients.FeignRefill;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.model.DrugDetails;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.model.MemberPrescription;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.model.MemberSubscription;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.dao.MemberPrescriptiondao;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.dao.MemberSubscriptiondao;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	MemberSubscriptiondao subscriptionDao;

	@Autowired
	MemberPrescriptiondao prescriptionDao;

	@Autowired
	FeignDrug feignDrug;

	@Autowired
	FeignRefill feignRefill;

	String exceptionMessage = "Token is null";

	public ResponseEntity<String> subscribe(MemberPrescription prescription,
			@RequestHeader("Authorization") String token) throws DrugNotFoundException {

		MemberSubscription subscribe = new MemberSubscription();

		Optional<DrugDetails> drugDetails = null;

		try {
			drugDetails = feignDrug.getDrugByName(token, prescription.getDrugName());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new DrugNotFoundException("Drug not found");
		}

		String ss = feignDrug.updateQuantity(token, prescription.getDrugName(), prescription.getMemberLocation(),
				prescription.getQuantity());

		prescriptionDao.save(prescription);

		subscribe.setDrugName(drugDetails.get().getDrugName());
		subscribe.setMemberId(prescription.getMemberId());
		subscribe.setMemberLocation(prescription.getMemberLocation());
		subscribe.setPrescriptionId(prescription.getId());
		subscribe.setQuantity(prescription.getQuantity());
		subscribe.setDate(LocalDate.now());
		subscribe.setStatus("active");
		subscribe.setRefillOccurrence(prescription.getCourseDuration());
		subscriptionDao.save(subscribe);

		return new ResponseEntity<>("\"You have successfully subscribed \"", HttpStatus.OK);

	}

	// done
	public ResponseEntity<MemberSubscription> getSubscription(int id) throws SubscriptionNotFoundException {
		Optional<MemberSubscription> optional = subscriptionDao.findById(id);

		if (!optional.isPresent())
			throw new SubscriptionNotFoundException();

		return new ResponseEntity<>(optional.get(), HttpStatus.OK);

	}

	// done
	public ResponseEntity<String> getDrugBySubscription(int id) throws SubscriptionNotFoundException {

		List<MemberSubscription> optional = subscriptionDao.findBySubscriptionId(id);

		if (optional.isEmpty())
			throw new SubscriptionNotFoundException();

		return new ResponseEntity<>(optional.get(0).getDrugName(), HttpStatus.OK);

	}

	public ResponseEntity<String> unsubscribe(String token, String memberId, int subscriptionId) {

//		String token="";

		boolean result = feignRefill.getRefillPaymentDues(token, subscriptionId);

		if (!result) {
			subscriptionDao.deleteById(subscriptionId);
			return new ResponseEntity<>("You have succesfully Unsubscribed", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("You have to clear your payment dues first.", HttpStatus.OK);
		}

	}

	// done
	public List<MemberSubscription> getAllSubscription(String memberId) throws SubscriptionListEmptyException {
		if (subscriptionDao.findByMemberId(memberId).isEmpty()) {
			throw new SubscriptionListEmptyException();
		}

		List<MemberSubscription> allDetails = subscriptionDao.findByMemberId(memberId);
		return allDetails;
	}

}