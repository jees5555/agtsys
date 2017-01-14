package com.github.jees5555.agtsys.dao;

import java.util.List;

import com.github.jees5555.agtsys.domain.Role;

public interface RoleMapper {

	List<Role> selectAllRoles() throws Exception;

	Role selectRoleByRole(Role role) throws Exception;

	int insertRole(Role role) throws Exception;

	int updateRole(Role role) throws Exception;

	int deleteRole(Role role) throws Exception;

	int insert(Role record);

	Role selectByPrimaryKey(Long id);

	int updateByPrimaryKey(Role record);
}