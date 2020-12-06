package com.tome.main.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping(value = "/")
	public String home() {
		return "index";
	}
	
	@RequestMapping(value="/login")
	public String loginPage() {
		return "login";
	}
	@RequestMapping(value="/loginfail")
	public String loginFail() {
		return "loginFail";
	}
	
	@RequestMapping(value = "/accounthome")
	public String accountHome() {
		return "Account";
	}
	
	@RequestMapping(value = "/viewchar")
	public String charView() {
		return "viewchar";
	}
	
	@RequestMapping(value = "/CreateChar")
	public String charCreate() {
		return "Createchar";
	}
	
	@RequestMapping(value = "/buyskill")
	public String buySkill() {
		return "spendXP";
	}
	
	@RequestMapping(value = "/admin") 
	public String admin() {
		return "Admin";
	}
	
	

}
