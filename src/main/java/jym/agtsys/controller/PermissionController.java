package jym.agtsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jym.agtsys.domain.Function;
import jym.agtsys.domain.Role;
import jym.agtsys.service.FunctionService;
import jym.agtsys.service.PermissionService;
import jym.agtsys.service.RoleService;

@Controller
@RequestMapping("permission")
public class PermissionController {
	@Autowired
	private PermissionService ps;
	@Autowired
	private FunctionService fs;
	@Autowired
	private RoleService rs;

	//返回权限管理页面
	@RequestMapping("manage")
	public String manage(Model model) throws Exception {
		List<Role> roles = rs.getRoles();
		model.addAttribute("roles", roles);
		return "permissionmanage";
	}
	// 根据角色返回所有的功能列表，并且选中当前角色具有的功能
	@RequestMapping(value = "list/{roleid}", method = RequestMethod.POST)
	@ResponseBody 
	public Object list(@PathVariable(value = "roleid") Long roleid) throws Exception {
		Role role =new Role();
		role.setId(roleid);
		List<Function> functions=fs.getFuntionsByRole(role);
		return functions;
	}


}
