package tech.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.model.user;
import tech.csm.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	

	@Autowired
	UserRepo userRepo;

	@Override
	public String deleteUserById(String userId) {	
		userRepo.delete(userRepo.findById(userId).get());
		return "redirect:./getform";
	}

	@Override
	public String saveUser(user user) {
	userRepo.save(user);
		return "chapchap!";
	}

	@Override
	public boolean existsByUserId(String userId) {
		return userRepo.existsById(userId);
	}

	@Override
	public user updateUserById(String userId) {
		return userRepo.findById(userId).get();
	}

}
