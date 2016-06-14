package com.ithxh.xhh.controller.qq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/test")
public class TestCtrl{

	@RequestMapping(value="toLogin")
	public String toLogin(){
		
		return "front/login";
	}
	
	@RequestMapping(value="toError")
	public String toError(){
		
		return "error/404";
	}
	
	@RequestMapping(value="toReg")
	public String toRegister(){
		
		return "front/register";
	}
}
