package tech.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.model.user;
import tech.csm.repo.UserRepo;

public interface UserService {

	String deleteUserById(String userId);

	String saveUser(user user);

	boolean existsByUserId(String userId);

	user updateUserById(String userId);

	
	
}
