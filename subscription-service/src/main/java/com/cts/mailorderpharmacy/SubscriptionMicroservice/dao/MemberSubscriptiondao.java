package com.cts.mailorderpharmacy.SubscriptionMicroservice.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cts.mailorderpharmacy.SubscriptionMicroservice.model.MemberSubscription;


@Repository
public interface MemberSubscriptiondao extends JpaRepository<MemberSubscription, Integer> {
	@Query(value = "SELECT s FROM MemberSubscription s WHERE MEMBER_ID = ?1")
	 List<MemberSubscription> findByMemberId(String mId);
	
	@Modifying
	@Query(value = "SELECT * FROM member_subscription  WHERE  subscription_id=?1",nativeQuery = true)
	 List<MemberSubscription> findBySubscriptionId(  int sId);

}
