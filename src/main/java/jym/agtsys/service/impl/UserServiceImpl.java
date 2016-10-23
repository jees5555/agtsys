package jym.agtsys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jym.agtsys.dao.UserMapper;
import jym.agtsys.domain.User;
import jym.agtsys.service.UserService;
import jym.agtsys.util.MySqlPageTool;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper um;
	
	@Override
	public User login(User user) throws Exception {
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
	public User checkOldPassword(User user) throws Exception {
		return um.selectUserByUser(user);
	}

	@Override
	public int updateUser(User user) throws Exception {
		return um.updateUser(user);
	}

	@Override
	public List<User> getPageUsersByUser(User user, MySqlPageTool pageTool) throws Exception {
		return um.selectPageUsersByUser(user, pageTool);
	}

	@Override
	public int getUsersCount(User user) throws Exception {
		return um.selectUsersCount(user);
	}

	@Override
	public User checkUserExist(User user) throws Exception {
		return um.selectUserByUser(user);
	}

	@Override
	public int addUser(User user) throws Exception {
		return um.insertUser(user);
	}

	@Override
	public int deleteUser(User user) throws Exception {
		return um.deleteUser(user);
	}

}
