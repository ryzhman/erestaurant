package com.restaurant.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	@RequestMapping(value="/admin-Staff", method=RequestMethod.GET)
	public String adminStaff(){
		
		return "admin-Staff?faces-redirect=true";
	}
	
	@RequestMapping(value="/administator/Administrator-Main", method=RequestMethod.GET)
	public String adminMain(){
		return "Administrator-Main?faces-redirect=true";
	}
}
