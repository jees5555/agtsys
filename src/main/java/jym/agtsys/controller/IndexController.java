package jym.agtsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	//进入登陆页面
		@RequestMapping(value={"login","/","index"},method=RequestMethod.GET)
		public String login(){
			return "login";
		}
		
		
}
