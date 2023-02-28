package com.cts.MailOrderPharmacy.Authorizations.Controller;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cts.MailOrderPharmacy.Authorizations.entity.TokenModel;
import com.cts.MailOrderPharmacy.Authorizations.entity.User;
import com.cts.MailOrderPharmacy.Authorizations.service.RegisterService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private RegisterService service;

	@GetMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestParam("username") String userName,
			@RequestParam("password") String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID CREDENTIALS", e);
		}
		String token = JWT.create().withSubject(userName)
				.withExpiresAt(new Date(System.currentTimeMillis() + 60000 * 30))
				.sign(Algorithm.HMAC512("mailorderpharmacy"));
//				.sign(Algorithm.HMAC512("bikerentalportal"));
		TokenModel tokenModel = new TokenModel();
		tokenModel.setToken(token);
		return ResponseEntity.ok(tokenModel);

	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		String message =  service.doRegister(user);
		return ResponseEntity.ok(message);
	}

}
