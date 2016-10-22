package jym.agtsys.dao;

import java.util.List;

import jym.agtsys.domain.SystemConfig;

public interface SystemConfigMapper {
	SystemConfig selectSystemConfigById (SystemConfig systemConfig) throws Exception;
	
	List<SystemConfig> selectSystemConfigByConfigType(SystemConfig systemConfig) throws Exception;
	
	int insertSystemConfig (SystemConfig systemConfig) throws Exception;
	
	int selectMaxTypeValueByConfigType (SystemConfig systemConfig) throws Exception;
	
    int deleteSystemConfig (SystemConfig systemConfig) throws Exception;
    
    int updateSystemConfig(SystemConfig systemConfig) throws Exception;

    int insert(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);
}