package com.github.jees5555.agtsys.service;

import java.util.List;

import com.github.jees5555.agtsys.domain.User;
import com.github.jees5555.agtsys.util.MySqlPageTool;

public interface UserService {

	User login(User user) throws Exception;

	User checkOldPassword(User user) throws Exception;

	List<User> getPageUsersByUser(User user, MySqlPageTool pageTool) throws Exception;

	int getUsersCount(User user) throws Exception;

	int updateUser(User user) throws Exception;

	User checkUserExist(User user) throws Exception;

	int TXaddUser(User user) throws Exception;

	int TXdeleteUser(User user) throws Exception;

}
