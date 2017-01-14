package com.github.jees5555.agtsys.controller;

import static com.github.jees5555.agtsys.constants.WebContants.*;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.github.jees5555.agtsys.domain.Logs;
import com.github.jees5555.agtsys.domain.User;
import com.github.jees5555.agtsys.service.LogsService;

@Controller
public class BaseController {
	@Autowired
	protected LogsService ls;

	protected int addLogs(User user, String operateInfo) throws Exception {
		Logs logs = new Logs();
		logs.setUserid(user.getId());
		logs.setUsername(user.getUsercode());
		logs.setOperateinfo(operateInfo);
		logs.setOperatedatetime(new Date());
		return ls.addLogs(logs);
	}

	protected User getSessionUser(HttpSession session) {
		return (User) session.getAttribute(SESSION_LOGIN_KEY);
	}
}
