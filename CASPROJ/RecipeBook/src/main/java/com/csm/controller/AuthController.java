package com.csm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csm.config.JwtProvider;
import com.csm.model.User;
import com.csm.repository.UserRepository;
import com.csm.request.LoginRequest;
import com.csm.response.AuthResponse;
import com.csm.service.CustomerUserDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {
 @Autowired
	private UserRepository userREpo;
 @Autowired
	private CustomerUserDetailsService customerUserDetails;
 @Autowired
	private JwtProvider jwtProvider;
 @Autowired
	private PasswordEncoder passwordEncoder;

 @PostMapping("/signup")
 public AuthResponse createUser(@RequestBody User user) throws Exception{
	 
	 String email = user.getEmail();
	 String password =user.getPassword();
	 String fullname =user.getFullName();
	 
	 User isExistEmail = userREpo.findByEmail(email);
	 if (isExistEmail!=null) {
	 throw new Exception("Email is already in use in another account");
	 }
	  User createdUser = new User();
	  createdUser.setEmail(email);
	 createdUser.setPassword(passwordEncoder.encode(password));
	 createdUser.setFullName(fullname);
	 
	 //encrypting data
	 User savedUser = userREpo.save(createdUser);
	 
	 Authentication auth = new UsernamePasswordAuthenticationToken(email, password);
	 SecurityContextHolder.getContext().setAuthentication(auth);
	 String token = jwtProvider.generateToken(auth);
	 
	 AuthResponse res = new AuthResponse();
	 res.setJwt(token);
	 res.setMessage("signup Success");
	 return res;
 }

 @PostMapping("/signin")
 public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
	 String username = loginRequest.getEmail();
	 String password = loginRequest.getPassword();
	 //user authentication
	 Authentication auth = authenticate(username,password);
	 SecurityContextHolder.getContext().setAuthentication(auth);
     String token = jwtProvider.generateToken(auth);

	 AuthResponse res = new AuthResponse();
	 res.setJwt(token);
	 res.setMessage("signin Success");
	 return res;
 }

private Authentication authenticate(String username, String password) {
	UserDetails userDetails =customerUserDetails.loadUserByUsername(username);
	
	if (userDetails==null) {
		throw new BadCredentialsException("User not found");
	}
	if (!passwordEncoder.matches(password ,userDetails.getPassword())) {
		throw new BadCredentialsException("Invalid Password !");
	}
	return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
}
	
}
