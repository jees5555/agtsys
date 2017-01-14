package com.github.jees5555.agtsys.service;

import java.util.List;

import com.github.jees5555.agtsys.domain.SystemConfig;

public interface SystemConfigService {
	SystemConfig getSystemConfigById(SystemConfig systemConfig) throws Exception;

	List<SystemConfig> getSystemConfigsByConfigType(SystemConfig systemConfig) throws Exception;

	int addSystemConfig(SystemConfig systemConfig) throws Exception;

	int deleteSystemConfig(SystemConfig systemConfig) throws Exception;

	int updateSystemConfig(SystemConfig systemConfig) throws Exception;
}
