package jym.agtsys.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static jym.agtsys.constants.WebContants.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jym.agtsys.constants.WebContants;
import jym.agtsys.domain.User;
import jym.agtsys.service.UserService;
import jym.agtsys.validate.LoginValidateGroup;

@Controller
@RequestMapping("user")
public class UserController {
	@Resource
	private UserService us ;
	
	//执行登出
	@RequestMapping(value={"logout"},method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute(SESSION_LOGIN_KEY);
		return "redirect:/";
	}
	//执行登录
	@RequestMapping(value={"login"},method=RequestMethod.POST)
	public String doLogin(String captcha,@Validated(value={LoginValidateGroup.class}) User user,BindingResult result,HttpServletRequest request) throws Exception{
		String sessionCaptcha =(String)request.getSession().getAttribute(WebContants.SESSION_CAPTCHA_KEY);
		if(!captcha.equalsIgnoreCase(sessionCaptcha)){
			request.setAttribute("captchaError", CAPTCHA_ERROR_MESSAGE);
			return "login";
		}
		if(result.hasErrors()){
			List<ObjectError> errors=result.getAllErrors();
			request.setAttribute("errors", errors);
			return "login";
		}
		user=us.login(user);
		if(user==null){
			request.setAttribute("loginError", LOGIN_ERROR_MESSAGE);
			return "login";
		}else{
			request.getSession().setAttribute(SESSION_LOGIN_KEY, user);
			return "redirect:/main";
		}
	}
		
	//进入修改密码页面
	@RequestMapping(value={"updatePassword"},method=RequestMethod.GET)
	public String updatePassword(){
		return "updatePassword";
	}
	
	//验证旧密码是否正确
	@RequestMapping(value={"passwordCheck"},method=RequestMethod.POST)
	@ResponseBody
	public String passwordCheck(String oldpassword,HttpSession session) throws Exception{
		User user =(User)session.getAttribute(SESSION_LOGIN_KEY);
		user.setUserpassword(oldpassword);
		user=us.checkOldPassword(user);
		if(user!=null){
			return OPERATE_SUCCESS;	
		}else{
			return OPERATE_FAILURE;
		}
	}
	
	//修改密码
	@RequestMapping(value={"doUpdatePassword"},method=RequestMethod.POST)
	@ResponseBody
	public String doUpdatePassword(String newpassword,HttpSession session) throws Exception{
		User user =(User)session.getAttribute(SESSION_LOGIN_KEY);
		User u =new User();
		u.setLastupdatetime(new Date());
		u.setId(user.getId());
		u.setUserpassword(newpassword);
		if(us.updateUser(u)==1){
			return OPERATE_SUCCESS;
		}else{
			return OPERATE_FAILURE;
		}
	}
}
