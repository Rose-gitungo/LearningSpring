package com.csm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csm.model.User;
import com.csm.repository.UserRepository;
import com.csm.service.UserService;

//@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/users")

public class UserController {
	@Autowired
	private UserService userService;

//	@PostMapping
//	public User createUser(@RequestBody User user) throws Exception {
//		User isExist=userService.findByEmail(user.getEmail());
//		if (isExist!=null) {
//			throw new Exception("Email is already in use!");
//		}
//		User savedUser= userRepo.save(user);
//		return savedUser;
//	}
	
	@GetMapping("/profile")
	public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwt(jwt);
		return user;
	}
	
	
	
}
