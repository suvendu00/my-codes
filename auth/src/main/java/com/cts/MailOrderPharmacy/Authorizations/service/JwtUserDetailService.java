package com.cts.MailOrderPharmacy.Authorizations.service;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cts.MailOrderPharmacy.Authorizations.dao.UserDao;
import com.cts.MailOrderPharmacy.Authorizations.entity.MyUserDetails;
import com.cts.MailOrderPharmacy.Authorizations.entity.User;

@Component
public class JwtUserDetailService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optionaluserList = userDao.findByUserName(username);
		if(optionaluserList.isPresent()) {
			User user=optionaluserList.get();
			
			return new MyUserDetails(user);
		}else {
			throw new UsernameNotFoundException("User not found with username: " + username);

		}
	}

}
