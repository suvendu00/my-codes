package com.cts.mailorderpharmacy.RefillMicroservice.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cts.mailorderpharmacy.RefillMicroservice.entity.RefillOrder;



public interface RefillOrderDao extends JpaRepository<RefillOrder,Integer> {
	@Modifying
	@Query(value = "SELECT s FROM RefillOrder s WHERE subscriptionId = ?1")
	 List<RefillOrder> findBySubscriptionId(int subId);

}

