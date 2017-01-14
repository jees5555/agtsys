package com.github.jees5555.agtsys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static com.github.jees5555.agtsys.constants.WebContants.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.jees5555.agtsys.domain.EasyUINode;
import com.github.jees5555.agtsys.domain.Function;
import com.github.jees5555.agtsys.domain.Role;
import com.github.jees5555.agtsys.domain.User;
import com.github.jees5555.agtsys.service.FunctionService;

@Controller
@RequestMapping("main")
public class MainController {

	@Resource
	private FunctionService fs;

	// 进入主页面
	@RequestMapping("")
	public String main() {
		return "main";
	}

	// 加载树形菜单
	@RequestMapping("tree")
	@ResponseBody
	public Object getTree(HttpSession session) throws Exception {
		User user = (User) session.getAttribute(SESSION_LOGIN_KEY);
		Role role = new Role();
		role.setId(user.getRoleid());
		List<Function> functions = fs.getFuntionsByRole(role);
		List<EasyUINode> nodes = new ArrayList<EasyUINode>();
		functionToEasyUINode(functions, nodes, 0);
		return nodes;
	}

	private void functionToEasyUINode(List<Function> functions, List<EasyUINode> nodes, int parentId) {
		if (functions != null) {
			for (Function function : functions) {
				EasyUINode node = new EasyUINode();
				if (function.getParentid() == parentId) {
					node = function.toEasyUINode();
					functionToEasyUINode(functions, node.getChildren(), function.getId().intValue());
					nodes.add(node);
				}
			}
		}
	}

}
