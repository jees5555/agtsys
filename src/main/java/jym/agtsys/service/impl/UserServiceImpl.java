package jym.agtsys.service.impl;

import java.util.Date;

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
		user = um.selectUserByUser(user);
		if(user!=null){
			User u =new User();
			u.setId(user.getId());
			u.setLastlogintime(new Date());
			um.updateUser(u);
		}
		return user;
	}

	@Override
	public User checkOldPassword(User user) {
		return um.selectUserByUser(user);
	}

	@Override
	public int updateUser(User user) {
		return um.updateUser(user);
	}

}
