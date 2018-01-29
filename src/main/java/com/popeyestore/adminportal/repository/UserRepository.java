package com.popeyestore.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.popeyestore.adminportal.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
