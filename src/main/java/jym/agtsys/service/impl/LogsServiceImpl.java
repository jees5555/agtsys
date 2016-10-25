package jym.agtsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jym.agtsys.dao.LogsMapper;
import jym.agtsys.domain.Logs;
import jym.agtsys.service.LogsService;

@Service
public class LogsServiceImpl implements LogsService {
	@Autowired
	private LogsMapper lm;

	@Override
	public int addLogs(Logs logs) throws Exception {
		return lm.insertLogs(logs);
	}

}
