package jym.agtsys.service;

import java.util.List;

import jym.agtsys.domain.Role;

public interface RoleService {

	List<Role> selectAllRoles () throws Exception;
	
	Role checkRoleExist (Role role) throws Exception;
	
	int addRole (Role role) throws Exception;
}
