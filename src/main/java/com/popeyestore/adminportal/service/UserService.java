package com.popeyestore.adminportal.service;

import java.util.Set;

import com.popeyestore.adminportal.domain.User;
import com.popeyestore.adminportal.domain.security.UserRole;



public interface UserService {
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
}
