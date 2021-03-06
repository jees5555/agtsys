package com.github.jees5555.agtsys.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseIntercepter implements HandlerInterceptor {

	@Override
	public abstract boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	protected boolean exclude(HttpServletRequest request) {
		String path = request.getServletPath(); // 截去上下文路径，例如：/user/login
		// 排除首页的链接
		if (path.equals("/") || path.contains("login") || path.contains("captcha")) {
			return true;
		} else {
			return false;
		}
	}

}
