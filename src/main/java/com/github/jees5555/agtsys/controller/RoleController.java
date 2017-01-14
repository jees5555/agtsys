package com.github.jees5555.agtsys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.jees5555.agtsys.domain.Role;
import com.github.jees5555.agtsys.domain.User;
import com.github.jees5555.agtsys.service.RoleService;

import static com.github.jees5555.agtsys.constants.WebContants.*;

import java.util.Date;

@Controller
@RequestMapping("role")
public class RoleController {
	@Resource
	private RoleService rs;

	// 返回角色列表数据
	@RequestMapping("list")
	@ResponseBody
	public Object getRoleList() throws Exception {
		return rs.getRoles();
	}

	// 返回角色列表页面
	@RequestMapping("manage")
	public String getRoleManage() {
		return "rolemanage";
	}

	// 返回添加角色页面
	@RequestMapping(value = { "add" }, method = RequestMethod.GET)
	public String addRole() {
		return "addrole";
	}

	// 检查角色名称是否存在
	@RequestMapping(value = { "rolenameCheck" }, method = RequestMethod.POST)
	@ResponseBody
	public String rolenameCheck(Role role) throws Exception {
		role = rs.checkRoleExist(role);
		if (role == null) {
			return OPERATE_SUCCESS;
		} else {
			return OPERATE_FAILURE;
		}
	}

	// 添加角色
	@RequestMapping(value = { "add" }, method = RequestMethod.POST)
	@ResponseBody
	public String doAddRole(Role role, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(SESSION_LOGIN_KEY);
		role.setCreationtime(new Date());
		role.setCreatedby(user.getUsercode());
		if (rs.addRole(role) == 1) {
			return OPERATE_SUCCESS;
		} else {
			return OPERATE_FAILURE;
		}
	}

	// 返回角色修改页面
	@RequestMapping(value = { "update/{id}" }, method = RequestMethod.GET)
	public String updateRole(@PathVariable(value = "id") Long id, Model model) throws Exception {
		Role role = new Role();
		role.setId(id);
		role = rs.checkRoleExist(role);
		model.addAttribute("role", role);
		return "updaterole";
	}

	// 修改角色
	@RequestMapping(value = { "update" }, method = RequestMethod.POST)
	@ResponseBody
	public String doUpdateRole(Role role) throws Exception {
		role.setLastupdatetime(new Date());
		if (rs.updateRole(role) == 1) {
			return OPERATE_SUCCESS;
		} else {
			return OPERATE_FAILURE;
		}
	}

	// 删除角色
	@RequestMapping(value = { "delete" }, method = RequestMethod.POST)
	@ResponseBody
	public String doDeleteRole(Role role) throws Exception {
		if (rs.deleteRole(role) == 1) {
			return OPERATE_SUCCESS;
		} else {
			return OPERATE_FAILURE;
		}
	}
}