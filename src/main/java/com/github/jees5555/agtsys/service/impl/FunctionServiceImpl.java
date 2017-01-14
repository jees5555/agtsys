package com.github.jees5555.agtsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jees5555.agtsys.dao.FunctionMapper;
import com.github.jees5555.agtsys.domain.Function;
import com.github.jees5555.agtsys.domain.Role;
import com.github.jees5555.agtsys.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService {
	@Autowired
	private FunctionMapper fm;

	@Override
	public List<Function> getFuntionsByRole(Role role) throws Exception {
		return fm.selectFuntionsByRole(role);
	}

	@Override
	public List<Function> getAllFuntions() throws Exception {
		return fm.selectFuntions();
	}

}
