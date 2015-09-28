package com.packt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
	public static final String SUCCESS = "User created";
	public static final String ERROR = "Cannot create the user due to following error =";
	@Autowired
	private RegistrationService registrationService;
	
	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@RequestMapping({ "/register" })
	public String showRegisterView(ModelMap model) {
		return "register";
	}
	
	@RequestMapping({ "/onRegistration" })
	public ModelAndView onRegistration(ModelMap model) {
		String error = registrationService.hasError();
		if(error != null){
			model.addAttribute("message", ERROR+error);
		}else{
			model.addAttribute("message", SUCCESS);
		}
		return new ModelAndView("register", model);
	}
	
}
