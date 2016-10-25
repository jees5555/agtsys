package jym.agtsys.dao;

import jym.agtsys.domain.Logs;

public interface LogsMapper {
	int insertLogs(Logs logs) throws Exception;
	
    int deleteByPrimaryKey(Long id);

    int insert(Logs record);

    Logs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Logs record);

    int updateByPrimaryKey(Logs record);
}