package com.github.jees5555.agtsys.service;

import java.util.List;

import com.github.jees5555.agtsys.domain.Function;
import com.github.jees5555.agtsys.domain.Role;

public interface FunctionService {
	List<Function> getFuntionsByRole(Role role) throws Exception;

	List<Function> getAllFuntions() throws Exception;
}
