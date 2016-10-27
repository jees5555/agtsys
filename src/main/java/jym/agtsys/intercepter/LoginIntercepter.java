package jym.agtsys.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static jym.agtsys.constants.WebContants.*;

import jym.agtsys.exception.AccessDeniedException;

public class LoginIntercepter extends BaseIntercepter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
	        	//排除登录页面
				if(this.exclude(request)){
					return true;
				}else{
					//登录验证
					if(checkLogin(request,response)){
						//再次排除
						if(this.loginExclude(request)){
							return true;
						}
						//防止非法路径进入
						if(checkURL(request)){
							return true;
						}else{
						    return false;
						}
					}else{
						return false;
					}
				}
			}
	//登陆后再次排除
		private boolean loginExclude(HttpServletRequest request){
				String path = request.getServletPath();
				if(path.contains("main")){
					return true;
				}else{
					return false;
				}
			}
	
	// 验证登录方法
	private boolean checkLogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//如果用户已经登录放行
		if (request.getSession().getAttribute(SESSION_LOGIN_KEY) != null){
			return true;
		}else{
			//非法请求 即这些请求需要登录后才能访问
			// 重定向到登录页面
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
	}


		// 拦截页面非法请求：盗链等等
	 private boolean checkURL(HttpServletRequest request)throws AccessDeniedException {
		     String referer = request.getHeader("REFERER");// 获取父url
				//判断合法
				if ("".equals(referer) || null == referer) {
					throw new AccessDeniedException("非法访问");
				}else{
					if(referer.contains("login")||referer.contains("main")){
						return true;
					}
					else{
						return false;
					}
				}
	}
		
}
