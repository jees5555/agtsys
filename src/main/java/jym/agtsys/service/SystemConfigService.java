package jym.agtsys.service;

import java.util.List;

import jym.agtsys.domain.SystemConfig;

public interface SystemConfigService {

	List<SystemConfig> getSystemConfigByConfigType(SystemConfig systemConfig) throws Exception;
	
	int addSystemConfig(SystemConfig systemConfig) throws Exception;
	
	int deleteSystemConfig (SystemConfig systemConfig) throws Exception;
}
