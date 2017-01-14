package com.github.jees5555.agtsys.dao;

import java.util.List;

import com.github.jees5555.agtsys.domain.Function;
import com.github.jees5555.agtsys.domain.Role;

public interface FunctionMapper {
	List<Function> selectFuntionsByRole(Role role) throws Exception;

	List<Function> selectFuntions() throws Exception;

	int deleteByPrimaryKey(Long id);

	int insert(Function record);

	int insertSelective(Function record);

	Function selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Function record);

	int updateByPrimaryKey(Function record);

}