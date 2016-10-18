package jym.agtsys.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jym.agtsys.dao.UserMapper;
import jym.agtsys.domain.User;

public class UserMapperTest {
	private ApplicationContext ac;
	private UserMapper um;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		um=(UserMapper)ac.getBean("userMapper");
	}
	
	@Test
	public void selectUserByUser() throws Exception{
		User user =new User();
		user.setUsercode("admin");
		user.setUserpassword("123456");
		User u=um.selectUserByUser(user);
		System.out.println(u.getId());
		
	}

}
