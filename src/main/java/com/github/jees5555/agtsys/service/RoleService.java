package com.github.jees5555.agtsys.service;

import java.util.List;

import com.github.jees5555.agtsys.domain.Role;

public interface RoleService {

	List<Role> getRoles() throws Exception;

	Role checkRoleExist(Role role) throws Exception;

	int addRole(Role role) throws Exception;

	int updateRole(Role role) throws Exception;

	int deleteRole(Role role) throws Exception;
}
