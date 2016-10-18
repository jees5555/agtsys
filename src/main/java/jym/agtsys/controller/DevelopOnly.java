package jym.agtsys.controller;
import static jym.agtsys.constants.WebContants.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jym.agtsys.domain.User;
import jym.agtsys.service.UserService;

/**
 * This java file is only for developers
 * please delete this file before deploy.
 */

@Controller
public class DevelopOnly {
	@Resource
	private UserService us ;
	
	@RequestMapping("dev")
	public String developersLogin(HttpSession session) throws Exception{
		User user =new User();
		user.setUsercode("admin");
		user.setUserpassword("123456");
		user=us.login(user);
		session.setAttribute(SESSION_LOGIN_KEY, user);
		return "redirect:/main";
		
	}
}
