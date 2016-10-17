package jym.agtsys.dao;

import java.util.List;

import jym.agtsys.domain.Function;
import jym.agtsys.domain.Role;

public interface FunctionMapper {
	List<Function> selectFuntionsByRole(Role role);
	
    int deleteByPrimaryKey(Long id);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);
}