package jym.agtsys.service;

import jym.agtsys.domain.User;

public interface UserService {
	
User login(User user);

User checkOldPassword(User user);

int updateUser(User user);

}
