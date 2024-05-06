package com.csm.service;

import java.util.List;

import com.csm.model.User;

public interface UserService {

	User findByEmail(String email);
	
	User findUserById(Integer id) throws Exception;
	
	User findUserByJwt(String jwt) throws Exception;

}
