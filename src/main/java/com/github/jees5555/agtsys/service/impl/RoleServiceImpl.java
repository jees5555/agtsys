package com.github.jees5555.agtsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jees5555.agtsys.dao.RoleMapper;
import com.github.jees5555.agtsys.domain.Role;
import com.github.jees5555.agtsys.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper rm;

	@Override
	public List<Role> getRoles() throws Exception {
		return rm.selectAllRoles();
	}

	@Override
	public Role checkRoleExist(Role role) throws Exception {
		return rm.selectRoleByRole(role);
	}

	@Override
	public int addRole(Role role) throws Exception {
		return rm.insertRole(role);
	}

	@Override
	public int updateRole(Role role) throws Exception {
		return rm.updateRole(role);
	}

	@Override
	public int deleteRole(Role role) throws Exception {
		return rm.deleteRole(role);
	}

}
