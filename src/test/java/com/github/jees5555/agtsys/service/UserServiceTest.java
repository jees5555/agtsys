package com.github.jees5555.agtsys.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.jees5555.agtsys.dao.UserMapper;
import com.github.jees5555.agtsys.domain.User;
import com.github.jees5555.agtsys.service.UserService;
import com.github.jees5555.agtsys.service.impl.UserServiceImpl;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class UserServiceTest {
	@Tested
	UserService us;
	@Injectable
	UserMapper um;

	@Before
	public void setUp() throws Exception {
		us = new UserServiceImpl();
	}

	@Test
	public void testLogin() throws Exception {
		// 录制
		new Expectations() {
			{
				um.selectUserByUser(withInstanceOf(User.class));
				times = 1;
			}
		};
		// 回放
		us.login(new User());
		// 验证
		new Verifications() {
			{
				um.selectUserByUser(withInstanceOf(User.class));
				times = 1;
			}

		};

	}

}
