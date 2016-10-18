package jym.agtsys.dao;

import java.util.List;

import jym.agtsys.domain.Role;

public interface RoleMapper {
	
	List<Role> selectAllRoles () throws Exception ;
	
	Role selectRoleByRole (Role role) throws Exception;
	
	int addRole(Role role) throws Exception;
	
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}