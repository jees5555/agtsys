package jym.agtsys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jym.agtsys.dao.AccountMapper;
import jym.agtsys.dao.UserMapper;
import jym.agtsys.domain.Account;
import jym.agtsys.domain.User;
import jym.agtsys.service.UserService;
import jym.agtsys.util.MySqlPageTool;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper um;
    @Autowired
    private AccountMapper am;
	
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
	public int TXaddUser(User user) throws Exception {
		um.insertUser(user);
		Account account =new Account();
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
