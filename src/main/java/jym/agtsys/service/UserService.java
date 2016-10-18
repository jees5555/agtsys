package jym.agtsys.service;

import jym.agtsys.domain.User;

public interface UserService {
	
User login(User user)  throws Exception;

User checkOldPassword(User user)  throws Exception;

int updateUser(User user)  throws Exception;

}
