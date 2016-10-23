package jym.agtsys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static jym.agtsys.constants.WebContants.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jym.agtsys.constants.WebContants;
import jym.agtsys.domain.Role;
import jym.agtsys.domain.User;
import jym.agtsys.service.RoleService;
import jym.agtsys.service.UserService;
import jym.agtsys.util.MySqlPageTool;
import jym.agtsys.validate.LoginValidateGroup;

@Controller
@RequestMapping("user")
public class UserController {
	@Resource
	private UserService us ;
	@Resource
	private RoleService rs ;
	
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
	
	//返回用户管理页面
	@RequestMapping("manage")
	public String getUserManage (Model model) throws Exception{
		List<Role> roles =rs.getRoles();
		model.addAttribute("roles", roles);
		return "usermanage";
	}
	//返回用户列表数据
	@RequestMapping("list")
	@ResponseBody
	  public Object getUserList(User user,Integer page,Integer rows) throws Exception{
		Map <String,Object> map =new HashMap<String,Object>();
		map.put("total", us.getUsersCount(user));
		map.put("rows", us.getPageUsersByUser(user, new MySqlPageTool(page, rows)));
	    return map;
	    }
	//返回添加用户页面
    @RequestMapping(value={"add"},method=RequestMethod.GET)
    public String addUser (Model model) throws Exception{
    	List<Role> roles =rs.getRoles();
		model.addAttribute("roles", roles);
    	return "adduser";
    }
    //检查登陆账号是否存在
    @RequestMapping(value={"usercodeCheck"},method=RequestMethod.POST)
    @ResponseBody
    public String usercodeCheck (User user) throws Exception{
    	user=us.checkUserExist(user);
    	if(user==null){
    		return OPERATE_SUCCESS;
    	}else{
    		return OPERATE_FAILURE;
    	}
    }
  //添加用户
    @RequestMapping(value={"add"},method=RequestMethod.POST)
    @ResponseBody
    public String doAddUser (User user,HttpSession session) throws Exception{
    	User userSession =(User)session.getAttribute(SESSION_LOGIN_KEY);
    	user.setCreationtime(new Date());
    	user.setCreatedby(userSession.getUsercode());
    	if(us.addUser(user)==1){
    		return OPERATE_SUCCESS;
    	}else{
    		return OPERATE_FAILURE;
    	}
    }
  //返回修改用户页面
    @RequestMapping(value={"update/{id}"},method=RequestMethod.GET)
    public String updateUser (@PathVariable(value="id")Long id,Model model) throws Exception{
    	User user =new User();
    	user.setId(id);
    	user=us.checkUserExist(user);
    	model.addAttribute("user", user);
    	List<Role> roles =rs.getRoles();
		model.addAttribute("roles", roles);
    	return "updateuser";
    }
    //修改用户
    @RequestMapping(value={"update"},method=RequestMethod.POST)
    @ResponseBody
    public String doUpdateUser (User user) throws Exception{
    	user.setLastupdatetime(new Date());
    	if(us.updateUser(user)==1){
    		return OPERATE_SUCCESS;
    	}else{
    		return OPERATE_FAILURE;
    	}
    }
    
    //删除用户
    @RequestMapping(value={"delete"},method=RequestMethod.POST)
    @ResponseBody
    public String doDeleteUser (User user) throws Exception{
    	if(us.deleteUser(user)==1){
    		return OPERATE_SUCCESS;
    	}else{
    		return OPERATE_FAILURE;
    	}
    }
}
