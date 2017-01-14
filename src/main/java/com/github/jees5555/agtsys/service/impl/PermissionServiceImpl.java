package com.github.jees5555.agtsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jees5555.agtsys.dao.PermissionMapper;
import com.github.jees5555.agtsys.domain.Permission;
import com.github.jees5555.agtsys.domain.Role;
import com.github.jees5555.agtsys.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionMapper pm;

	@Override
	public int TXoperatePermission(Permission permission, String flist) throws Exception {
		// 先删除旧权限，再新增新的权限
		pm.deletePermission(permission);
		int count = 0;
		if (flist != null && !flist.trim().equals("")) {
			String[] ids = flist.split(",");
			for (int i = 0; i < ids.length; i++) {
				permission.setFunctionid(Long.valueOf(ids[i]));
				pm.insertPermission(permission);
				count++;
			}
		}
		return count;
	}

	@Override
	public List<Permission> getPermissionByRole(Role role) throws Exception {
		return pm.selectPermissionByRole(role);
	}

}
