package com.github.jees5555.agtsys.service;

import java.util.List;

import com.github.jees5555.agtsys.domain.Permission;
import com.github.jees5555.agtsys.domain.Role;

public interface PermissionService {
	int TXoperatePermission(Permission permission, String flist) throws Exception;

	List<Permission> getPermissionByRole(Role role) throws Exception;
}
