package jym.agtsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jym.agtsys.dao.PermissionMapper;
import jym.agtsys.domain.Permission;
import jym.agtsys.domain.Role;
import jym.agtsys.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionMapper pm;

	@Override
	public int TXoperatePermission(Permission permission) throws Exception {
		return 0;
	}

	@Override
	public List<Permission> getPermissionByRole(Role role) throws Exception {
		return pm.selectPermissionByRole(role);
	}

}
