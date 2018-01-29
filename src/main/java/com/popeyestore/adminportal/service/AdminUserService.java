package com.popeyestore.adminportal.service;

import java.util.Set;

import com.popeyestore.domain.User;
import com.popeyestore.domain.security.UserRole;



public interface AdminUserService {
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
}
