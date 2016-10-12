package jym.agtsys.controller;

import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jym.agtsys.service.CaptchaService;

@Controller
@RequestMapping("captcha")
public class CaptchaController {
	@Resource
	private CaptchaService cs;

	@RequestMapping(value="get",method=RequestMethod.GET)
	public void getCaptcha(HttpServletResponse resp) throws Exception{
		OutputStream os= resp.getOutputStream();
		String captcha =cs.getCaptcha(os);
		os.flush();
		os.close();
		
	}
}
