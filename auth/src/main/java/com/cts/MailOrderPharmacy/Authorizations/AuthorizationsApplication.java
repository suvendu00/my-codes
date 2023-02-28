package com.cts.MailOrderPharmacy.Authorizations;

import org.springframework.boot.SpringApplication;import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cts.MailOrderPharmacy.Authorizations.dao.UserDao;
import com.cts.MailOrderPharmacy.Authorizations.entity.User;


@SpringBootApplication
public class AuthorizationsApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(AuthorizationsApplication.class, args);
		UserDao dao=(UserDao) context.getBean("userDao");
		
		
//		 User user=new User(); 
//		 user.setUserName("sujit");
//		 user.setPassword("sujit123@"); 
////		 user.setRoles("ROLE_ADMIN");
////		 user.setActive(true); 
//		 dao.save(user);
//		 
//		 User user1=new User(); 
//		 user1.setUserName("naved");
//		 user1.setPassword("naved123@"); 
//		 user1.setRoles("ROLE_ADMIN");
//		 user1.setActive(true); 
//		 dao.save(user1);
//		 
//		 User user2=new User(); 
//		 user1.setUserName("suvendu");
//		 user1.setPassword("suvendu123@"); 
//		 user1.setRoles("ROLE_ADMIN");
//		 user1.setActive(true); 
//		 dao.save(user2);
//		 
//		 User user3=new User(); 
//		 user1.setUserName("mounica");
//		 user1.setPassword("mounica123@"); 
//		 user1.setRoles("ROLE_ADMIN");
//		 user1.setActive(true); 
//		 dao.save(user3);
		 
	}

}
