package jym.agtsys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jym.agtsys.constants.WebContants;
import jym.agtsys.domain.User;
import jym.agtsys.service.UserService;
import jym.agtsys.validate.LoginValidateGroup;

@Controller
public class UserController {
	@Resource
	private UserService us ;
	
	@RequestMapping(value={"login","/"},method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	@RequestMapping(value={"logout"},method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute(WebContants.SESSION_LOGIN_KEY);
		return "redirect:/login";
	}
	
	@RequestMapping(value={"user/login"},method=RequestMethod.POST)
	public String doLogin(String captcha,@Validated(value={LoginValidateGroup.class}) User user,BindingResult result,HttpServletRequest request){
		String sessionCaptcha =(String)request.getSession().getAttribute(WebContants.SESSION_CAPTCHA_KEY);
		if(!captcha.equalsIgnoreCase(sessionCaptcha)){
			request.setAttribute("captchaError", WebContants.CAPTCHA_ERROR_MESSAGE);
			return "login";
		}
		if(result.hasErrors()){
			List<ObjectError> errors=result.getAllErrors();
			request.setAttribute("errors", errors);
			return "login";
		}
		user=us.login(user);
		if(user==null){
			request.setAttribute("loginError", WebContants.LOGIN_ERROR_MESSAGE);
			return "login";
		}else{
			request.getSession().setAttribute(WebContants.SESSION_LOGIN_KEY, user);
			return "redirect:/main";
		}
	}
	
	@RequestMapping("main")
	public String main(){
		return "main";
	}
	
	@RequestMapping(value={"user/updatePassword"},method=RequestMethod.GET)
	public String updatePassword(){
		return "updatePassword";
	}
	
}
