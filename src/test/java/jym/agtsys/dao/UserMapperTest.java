package jym.agtsys.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jym.agtsys.dao.UserMapper;
import jym.agtsys.domain.User;
import jym.agtsys.util.MySqlPageTool;

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
	@Test
	public void selectPageUsersByUser() throws Exception{
		User user =new User();
		user.setUsername(null);
		user.setIsstart(1);
		user.setRoleid(1L);
		MySqlPageTool pageTool =new MySqlPageTool(1, 5);
		um.selectPageUsersByUser(user, pageTool);
	}
	
	@Test
	public void selectUsersCount()  throws Exception{
		User user =new User();
		user.setUsername(null);
		user.setIsstart(1);
		user.setRoleid(null);
		int count=um.selectUsersCount(user);
		System.out.println(count);
		
	}

}
