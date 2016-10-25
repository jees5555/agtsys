package jym.agtsys.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static jym.agtsys.constants.WebContants.*;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class GlobalExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
		//判断是否是ajax请求
		if(request.getHeader("X-Requested-With")!=null){
			mv.setViewName("ajaxexception");
			mv.addObject(OPERATE_EXCEPTION,"服务器异常，请联系管理员！");
		}else{
			mv.setViewName("nonajaxexception");
			mv.addObject(OPERATE_EXCEPTION,"服务器异常，请联系管理员！");
		}
		return mv;
	}
}
