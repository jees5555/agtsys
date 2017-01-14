package com.github.jees5555.agtsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jees5555.agtsys.dao.LogsMapper;
import com.github.jees5555.agtsys.domain.Logs;
import com.github.jees5555.agtsys.service.LogsService;

@Service
public class LogsServiceImpl implements LogsService {
	@Autowired
	private LogsMapper lm;

	@Override
	public int addLogs(Logs logs) throws Exception {
		return lm.insertLogs(logs);
	}

}
