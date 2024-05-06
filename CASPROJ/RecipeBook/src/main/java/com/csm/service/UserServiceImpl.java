package com.csm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csm.config.JwtProvider;
import com.csm.model.User;
import com.csm.repository.UserRepository;

@Service
public class UserServiceImpl  implements UserService{
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	JwtProvider jwtProvider;

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User findUserById(Integer id) throws Exception {
		 Optional<User> u=userRepo.findById(id);
		 if (u.isPresent()) {
			return u.get();
		}
		throw new Exception() ;
	}

	@Override
	public User findUserByJwt(String jwt) throws Exception {
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		if (email==null) {
			throw new Exception("Provider valid jwt token");
		}
		User user =userRepo.findByEmail(email);
		if (user==null) {
			throw new Exception("User not found with email" +email);
		}
		return user;
	}
}
