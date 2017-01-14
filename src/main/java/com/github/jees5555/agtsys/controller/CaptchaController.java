package com.github.jees5555.agtsys.controller;

import static com.github.jees5555.agtsys.constants.WebContants.*;

import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.jees5555.agtsys.service.CaptchaService;

@Controller
@RequestMapping("captcha")
public class CaptchaController {
	@Resource
	private CaptchaService cs;

	@RequestMapping(value = "get", method = RequestMethod.GET)
	public void getCaptcha(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		OutputStream os = resp.getOutputStream();
		String captcha = cs.getCaptcha(os);
		session.setAttribute(SESSION_CAPTCHA_KEY, captcha);
		os.flush();
		os.close();
	}

	@ResponseBody
	@RequestMapping(value = "check", method = RequestMethod.POST)
	public String checkCaptcha(String captcha, HttpSession session) {
		String sessionCaptcha = (String) session.getAttribute(SESSION_CAPTCHA_KEY);
		if (captcha.equalsIgnoreCase(sessionCaptcha)) {
			return OPERATE_SUCCESS;
		} else {
			return OPERATE_FAILURE;
		}
	}

}
