package com.github.jees5555.agtsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String tologin() {
		return "redirect:/login";
	}

	// 进入登陆页面
	@RequestMapping(value = { "login" }, method = RequestMethod.GET)
	public String login() {
		return "login";
	}

}
