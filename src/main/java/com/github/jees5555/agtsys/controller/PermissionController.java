package com.github.jees5555.agtsys.controller;

import static com.github.jees5555.agtsys.constants.WebContants.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.jees5555.agtsys.domain.EasyUINode;
import com.github.jees5555.agtsys.domain.Function;
import com.github.jees5555.agtsys.domain.FunctionEasyUITreeGrid;
import com.github.jees5555.agtsys.domain.Permission;
import com.github.jees5555.agtsys.domain.Role;
import com.github.jees5555.agtsys.domain.User;
import com.github.jees5555.agtsys.service.FunctionService;
import com.github.jees5555.agtsys.service.PermissionService;
import com.github.jees5555.agtsys.service.RoleService;

@Controller
@RequestMapping("permission")
public class PermissionController extends BaseController {
	@Autowired
	private PermissionService ps;
	@Autowired
	private FunctionService fs;
	@Autowired
	private RoleService rs;

	// 返回权限管理页面
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
		Role role = new Role();
		role.setId(roleid);
		// 获取所有功能
		List<Function> allFunctions = fs.getAllFuntions();
		// 获取角色功能
		List<Function> functions = fs.getFuntionsByRole(role);
		for (Function function : functions) {
			for (int i = 0; i < allFunctions.size(); i++) {
				if (function.getId() == allFunctions.get(i).getId()) {
					allFunctions.get(i).setChecked(true);
				}
			}
		}
		List<FunctionEasyUITreeGrid> treeGrids = new ArrayList<FunctionEasyUITreeGrid>();
		functionToEasyTreeGrid(allFunctions, treeGrids, 0);
		return treeGrids;
	}

	// 修改权限
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String functionUpdate(String flist, Permission permission, HttpSession session) throws Exception {
		User user = this.getSessionUser(session);
		permission.setCreatedby(user.getUsercode());
		permission.setCreationtime(new Date());
		permission.setIsstart(1);
		if (permission.getRoleid() == null) {
			throw new Exception("roid id is null when save permission");
		}
		if (ps.TXoperatePermission(permission, flist) >= 1) {
			this.addLogs(user, "用户修改角色ID：" + permission.getRoleid() + " 的对应的功能ID为：" + flist);
			return OPERATE_SUCCESS;
		} else {
			return OPERATE_FAILURE;
		}
	}

	// 递归将javaBean改成TreeGird要求的对象
	private void functionToEasyTreeGrid(List<Function> functions, List<FunctionEasyUITreeGrid> treeGrids,
			int parentId) {
		if (functions != null) {
			for (Function function : functions) {
				FunctionEasyUITreeGrid treeGrid = new FunctionEasyUITreeGrid();
				if (function.getParentid() == parentId) {
					treeGrid = function.toFunctionEasyUITreeGrid();
					functionToEasyTreeGrid(functions, treeGrid.getChildren(), function.getId().intValue());
					treeGrids.add(treeGrid);
				}
			}
		}
	}

}
