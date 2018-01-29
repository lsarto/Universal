package com.popeyestore.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.popeyestore.adminportal.domain.security.Role;


public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
