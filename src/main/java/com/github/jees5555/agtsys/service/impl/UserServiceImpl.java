package com.github.jees5555.agtsys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jees5555.agtsys.dao.AccountMapper;
import com.github.jees5555.agtsys.dao.UserMapper;
import com.github.jees5555.agtsys.domain.Account;
import com.github.jees5555.agtsys.domain.User;
import com.github.jees5555.agtsys.service.UserService;
import com.github.jees5555.agtsys.util.MySqlPageTool;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper um;
	@Autowired
	private AccountMapper am;

	@Override
	public User login(User user) throws Exception {
		user = um.selectUserByUser(user);
		if (user != null) {
			User u = new User();
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
	public int TXaddUser(User user) throws Exception {
		um.insertUser(user);
		Account account = new Account();
		account.setUserid(user.getId());
		return am.insertAccount(account);
	}

	@Override
	public int TXdeleteUser(User user) throws Exception {
		Account account = new Account();
		account.setUserid(user.getId());
		am.deleteAccount(account);
		return um.deleteUser(user);
	}

}
