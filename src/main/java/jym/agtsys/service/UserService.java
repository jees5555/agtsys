package jym.agtsys.service;

import java.util.List;

import jym.agtsys.domain.User;
import jym.agtsys.util.MySqlPageTool;

public interface UserService {
	
User login(User user)  throws Exception;

User checkOldPassword(User user)  throws Exception;

List <User> getPageUsersByUser(User user,MySqlPageTool pageTool) throws Exception;

int getUsersCount(User user) throws Exception;

int updateUser(User user)  throws Exception;

}
