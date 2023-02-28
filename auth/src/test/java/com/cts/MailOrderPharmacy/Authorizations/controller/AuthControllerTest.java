package com.cts.MailOrderPharmacy.Authorizations.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cts.MailOrderPharmacy.Authorizations.Controller.LoginController;
import com.cts.MailOrderPharmacy.Authorizations.dao.UserDao;
import com.cts.MailOrderPharmacy.Authorizations.entity.MyUserDetails;
import com.cts.MailOrderPharmacy.Authorizations.entity.User;
import com.cts.MailOrderPharmacy.Authorizations.service.JwtUserDetailService;

@SpringBootTest
public class AuthControllerTest {

	@MockBean
	User user;

	@MockBean
	MyUserDetails userDetails;

	@MockBean
	JwtUserDetailService jwtUserDetailService;

	@Autowired
	UserDao userdao;

	@MockBean
	LoginController login;

	@Test
	public void generateTokenTest() throws Exception {

		//String token;

//		user = new User(101, "sumit", "sumit123@", true, "ROLE_ADMIN");
//		String token = JWT.create()
//	            .withSubject(user.getUserName())
//	             .withExpiresAt(new Date(System.currentTimeMillis() + 60000*3))
//	             .sign(Algorithm.HMAC512("mailorderpharmacy"));
		
		
		
		
		
		
		
		//user=userdao.findByUserName("sujit").get();
		// System.out.println(users.getPassword());

		//user = userdao.findByUserName("sujit").get();
		//System.out.println(user.getPassword());
		// System.out.println(users.getPassword());
		// String tok=login.authenticate(users.getUserName(), users.getPassword());

		 //UserDetails userDetail =jwtUserDetailService.loadUserByUsername(user.getUserName());
		// System.out.println(userDetail.getUsername());

		//token = login.authenticate(user.getUserName(), user.getPassword());
		
		
		
		
//		System.out.println(token);
//		assertNotNull(token);

	}

}
