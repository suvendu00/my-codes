package com.cts.mailorderpharmacy.SubscriptionMicroservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.mailorderpharmacy.SubscriptionMicroservice.model.MemberPrescription;

@Repository
public interface MemberPrescriptiondao extends JpaRepository<MemberPrescription, Long> {

	
}
