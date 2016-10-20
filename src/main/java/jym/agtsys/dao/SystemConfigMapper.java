package jym.agtsys.dao;

import java.util.List;

import jym.agtsys.domain.SystemConfig;

public interface SystemConfigMapper {
	
	List<SystemConfig> selectSystemConfigByConfigType(SystemConfig systemConfig) throws Exception;
	
	int insertSystemConfig (SystemConfig systemConfig) throws Exception;
	
	int selectMaxTypeValueByConfigType (SystemConfig systemConfig) throws Exception;
	
    int deleteSystemConfig (SystemConfig systemConfig) throws Exception;
    

    int insert(SystemConfig record);

    int updateByPrimaryKeySelective(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);
}