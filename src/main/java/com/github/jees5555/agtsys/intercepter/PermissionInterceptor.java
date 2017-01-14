package com.github.jees5555.agtsys.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.bridge.AbortException;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.jees5555.agtsys.domain.Permission;
import com.github.jees5555.agtsys.domain.Role;
import com.github.jees5555.agtsys.domain.User;
import com.github.jees5555.agtsys.exception.AccessDeniedException;
import com.github.jees5555.agtsys.service.PermissionService;

import static com.github.jees5555.agtsys.constants.WebContants.*;

import java.util.List;

public class PermissionInterceptor extends BaseIntercepter {
	@Autowired
	public PermissionService ps;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (this.exclude(request)) {
			return true;
		}
		return checkPermission(request);
	}

	private boolean checkPermission(HttpServletRequest request) throws Exception {
		boolean flag = false;
		String path = request.getServletPath();
		// 截取模块名称
		String[] urls = path.split("/");
		String model = urls[1];
		// 排除项目
		if (path.contains("main") || path.contains("check") || path.contains("login") || path.contains("logout")) {
			return true;
		}
		// 获取当前用户的权限功能
		User user = (User) request.getSession().getAttribute(SESSION_LOGIN_KEY);
		Role role = new Role();
		role.setId(user.getRoleid());
		List<Permission> permissions = ps.getPermissionByRole(role);
		for (Permission permission : permissions) {
			String funcurl = permission.getFuncurl().trim();
			if (funcurl.contains(model) && funcurl != null && !funcurl.equals("")) {
				return flag = true;
			}
		}
		if (!flag) {
			throw new AccessDeniedException("没有权限");
		}
		return flag;
	}

}
