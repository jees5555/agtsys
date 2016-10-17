package jym.agtsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jym.agtsys.dao.FunctionMapper;
import jym.agtsys.domain.Function;
import jym.agtsys.domain.Role;
import jym.agtsys.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService{
	@Autowired
	private FunctionMapper fm;

	@Override
	public List<Function> selectFuntionsByRole(Role role) {
		return fm.selectFuntionsByRole(role);
	}

}
