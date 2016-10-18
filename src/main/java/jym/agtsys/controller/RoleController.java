package jym.agtsys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static jym.agtsys.constants.WebContants.*;

import java.util.Date;

import jym.agtsys.domain.Role;
import jym.agtsys.domain.User;
import jym.agtsys.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleController {
    @Resource
	private RoleService rs;
    
    //返回角色列表数据
    @RequestMapping("list")
    @ResponseBody
    public Object getRoleList() throws Exception{
    	return rs.selectAllRoles();
    }
    //返回角色列表页面
    @RequestMapping("manage")
    public String getRoleManage(){
    	return "rolemanage";
    }
    //返回添加角色页面
    @RequestMapping(value={"addRole"},method=RequestMethod.GET)
    public String addRole (){
    	return "addrole";
    }
    //检查角色名称是否存在
    @RequestMapping(value={"rolenameCheck"},method=RequestMethod.POST)
    @ResponseBody
    public String rolenameCheck(Role role) throws Exception{
    	role=rs.checkRoleExist(role);
    	if(role==null){
    		return OPERATE_SUCCESS;
    	}else{
    	    return OPERATE_FAILURE;
    	}
    }
    //添加角色
    @RequestMapping(value={"doAddRole"},method=RequestMethod.POST)
    @ResponseBody
    public String doAddRole(Role role,HttpSession session) throws Exception{
    	User user =(User)session.getAttribute(SESSION_LOGIN_KEY);
        role.setCreationtime(new Date());
    	role.setCreatedby(user.getUsercode());
    	if(rs.addRole(role)==1){
    		return OPERATE_SUCCESS;
    	}else{
    	    return OPERATE_FAILURE;
    	}
    }
}
    
