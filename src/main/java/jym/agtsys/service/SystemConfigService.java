package jym.agtsys.service;

import java.util.List;

import jym.agtsys.domain.SystemConfig;

public interface SystemConfigService {
	SystemConfig getSystemConfigById (SystemConfig systemConfig) throws Exception;

	List<SystemConfig> getSystemConfigsByConfigType(SystemConfig systemConfig) throws Exception;
	
	int addSystemConfig(SystemConfig systemConfig) throws Exception;
	
	int deleteSystemConfig (SystemConfig systemConfig) throws Exception;
	
	int updateSystemConfig (SystemConfig systemConfig) throws Exception;
}
