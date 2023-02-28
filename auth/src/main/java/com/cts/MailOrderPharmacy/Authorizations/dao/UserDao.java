package com.cts.MailOrderPharmacy.Authorizations.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cts.MailOrderPharmacy.Authorizations.entity.User;




public interface UserDao extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);

}