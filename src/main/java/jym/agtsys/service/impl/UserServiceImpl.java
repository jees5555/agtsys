package jym.agtsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jym.agtsys.dao.UserMapper;
import jym.agtsys.domain.User;
import jym.agtsys.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper um;
	
	@Override
	public User login(User user) {
		return um.selectUserByUser(user);
	}

}
