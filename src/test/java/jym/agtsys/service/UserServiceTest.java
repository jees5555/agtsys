package jym.agtsys.service;

import org.junit.Before;
import org.junit.Test;

import jym.agtsys.dao.UserMapper;
import jym.agtsys.domain.User;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;

public class UserServiceTest {
    @Tested
	UserService us;
	@Injectable
    UserMapper um;
    
	@Before
	public void setUp() throws Exception {
		
	}
	/*@Test
	public void testLogin() {
		User u = new User();
		u.setUsercode("admin");
		u.setUserpassword("123456");
		us.login(u);
		new Verifications() {
			{
				um.selectUserByUser(withInstanceOf(User.class));
				times=1;
			}
			
		};
		
	}*/

}
