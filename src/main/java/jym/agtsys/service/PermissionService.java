package jym.agtsys.service;

import java.util.List;

import jym.agtsys.domain.Permission;
import jym.agtsys.domain.Role;

public interface PermissionService {
   int TXoperatePermission(Permission permission) throws Exception;
   
   List<Permission> getPermissionByRole(Role role)throws Exception;
}
