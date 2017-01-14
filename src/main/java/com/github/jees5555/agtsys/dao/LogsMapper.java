package com.github.jees5555.agtsys.dao;

import com.github.jees5555.agtsys.domain.Logs;

public interface LogsMapper {
	int insertLogs(Logs logs) throws Exception;

	int deleteByPrimaryKey(Long id);

	int insert(Logs record);

	Logs selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Logs record);

	int updateByPrimaryKey(Logs record);
}