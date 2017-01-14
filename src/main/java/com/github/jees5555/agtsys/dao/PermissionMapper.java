package com.github.jees5555.agtsys.dao;

import java.util.List;

import com.github.jees5555.agtsys.domain.Permission;
import com.github.jees5555.agtsys.domain.Role;

public interface PermissionMapper {
	int insertPermission(Permission permission) throws Exception;

	int deletePermission(Permission permission) throws Exception;

	List<Permission> selectPermissionByRole(Role role) throws Exception;

	int insert(Permission record);

	int updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);
}