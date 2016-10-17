package jym.agtsys.service;

import java.util.List;

import jym.agtsys.domain.Function;
import jym.agtsys.domain.Role;

public interface FunctionService {
	List<Function> selectFuntionsByRole(Role role);
}
