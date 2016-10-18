package jym.agtsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jym.agtsys.dao.RoleMapper;
import jym.agtsys.domain.Role;
import jym.agtsys.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
    private RoleMapper rm;
	
	@Override
	public List<Role> selectAllRoles() throws Exception {
		return rm.selectAllRoles();
	}

	@Override
	public Role checkRoleExist(Role role) throws Exception {
		return rm.selectRoleByRole(role);
	}

	@Override
	public int addRole(Role role) throws Exception {
		return rm.addRole(role);
	}

}
