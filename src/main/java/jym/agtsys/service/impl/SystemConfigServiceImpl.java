package jym.agtsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jym.agtsys.dao.SystemConfigMapper;
import jym.agtsys.domain.SystemConfig;
import jym.agtsys.service.SystemConfigService;

@Service
public class SystemConfigServiceImpl implements SystemConfigService{
	@Autowired
    private SystemConfigMapper scm;
	
	@Override
	public List<SystemConfig> getSystemConfigByConfigType(SystemConfig systemConfig) throws Exception  {
		return scm.selectSystemConfigByConfigType(systemConfig);
	}

	@Override
	public int addSystemConfig(SystemConfig systemConfig) throws Exception {
		Integer max= scm.selectMaxTypeValueByConfigType(systemConfig);
		systemConfig.setConfigtypevalue(++max);
		return scm.insertSystemConfig(systemConfig);
	}

	@Override
	public int deleteSystemConfig(SystemConfig systemConfig) throws Exception {
		return scm.deleteSystemConfig(systemConfig);
	}

	@Override
	public SystemConfig getSystemConfigById(SystemConfig systemConfig) throws Exception {
		return scm.selectSystemConfigById(systemConfig);
	}

	@Override
	public int updateSystemConfig(SystemConfig systemConfig) throws Exception {
		return scm.updateSystemConfig(systemConfig);
	}

}
