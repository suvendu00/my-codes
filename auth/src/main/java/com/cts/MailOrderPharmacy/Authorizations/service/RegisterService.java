package com.cts.MailOrderPharmacy.Authorizations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.MailOrderPharmacy.Authorizations.dao.UserDao;
import com.cts.MailOrderPharmacy.Authorizations.entity.User;

@Service
public class RegisterService {

	@Autowired
	private UserDao dao;
	
	public String doRegister(User user) {
		User newUser = new User();
		
		newUser.setFullName(user.getFullName());
		newUser.setEmail(user.getEmail());
		newUser.setPhno(user.getPhno());
		newUser.setUserName(user.getUserName());
		newUser.setPassword(user.getPassword());
		
		dao.save(newUser);
		return "Hi "+user.getFullName()+ " You Have Registered Successfully";
	}
}
